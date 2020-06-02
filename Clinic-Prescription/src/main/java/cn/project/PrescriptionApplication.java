package cn.project;

import cn.project.utils.HttpClientHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@SpringBootApplication
@MapperScan(basePackages = "cn.project.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class PrescriptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrescriptionApplication.class,args);
    }
    @Bean
    HttpClientHelper httpClientHelper(HttpServletRequest request){
        return new HttpClientHelper(request);
    }
}
