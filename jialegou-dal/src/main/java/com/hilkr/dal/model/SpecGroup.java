package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

@Data
public class SpecGroup extends BaseModel {

    private Long id;

    private Long cid;

    private String name;

}