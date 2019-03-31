package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hilkr.dal.BaseModel;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Data
public class Spu extends BaseModel {

    private Long id;

    private String title;

    private String subTitle;

    private Long cid1;

    private Long cid2;

    private Long cid3;

    private Long brandId;

    private Boolean saleable;

    private Boolean valid;

    private Date spuCreateTime;

    @JsonIgnore
    private Date lastUpdateTime;

    // @Transient
    /**
     * spu所属的分类名称
     */
    @TableField(exist = false)
    private String cname;

    // @Transient
    /**
     * spu所属品牌名
     */
    @TableField(exist = false)
    private String bname;

    // @Transient
    /**
     * spu详情
     */
    @TableField(exist = false)
    private SpuDetail spuDetail;

    // @Transient
    /**
     * sku集合
     */
    @TableField(exist = false)
    private List<Sku> skus;
}