package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class Stock extends BaseModel {

    private Long skuId;

    private Integer seckillStock;

    private Integer seckillTotal;

    private Integer stock;

}