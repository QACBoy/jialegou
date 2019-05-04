package com.hilkr.dal.model;

import lombok.Data;

@Data
public class OrderDetail {
    private Long id;

    private Long orderId;

    private Long skuId;

    private Integer num;

    private String title;

    private String ownSpec;

    private Double price;

    private String image;
}