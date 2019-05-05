package com.hilkr.payment.Alipay.controller;

import com.hilkr.payment.Alipay.api.AlipayApi;
import com.hilkr.payment.Alipay.enums.PayState;
import com.hilkr.payment.Alipay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AlipayController implements AlipayApi {

    @Autowired
    private IAlipayService alipayService;

    @RequestMapping("/callback")
    public Object test(HttpServletRequest httpServletRequest) {
        log.info("alipay 支付回调成功");
        Map<String, String> params = new HashMap<>();
        Map requestParameterMap = httpServletRequest.getParameterMap();
        System.out.println(requestParameterMap.toString());
        return requestParameterMap;
    }

    @Override
    public String generateUrl(@PathVariable("id") Long orderId) {
        log.info("alipay 获取付款链接中...");
        String url = alipayService.createPayUrl(orderId);
        if (StringUtils.isNotBlank(url)) {
            return url;
        } else {
            return null;
        }
    }

    @Override
    public PayState queryOrder(Long orderId) {
        return alipayService.queryOrder(orderId);
    }

    @Autowired
    Environment environment;

    public String getPort(){
        return environment.getProperty("local.server.port");
    }

    @Override
    public String getServerInfo(){
        String info = " server port: " + getPort();
        return info;
    }

}
