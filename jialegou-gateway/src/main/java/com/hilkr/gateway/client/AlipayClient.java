package com.hilkr.gateway.client;

import com.hilkr.payment.Alipay.api.AlipayApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "payment-service")
public interface AlipayClient extends AlipayApi {
}
