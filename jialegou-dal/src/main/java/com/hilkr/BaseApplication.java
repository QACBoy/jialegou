package com.hilkr;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-02
 */
@MapperScan({
        "com.baomidou.mybatisplus.samples.quickstart.mapper",
        "com.hilkr.dal"
})
@AutoConfigureAfter({MybatisConfiguration.class})
@EnableAutoConfiguration
public class BaseApplication {
}
