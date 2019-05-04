package com.hilkr.dal.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class SeckillSku {
    private Long id;

    private Long skuId;

    private Date startTime;

    private Date endTime;

    private String title;

    private Double seckillPrice;

    private String image;

    private Boolean enable;

    @JsonIgnore
    @TableField(exist = false)
    private Integer stock;

    @JsonIgnore
    @TableField(exist = false)
    private Integer seckillTotal;

}