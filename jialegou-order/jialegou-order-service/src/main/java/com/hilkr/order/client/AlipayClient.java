package com.hilkr.order.client;

import com.hilkr.payment.Alipay.api.AlipayApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-05
 */
@FeignClient(value = "payment-service")
public interface AlipayClient extends AlipayApi {
}
