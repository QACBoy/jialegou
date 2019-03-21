package com.hilkr.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-03-21
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class JialegouUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouUploadApplication.class);
    }
}
