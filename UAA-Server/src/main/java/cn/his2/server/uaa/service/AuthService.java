package cn.his2.server.uaa.service;

import cn.his2.server.uaa.pojo.po.User;
import cn.his2.server.uaa.pojo.vo.TokenVO;

import java.util.Map;

public interface AuthService {
    /**
     *
     * @param username
     * @param password
     * @return token
     */
    TokenVO login(String username, String password);
    void register(User user);
    void logout(String token);
    TokenVO refresh(String oldToken);

    Map<String, Object> getUserInfo(String authorization) throws Exception;
}
