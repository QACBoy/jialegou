package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class Brand extends BaseModel {

    private Long id;

    private String name;

    private String image;

    private String letter;
}