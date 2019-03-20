package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class Category extends BaseModel {

    private Long id;

    private String name;

    private Long parentId;

    private Boolean isParent;

    private Integer sort;

}