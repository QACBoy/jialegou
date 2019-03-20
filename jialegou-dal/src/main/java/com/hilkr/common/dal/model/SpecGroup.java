package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class SpecGroup extends BaseModel {

    private Long id;

    private Long cid;

    private String name;

}