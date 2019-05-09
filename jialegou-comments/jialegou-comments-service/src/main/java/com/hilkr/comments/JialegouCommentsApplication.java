package com.hilkr.comments;

import com.hilkr.BaseApplication;
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
public class JialegouCommentsApplication extends BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouCommentsApplication.class,args);
    }
}
