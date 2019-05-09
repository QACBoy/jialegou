package com.hilkr.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@EnableEurekaServer
@SpringBootApplication
public class JialegouRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouRegistryApplication.class, args);
    }

}
