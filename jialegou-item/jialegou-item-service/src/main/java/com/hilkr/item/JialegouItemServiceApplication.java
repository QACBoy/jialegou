package com.hilkr.item;

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
 * @author hilkr
 */

@EnableDiscoveryClient
@SpringBootApplication
public class JialegouItemServiceApplication extends BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouItemServiceApplication.class, args);
    }
}


