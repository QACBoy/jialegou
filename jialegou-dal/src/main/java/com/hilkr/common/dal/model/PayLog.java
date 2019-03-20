package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class PayLog extends BaseModel {

    private Long orderId;

    private Long totalFee;

    private Long userId;

    private String transactionId;

    private Boolean status;

    private Boolean payType;

    private String bankType;

    private Date payCreateTime;

    private Date payTime;

    private Date payClosedTime;

    private Date refundTime;

}