package com.hilkr.common.dal.model;

import com.hilkr.common.dal.BaseModel;
import lombok.Data;

import java.util.Date;

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

    private Date lastUpdateTime;

}