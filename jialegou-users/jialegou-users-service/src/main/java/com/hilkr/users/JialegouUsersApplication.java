package com.hilkr.users;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.hilkr.BaseApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
public class JialegouUsersApplication extends BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouUsersApplication.class);
    }

}