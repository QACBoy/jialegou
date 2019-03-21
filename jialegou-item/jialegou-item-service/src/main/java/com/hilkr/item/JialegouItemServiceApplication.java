package com.hilkr.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/3
 *
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan({
        "com.baomidou.mybatisplus.samples.quickstart.mapper",
        "com.hilkr.common.dal"
})
public class JialegouItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouItemServiceApplication.class, args);
    }
}


