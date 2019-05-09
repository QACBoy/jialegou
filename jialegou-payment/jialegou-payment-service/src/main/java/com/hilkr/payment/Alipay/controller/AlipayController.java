package com.hilkr.payment.Alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.hilkr.payment.Alipay.api.AlipayApi;
import com.hilkr.payment.Alipay.enums.PayState;
import com.hilkr.payment.Alipay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Slf4j
@RestController
public class AlipayController implements AlipayApi {

    @Autowired
    private IAlipayService alipayService;

    @RequestMapping("/callback")
    public void alipayCallback(HttpServletRequest request){
        Map<String,String> params = Maps.newHashMap();

        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){

                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
        }
        log.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.
        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                log.error("非法请求,验证不通过!");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝验证回调异常",e);
        }
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
    public PayState queryOrder(@PathVariable("id")Long orderId) {
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
