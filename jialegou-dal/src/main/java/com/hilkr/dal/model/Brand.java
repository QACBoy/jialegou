package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hilkr.dal.BaseModel;
import lombok.Data;

@Data
@TableName("brand")
public class Brand extends BaseModel {

    private Long id;

    private String name;

    private String image;

    private String letter;
}