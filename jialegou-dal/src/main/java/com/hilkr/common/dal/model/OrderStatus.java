package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class OrderStatus extends BaseModel {

    private Long orderId;

    private Integer status;

    private Date orderCreateTime;

    private Date paymentTime;

    private Date consignTime;

    private Date endTime;

    private Date closeTime;

    private Date commentTime;

}