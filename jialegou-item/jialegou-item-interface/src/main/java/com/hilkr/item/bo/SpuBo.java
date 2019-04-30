package com.hilkr.item.bo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.hilkr.dal.model.Sku;
import com.hilkr.dal.model.Spu;
import com.hilkr.dal.model.SpuDetail;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SpuBo extends Spu {
    /**
     * 商品分类名称
     */
    @TableField(exist = false)
    private String cname;
    /**
     * 品牌名称
     */
    @TableField(exist = false)
    private String bname;

    /**
     * 商品详情
     */
    @TableField(exist = false)
    private SpuDetail spuDetail;

    /**
     * sku列表
     */
    @TableField(exist = false)
    private List<Sku> skus;

    public SpuBo() {
    }

    public SpuBo(Long brandId, Long cid1, Long cid2, Long cid3, String title, String subTitle, Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        super(brandId, cid1, cid2, cid3, title, subTitle, saleable, valid, createTime, lastUpdateTime);
    }
}
