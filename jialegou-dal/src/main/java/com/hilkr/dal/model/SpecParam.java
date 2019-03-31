package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hilkr.dal.BaseModel;
import lombok.Data;

@Data
public class SpecParam extends BaseModel {

    private Long id;

    private Long cid;

    private Long groupId;

    private String name;

    @TableField(value = "'numeric'")
    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searching;

    private String segments;

}