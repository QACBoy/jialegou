package com.hilkr.dal;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author sam
 * @create 2019-03-20
 */
@Data
public class BaseModel {

    private String remark;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private Integer delFlg;
}