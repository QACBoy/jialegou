package com.hilkr.dal.model;

import com.hilkr.dal.BaseModel;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class SpecGroup extends BaseModel {

    private Long id;

    private Long cid;

    private String name;

    // @Transient
    // private List<SpecParam> params;
}