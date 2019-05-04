package com.hilkr.payment.Alipay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-04
 */
@Slf4j
@RestController
@RequestMapping("/payment/alipay")
public class test {

    @RequestMapping("/callback")
    public Object test(HttpServletRequest httpServletRequest){
        log.info("alipay 支付回调成功");
        Map<String,String> params = new HashMap<>();
        Map requestParameterMap = httpServletRequest.getParameterMap();
        System.out.println(requestParameterMap.toString());
        return requestParameterMap;
    }
}
