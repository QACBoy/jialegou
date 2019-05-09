package com.hilkr.comments.config;

import com.hilkr.comments.properties.IdWorkerProperties;
import com.hilkr.common.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Configuration
//@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfig {

    @Bean
    public IdWorker idWorker(IdWorkerProperties prop) {
        return new IdWorker(prop.getWorkerId(), prop.getDataCenterId());
    }
}
