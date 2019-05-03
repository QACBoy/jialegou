package com.hilkr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/3
 *
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class JialegouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouGatewayApplication.class);
    }
}