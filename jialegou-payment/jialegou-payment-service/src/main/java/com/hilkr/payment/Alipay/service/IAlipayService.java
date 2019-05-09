package com.hilkr.payment.Alipay.service;

import com.hilkr.payment.Alipay.enums.PayState;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-05
 */
public interface IAlipayService {

    String createPayUrl(Long orderId);

    PayState queryOrder(Long orderId);

    void alipayCallback(HttpServletRequest request);

}
