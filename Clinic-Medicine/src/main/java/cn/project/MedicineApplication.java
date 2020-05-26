package cn.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "cn.project.mapper")
@EnableCaching
@EnableDiscoveryClient
public class MedicineApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicineApplication.class,args);
    }
}
