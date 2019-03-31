package com.hilkr.item.service;

import com.hilkr.common.vo.PageResult;
import com.hilkr.dal.model.Sku;
import com.hilkr.dal.model.Spu;
import com.hilkr.dal.model.SpuDetail;
import com.hilkr.item.dto.CartDto;

import java.util.List;


/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-29
 */
public interface IGoodsService {
    PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkuBySpuId(Long spuId);

    void deleteGoodsBySpuId(Long spuId);

    void addGoods(Spu spu);

    void updateGoods(Spu spu);

    void handleSaleable(Spu spu);

    Spu querySpuBySpuId(Long spuId);

    List<Sku> querySkusByIds(List<Long> ids);

    void decreaseStock(List<CartDto> cartDtos);
}
