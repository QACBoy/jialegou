package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

@Data
public class SpuDetail extends BaseModel {

    private Long spuId;

    private String genericSpec;

    private String specialSpec;

    private String packingList;

    private String afterService;
}