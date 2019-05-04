package com.hilkr.seckill.vo;

import com.hilkr.auth.entity.UserInfo;
import com.hilkr.dal.model.SeckillSku;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-05-03
 */
public class SeckillMessage {
    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 秒杀商品
     */
    private SeckillSku seckillSku;

    public SeckillMessage() {
    }

    public SeckillMessage(UserInfo userInfo, SeckillSku seckillSku) {
        this.userInfo = userInfo;
        this.seckillSku = seckillSku;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SeckillSku getSeckillSku() {
        return seckillSku;
    }

    public void setSeckillSku(SeckillSku seckillSku) {
        this.seckillSku = seckillSku;
    }
}
