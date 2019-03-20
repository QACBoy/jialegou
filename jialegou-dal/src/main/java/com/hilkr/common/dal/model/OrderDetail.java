package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class OrderDetail extends BaseModel {

    private Long id;

    private Long orderId;

    private Long skuId;

    private Integer num;

    private String title;

    private String ownSpec;

    private Long price;

    private String image;

}