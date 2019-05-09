package com.hilkr.page.service;

import java.util.Map;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface IGoodsService {
    /**
     * 商品详细信息
     * @param spuId
     * @return
     */
    Map<String,Object> loadModel(Long spuId);
}
