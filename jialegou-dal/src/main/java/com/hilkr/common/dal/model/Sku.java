package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Sku extends BaseModel {

    private Long id;

    private Long spuId;

    private String title;

    private String images;

    private Long price;

    private String indexes;

    private String ownSpec;

    private Boolean enable;

    private Date skuCreateTime;

    private Date lastUpdateTime;

}