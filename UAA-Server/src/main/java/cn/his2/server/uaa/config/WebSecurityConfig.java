package cn.his2.server.uaa.config;

import cn.his2.server.uaa.response.ResultCode;
import cn.his2.server.uaa.response.ResultJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin1").password("$2a$10$T6Vl8WfdPZsK/4U25LRqM.wyv52TGTtmY7BxC/k4N01VyThUtjGoK").roles("ADMIN","USER")
                .and()
                .withUser("user02").password("$2a$10$T6Vl8WfdPZsK/4U25LRqM.wyv52TGTtmY7BxC/k4N01VyThUtjGoK").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*//未登录访问, 执行该方法
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            printResult(response, ResultCode.UNAUTHORIZED, authException.getMessage());
        });
        //登陆状态下访问，权限不足执行该方法
        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            printResult(response, ResultCode.FORBIDDEN, accessDeniedException.getMessage());
        });
        //使用jwt, 关闭csrf，设置无状态session
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
        //除登录注册以外都被保护
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login","/sign","/validateToken","/error/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
    }


    private void printResult(HttpServletResponse response, ResultCode forbidden, String message) throws IOException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(forbidden, message).toString();
        printWriter.write(body);
        printWriter.flush();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "swagger-ui.html",
                        "**/swagger-ui.html",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.gif",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/**/*.ttf"
                );
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html"
        );
    }
}
