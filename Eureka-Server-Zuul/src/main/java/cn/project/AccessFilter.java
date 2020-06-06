package cn.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AccessFilter extends ZuulFilter {
    @Value("${jwt.publicKey}")
    private String publicKey;
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String uri = request.getRequestURI();
        if (log.isDebugEnabled()) {
            log.debug("request uri: {}", uri);
        }
        return !StringUtils.equals(uri, "/api/uaa-server/login");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // 判断请求头部中是否包含token信息
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header)) {
            // 请求头中没有，判断请求参数是否存在token信息
            header = request.getParameter("Authorization");
        }
        if (StringUtils.isEmpty(header)) {
            // 请求中不包含token信息，提示登录
            Map<String, String> error = new HashMap<>();
            error.put("msg","请先登录");
            error.put("code","401");
            printJSON(currentContext, HttpServletResponse.SC_UNAUTHORIZED, error);
            return null;
        }
        //使用公钥验证签名
        try {
            Map<String, Object> claims =
                    JwtHelper.parseToken(header.substring("Bearer ".length()),
                            JwtHelper.getPublicKey(publicKey.getBytes()));
            if(log.isDebugEnabled()) {
                log.debug("request uri:{}", request.getRequestURI());
                log.debug("jwt claims:{}", claims);
            }
        } catch (Exception e) {
            //验证jwt失败
            Map<String, String> error = new HashMap<>();
            error.put("msg",e.getMessage());
            error.put("code","500");
            printJSON(currentContext, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, error);
            log.error(e.getMessage(), e);
            return null;
        }
        // 向下游服务器传递token
//        currentContext.addZuulRequestHeader("Authorization", header);
        return null;
    }

    private void printJSON(RequestContext ctx, int statusCode, Object error) {
        ObjectMapper objectMapper = new ObjectMapper();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(statusCode);
        ctx.getResponse().setContentType("application/json;charset=utf-8");
        try {
            ctx.setResponseBody(objectMapper.writeValueAsString(error));
        } catch (JsonProcessingException e) {
            ctx.setResponseBody("{\"ex\":\""+e.getMessage()+"\"}");
            log.error(e.getMessage(), e);
        }
    }
}
