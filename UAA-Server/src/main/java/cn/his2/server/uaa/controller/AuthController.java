package cn.his2.server.uaa.controller;

import cn.his2.server.uaa.pojo.vo.TokenVO;
import cn.his2.server.uaa.pojo.vo.UserVO;
import cn.his2.server.uaa.response.ResultJson;
import cn.his2.server.uaa.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultJson<TokenVO> login(@Valid @RequestBody UserVO userVO) {
        TokenVO tokenVO = authService.login(userVO.getUsername(), userVO.getPassword());
        return ResultJson.ok(tokenVO);
    }
    @PostMapping("/validateToken")
    @ApiOperation(value = "验证token, 获取用户信息", notes = "根据Jwt获取用户信息, code等于200为成功，其他值失败")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson<Map<String, Object>> user_info(HttpServletRequest request) throws Exception {
        Map<String, Object> jwtInfo = authService.getUserInfo(request.getHeader("Authorization"));
        return ResultJson.ok(jwtInfo);
    }
}
