package com.jialegou.seckill;

import com.hilkr.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-03
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class JialegouSeckillApplication extends BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(JialegouSeckillApplication.class,args);
    }
}
