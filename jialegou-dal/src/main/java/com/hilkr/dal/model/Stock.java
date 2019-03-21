package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

@Data
public class Stock extends BaseModel {

    private Long skuId;

    private Integer seckillStock;

    private Integer seckillTotal;

    private Integer stock;

}