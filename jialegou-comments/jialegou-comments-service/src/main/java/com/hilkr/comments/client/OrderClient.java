package com.hilkr.comments.client;

import com.hilkr.order.api.OrderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "order-service")
public interface OrderClient extends OrderApi {
}
