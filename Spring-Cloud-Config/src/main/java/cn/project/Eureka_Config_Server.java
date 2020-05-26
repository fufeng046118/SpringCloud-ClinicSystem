package cn.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Eureka_Config_Server {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_Config_Server.class,args);
    }
}
