package com.hilkr.dal.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderStatus {
    private Long orderId;

    private Integer status;

    private Date createTime;

    private Date paymentTime;

    private Date consignTime;

    private Date endTime;

    private Date closeTime;

    private Date commentTime;
}