package com.hilkr.order.utils;

/**
 * @author: HuYi.Zhang
 * @create: 2018-06-07 17:44
 **/
public enum PayState1 {
    /**
     * 未支付0
     * 支付成功1
     * 支付失败2
     */
    NOT_PAY(0),SUCCESS(1),FAIL(2);

    PayState1(int value) {
        this.value = value;
    }

    int value;

    public int getValue() {
        return value;
    }
}
