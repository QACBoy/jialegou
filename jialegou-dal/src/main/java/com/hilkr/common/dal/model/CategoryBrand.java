package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

@Data
public class CategoryBrand extends BaseModel {

    private Long categoryId;

    private Long brandId;

    //
    // private String remark;
    //
    // private Integer createBy;
    //
    // private Date createTime;
    //
    // private Integer updateBy;
    //
    // private Date updateTime;
    //
    // private Integer delFlg;

}