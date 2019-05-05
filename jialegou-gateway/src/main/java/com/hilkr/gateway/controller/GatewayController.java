package com.hilkr.gateway.controller;

import com.hilkr.gateway.client.AlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-05
 */
@RestController
public class GatewayController {

    @Autowired
    private AlipayClient alipayClient;

    @GetMapping("/test/info")
    public String getinfo() {
        return alipayClient.getServerInfo();
    }

}
