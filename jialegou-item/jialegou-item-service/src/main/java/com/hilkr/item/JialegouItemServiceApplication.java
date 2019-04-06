package com.hilkr.item;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.hilkr.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
        "com.hilkr.dal"
})
@AutoConfigureAfter({MybatisConfiguration.class})// TODO: 无效配置 org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.hilkr.dal.dao.StockMapper.insertList
public class JialegouItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JialegouItemServiceApplication.class, args);
    }
}


