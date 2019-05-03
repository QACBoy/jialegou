package com.hilkr.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class JialegouAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouAuthApplication.class, args);
    }

}
