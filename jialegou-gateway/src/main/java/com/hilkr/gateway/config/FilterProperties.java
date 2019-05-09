package com.hilkr.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * 白名单过滤
 *
 * @author hilkr
 */
//@ConfigurationProperties(prefix = "jialegou.filter")
@Configuration
@RefreshScope
public class FilterProperties {

    @Value("${jialegou.filter.allowPaths}")
    private String allowPaths;

    public String getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(String allowPaths) {
        this.allowPaths = allowPaths;
    }
}
