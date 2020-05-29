package cn.project;

import cn.project.utils.HttpClientHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@MapperScan(basePackages = "cn.project.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class PrescriptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrescriptionApplication.class,args);
    }
    @Bean
    HttpClientHelper httpClientHelper(HttpServletRequest request){
        return new HttpClientHelper(request);
    }
}
