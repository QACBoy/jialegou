package com.hilkr.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bystander
 * @date 2018/10/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    /**
     * 商品skuId
     */
    private Long skuId;

    /**
     * 购买数量
     */
    private Integer num;
}
