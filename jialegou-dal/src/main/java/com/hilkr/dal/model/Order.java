package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Order extends BaseModel {

    private Long orderId;

    private Long totalPay;

    private Long actualPay;

    private String promotionIds;

    private Boolean paymentType;

    private Long postFee;

    private Date orderCreateTime;

    private String shippingName;

    private String shippingCode;

    private String userId;

    private String buyerMessage;

    private String buyerNick;

    private Boolean buyerRate;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverMobile;

    private String receiverZip;

    private String receiver;

    private Integer invoiceType;

    private Integer sourceType;

}