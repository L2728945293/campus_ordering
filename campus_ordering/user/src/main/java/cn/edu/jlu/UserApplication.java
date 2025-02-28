package cn.edu.jlu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("cn.edu.jlu.mapper")
@EnableDiscoveryClient
//@EnableFeignClients
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}