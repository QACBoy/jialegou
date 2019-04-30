package com.hilkr.users;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.hilkr.dal.mapper")
public class JialegouUsersApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouUsersApplication.class);
    }

}