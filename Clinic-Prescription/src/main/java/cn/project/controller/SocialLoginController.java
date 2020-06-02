package cn.project.controller;

import cn.project.utils.HttpClientHelper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/social")
//@Controller
public class SocialLoginController {
    private static final Map<String, SocialConfig> CONFIG_MAP = new HashMap<>();
    private static final String uaaServerUrl = "http://localhost:8080";
    private static final String apiServerUrl = "http://localhost:8081/index";
    @Resource
    private HttpClientHelper httpClientHelper;
    static {
        CONFIG_MAP.put("gitee", new SocialConfig(){{
            setClientId("6d9a0dfa5624f9afa5f449f26fd544f67cb7b39bbe35a841bed65824a2ead8cd");
            setCallbackUrl("http://ximm8c.natappfree.cc/social/gitee/callback");
            setClientSecret("6b00dbfc0194be9d4646bc082fc739b7c891d359912873f3dca5d853ffdff340");
            setOauthAuthorize("/oauth/authorize");
            setOauthToken("/oauth/token");
            setServerUrl("https://gitee.com");
            setUserInfoApi("https://gitee.com/api/v5/user");
        }});
        CONFIG_MAP.put("github", new SocialConfig(){{
            setCallbackUrl("http://ximm8c.natappfree.cc/social/github/callback");
            setClientId("Iv1.6bcab9447ee1c924");
            setClientSecret("c193595bd19e09042d4d893285c8f82788a01ca8");
            setOauthAuthorize("/login/oauth/authorize");
            setOauthToken("/login/oauth/access_token");
            setServerUrl("https://github.com");
            setUserInfoApi("https://api.github.com/user");
        }});
    }
    @RequestMapping("/authorize/{systemId}")
    public void step1(@PathVariable String systemId, HttpServletResponse response) throws Exception {
        SocialConfig socialConfig = CONFIG_MAP.get(systemId);
        StringBuffer bufferUri = new StringBuffer(socialConfig.getServerUrl())
                .append(socialConfig.getOauthAuthorize())
                .append("?")
                .append("client_id=")
                .append(socialConfig.getClientId())
                .append("&redirect_uri=")
                .append(URLEncoder.encode(socialConfig.getCallbackUrl(), "UTF-8"))
                .append("&")
                .append("response_type=code");
        response.sendRedirect(bufferUri.toString());
    }

    @RequestMapping("/{systemId}/callback")
    public void step2(@PathVariable String systemId, String code, HttpServletResponse response) throws Exception{
        SocialConfig socialConfig = CONFIG_MAP.get(systemId);
        StringBuffer bufferUri = new StringBuffer(socialConfig.getServerUrl())
                .append(socialConfig.getOauthToken())
                .append("?grant_type=authorization_code&code=")
                .append(code)
                .append("&client_id=")
                .append(socialConfig.getClientId())
                .append("&redirect_uri=")
                .append(URLEncoder.encode(socialConfig.getCallbackUrl(), "UTF-8"))
                .append("&client_secret=")
                .append(socialConfig.getClientSecret());
        Map<String, Object> returnMap = httpClientHelper.postForMap(bufferUri.toString());
        //第三步，通过access_token获取用户信息
        String access_token = (String) returnMap.get("access_token");
        bufferUri.setLength(0);
        bufferUri.append(socialConfig.getUserInfoApi())
                .append("?access_token=")
                .append(access_token);
        Map<String, Object> userInfoMap = httpClientHelper.getForMap(bufferUri.toString());
        //跳回授权中心登录
        String name = null;
        if(userInfoMap.get("name") != null){
            name = URLEncoder.encode(userInfoMap.get("name").toString(),"UTF-8");
        }
        bufferUri.setLength(0);
        bufferUri.append(uaaServerUrl)
                .append("/social/login?systemId=").append(systemId)
                .append("&id=").append(userInfoMap.get("id"))
                .append("&login=").append(userInfoMap.get("login"))
                .append("&name=").append(name)
                .append("&avatar_url=").append(userInfoMap.get("avatar_url"))
                .append("&redirect_uri=").append(URLEncoder.encode(apiServerUrl,"UTF-8"));//登录成功后回调的地址
        response.sendRedirect(bufferUri.toString());
    }

    @Data
    @Accessors(chain = true)
    private static class SocialConfig {
        private String systemId;
        private String serverUrl;
        private String oauthAuthorize;
        private String oauthToken;
        private String userInfoApi;
        private String clientId;
        private String clientSecret;
        private String callbackUrl;

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public String getServerUrl() {
            return serverUrl;
        }

        public void setServerUrl(String serverUrl) {
            this.serverUrl = serverUrl;
        }

        public String getOauthAuthorize() {
            return oauthAuthorize;
        }

        public void setOauthAuthorize(String oauthAuthorize) {
            this.oauthAuthorize = oauthAuthorize;
        }

        public String getOauthToken() {
            return oauthToken;
        }

        public void setOauthToken(String oauthToken) {
            this.oauthToken = oauthToken;
        }

        public String getUserInfoApi() {
            return userInfoApi;
        }

        public void setUserInfoApi(String userInfoApi) {
            this.userInfoApi = userInfoApi;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getCallbackUrl() {
            return callbackUrl;
        }

        public void setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
        }
    }
}
