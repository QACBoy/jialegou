package com.hilkr.payment;

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
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class JialegouPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouPaymentApplication.class);
    }
}
