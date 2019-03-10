package com.hilkr.item.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/3
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class JialegouItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouItemServiceApplication.class, args);
    }
}
