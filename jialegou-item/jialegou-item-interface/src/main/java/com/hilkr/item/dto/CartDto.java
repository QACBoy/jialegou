package com.hilkr.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
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
