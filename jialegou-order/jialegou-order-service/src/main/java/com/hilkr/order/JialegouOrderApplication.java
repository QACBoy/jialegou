package com.hilkr.order;

import com.hilkr.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class JialegouOrderApplication extends BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouOrderApplication.class);
    }
}
