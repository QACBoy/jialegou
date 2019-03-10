package com.hilkr.common.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/3
 *
 */
// @EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class JialegouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouGatewayApplication.class);
    }
}
