package cn.his2.server.uaa.service;

import cn.his2.server.uaa.exception.CustomException;
import cn.his2.server.uaa.pojo.po.User;
import cn.his2.server.uaa.pojo.vo.TokenVO;
import cn.his2.server.uaa.response.ResultCode;
import cn.his2.server.uaa.response.ResultJson;
import cn.his2.server.uaa.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    /**
     * jwt签名私钥，实际项目应该妥善保管，不要直接写在代码中
     */
    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5tpTUWF6EG2BhbPWcWVrJtiNZ/E1duwmZxYw2tRabO/9gpxtXtLL8Lx6n7kxP/Ja5cpHvKuZv5Os2zt2KLsUo3JVcELgFld6ZzWxUwOniNxHKum+PJqZ1rOsu6nxl+rhofrdO7m969xXmSiz7ru6ODtyVOG9o3HCmn63jHGHQHhGvKGQ5uh7Fk4uWj2L31NZmVCO5y0gwbEk8IRcP5xo6fXKpsWeHwz6/GWillqqPrF7Fq7SVM95YRkiOd3P3YB7iAO7NKAuZTcWVFx1tLWFTStSi6nhyYiM1ibBOI5BkRlob+U3nyI5edJNjQn1o9q/RL0xmowe/7yqLTcp4MJ3FAgMBAAECggEASkNfynY3/X3xEaR/pvD4ZBcIlfBVg7jKgwpDIpFRo+vfGGhaf70Lodq52I9rRsUMBMZy4v/1vCL/iCA66QoA33Et7O3mmJ0wsbI6sr2eCU7aN4ZMHCNphz8JoO41ppYW7fpWFSM1wydRQ5C/4jk2venlfWpe6sDT712FLH9qAc2FMeAQL6eJqCB0Vtka8EsYFzPdAbUIifNk+ztsqzrUeyhSExWHvbS/TEE96wKyeGpnlvDKvm+yj3BC+CHcgee935Tv77LMta5yfV4WPjWZDxqypkHsVDKzMckYG345isl8e8JlJUP19AztNJdnCAa2wm3a61CV70lJWDHumOumgQKBgQDwRC1uGvCbVI4bCsw/dBJR9hnizR9UTCDBXNK0hjRaMZJ995xiGKGw+wfsHt5mda3WMX1g779WSsC9ufda09ajz1A7YBetty8mYu3JrNaRtjRIRustRM5CRNOWyXpKARzsPI3BtVboMIEdCrPsvgi2zfGxqY8Wg75V96tmbV+25QKBgQDF3+BDiZZr+Kn2xTStwMDX24OfNA8C72wAY3LrbPRtAPLgMBadfIdNG+P7zklmYzCir7Rcnwh2wG5qo7B+iXK4+1r878l5PWTTV2STgbSuufS+CDLOb0KNlPk/o3KrWm26IdEmRu1dEqPJ8s2xLDTzrX9fHtg+zB2tnZ1rs7z9YQKBgD9Ktmz6y8cfTw/wmD1e5jSRD+CfCFG8fCRzv02uzLu1s1FbmOZ+dpmYh028E/oJpfF2uACBJ3Yj2wIfqvlSNIiSZCA///zS9Ki4BsvJq/xWcw9XHzydk3gBiXt/N6enA2d3zB/rFSm1HU35M8x5gvIk7jZO5FKaAdnDlHM2/K/BAoGBAIAr4uV8ct4fZWC+HsBJseBghWKTYwHguhGKWX8bxTvyOGB1ZcC/UBjnbwbFeZADwhYvP+Ia9W2OejdDLmjYeWJPFoHuhI+u7+dhnXlatAHV3C1XvCkxsmdpTwYkxnsuCRNfIk3r9YarM0L1rr0LyITrJi0kGqiaqMKz7Ks98rYhAoGBALgoVLWjGJWFCyIz/wefaGkk+EYFBGIgvkwcWnwu98y2eo1x+a5hANSF9wb2eA/3lh+y0VVUpMf+lVfby2XbKQfQuQxiuJQQdEtzOpys00a4xmZ7YdEhwiKd/ltQTZDFo2+eHS78bSu0EyJaLS2rRVYzFrs+ocglC6PZtG6HFgmS";

    @Value("${jwt.publicKey}")
    private String publicKey;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.expiration}")
    private int expiration;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenVO login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("msg","这是一条测试消息");
        // 获取私钥
        PrivateKey privateKey = null;
        try {
            privateKey = JwtHelper.getPrivateKey(PRIVATE_KEY.getBytes());
        } catch (Exception e) {
            throw new CustomException(ResultJson.failure(ResultCode.SERVER_ERROR, e));
        }
        String token = JwtHelper.generateToken(userInfo, privateKey, expiration);
        return new TokenVO().setToken(token);
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void logout(String token) {

    }

    @Override
    public TokenVO refresh(String oldToken) {
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) throws Exception {
        token = token.substring(tokenHead.length());
        PublicKey pubKey = JwtHelper.getPublicKey(publicKey.getBytes());
        Map<String, Object> claims = JwtHelper.parseToken(token, pubKey);
        log.debug("parse info from token, method:getUserInfo:{}", claims);
        return claims;
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }
}
