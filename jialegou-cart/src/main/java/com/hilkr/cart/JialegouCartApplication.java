package com.hilkr.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class JialegouCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouCartApplication.class,args);
    }
}
