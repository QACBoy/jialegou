package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class SpuDetail extends BaseModel {

    private Long spuId;

    private String genericSpec;

    private String specialSpec;

    private String packingList;

    private String afterService;
}