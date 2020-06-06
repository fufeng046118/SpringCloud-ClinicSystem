package cn.project;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JwtHelper {
    /**
     * 生成jwt
     * @param userInfo
     * @param privateKey
     * @param expSeconds
     * @return
     */
    public static String generateToken(Map<String,Object> userInfo, PrivateKey privateKey, int expSeconds) {
        Date now = new Date();
        Date exp = DateUtils.addSeconds(now, expSeconds);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("sub")
                .setExpiration(exp)
                .setIssuedAt(now)
                .claim("USER_KEY", userInfo)
                .signWith(SignatureAlgorithm.RS512, privateKey);
        return jwtBuilder.compact();
    }

    /**
     * 解析jwt
     * @param token
     * @param publicKey
     * @return
     */
    public static Map<String, Object> parseToken(String token, PublicKey publicKey) {
        final Jwt jwt = Jwts.parser().setSigningKey(publicKey).parse(token);
        if(log.isDebugEnabled()) {
            log.debug("jwt header:{}", jwt.getHeader());
            log.debug("jwt body:{}", jwt.getBody());
        }
        return (Map<String, Object>) jwt.getBody();
    }

    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
        bytes = Base64.getDecoder().decode(bytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static PrivateKey getPrivateKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        bytes = Base64.getDecoder().decode(bytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }
}
