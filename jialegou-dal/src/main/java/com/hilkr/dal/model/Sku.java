package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hilkr.dal.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Sku extends BaseModel {

    private Long id;

    private Long spuId;

    private String title;

    private String images;

    private Long price;

    private String indexes;

    private String ownSpec;

    private Boolean enable;

    private Date skuCreateTime;

    private Date lastUpdateTime;

    /**
     * 库存
     */
    @TableField(exist = false)
    private Integer stock;

}