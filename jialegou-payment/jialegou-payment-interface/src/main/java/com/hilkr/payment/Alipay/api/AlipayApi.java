package com.hilkr.payment.Alipay.api;

import com.hilkr.payment.Alipay.enums.PayState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-05
 */
@RequestMapping("/payment/alipay")
public interface AlipayApi {

    @GetMapping("url/{id}")
    String generateUrl(@PathVariable("id") Long orderId);

    @GetMapping("state/{id}")
    PayState queryOrder(@PathVariable("id") Long orderId);

    @GetMapping("/info")
    String getServerInfo();
}
