package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hilkr.dal.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class SpecGroup extends BaseModel {

    private Long id;

    private Long cid;

    private String name;

    @TableField(exist = false)
    private List<SpecParam> params;
}