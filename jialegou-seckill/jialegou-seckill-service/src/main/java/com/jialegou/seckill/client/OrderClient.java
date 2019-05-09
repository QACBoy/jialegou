package com.jialegou.seckill.client;

import com.hilkr.order.api.OrderApi;
import com.jialegou.seckill.config.OrderConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "order-service", configuration = OrderConfig.class)
public interface OrderClient extends OrderApi {
}
