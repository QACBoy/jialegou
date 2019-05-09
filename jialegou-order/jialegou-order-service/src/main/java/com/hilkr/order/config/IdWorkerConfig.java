package com.hilkr.order.config;

import com.hilkr.common.utils.IdWorker;
import com.hilkr.order.properties.IdWorkerProperties;
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
