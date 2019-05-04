package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long orderId;

    @NotNull
    private Double totalPay;

    @NotNull
    private Double actualPay;

    @NotNull
    private String promotionIds;

    private Integer paymentType;

    private Long postFee;

    private Date createTime;

    private String shippingName;

    private String shippingCode;

    private Long userId;

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

    @TableField(exist = false)
    private List<OrderDetail> orderDetails;

    @TableField(exist = false)
    private Integer status;
}