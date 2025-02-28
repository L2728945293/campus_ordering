package cn.edu.jlu.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan("cn.edu.jlu")
@SpringBootApplication
@MapperScan("cn.edu.jlu.shop.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.edu.jlu.shop.client") // 添加此注解
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}