package com.hilkr.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-04-10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class JialegouPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouPageApplication.class);
    }
}
