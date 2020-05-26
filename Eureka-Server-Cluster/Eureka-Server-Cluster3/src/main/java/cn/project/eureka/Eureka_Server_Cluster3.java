package cn.project.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka_Server_Cluster3 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_Server_Cluster3.class,args);
    }
}
