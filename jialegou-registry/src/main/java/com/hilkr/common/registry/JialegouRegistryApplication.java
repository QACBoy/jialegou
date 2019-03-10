package com.hilkr.common.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/3
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class JialegouRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouRegistryApplication.class, args);
    }

}
