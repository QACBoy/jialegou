package com.hilkr.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hilkr.common.parameter.pojo.SpuQueryByPageParameter;
import com.hilkr.common.vo.PageResult;
import com.hilkr.dal.model.SeckillSku;
import com.hilkr.dal.model.Sku;
import com.hilkr.dal.model.SpuDetail;
import com.hilkr.dal.model.Stock;
import com.hilkr.item.bo.SeckillParameter;
import com.hilkr.item.bo.SpuBo;

import java.text.ParseException;


import java.util.List;


/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface IGoodsService extends IService<Stock> {
    /**
     * 分页查询
     *
     * @param spuQueryByPageParameter
     * @return
     */
    PageResult<SpuBo> querySpuByPageAndSort(SpuQueryByPageParameter spuQueryByPageParameter);

    /**
     * 保存商品
     *
     * @param spu
     */
    void saveGoods(SpuBo spu);

    /**
     * 根据id查询商品信息
     *
     * @param id
     * @return
     */
    SpuBo queryGoodsById(Long id);

    /**
     * 更新商品信息
     *
     * @param spuBo
     */
    void updateGoods(SpuBo spuBo);

    /**
     * 商品下架
     *
     * @param id
     */
    void goodsSoldOut(Long id);

    /**
     * 商品删除，单个多个二合一
     *
     * @param id
     */
    void deleteGoods(long id);

    /**
     * 根据spu商品id查询详细信息
     *
     * @param id
     * @return
     */
    SpuDetail querySpuDetailBySpuId(long id);

    /**
     * 根据Sku的id查询其下所有的sku
     *
     * @param id
     * @return
     */
    List<Sku> querySkuBySpuId(Long id);

    /**
     * 发送校区到mq
     *
     * @param id
     * @param type
     */
    void sendMessage(Long id, String type);

    /**
     * 查询sku根据id
     *
     * @param id
     * @return
     */
    Sku querySkuById(Long id);

    /**
     * 查询秒杀商品
     *
     * @return
     */
    List<SeckillSku> querySeckillGoods();

    /**
     * 添加秒杀商品
     *
     * @param seckillParameter
     */
    void addSeckillGoods(SeckillParameter seckillParameter) throws ParseException;
    // PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);
    //
    // SpuDetail querySpuDetailBySpuId(Long spuId);
    //
    // List<Sku> querySkuBySpuId(Long spuId);
    //
    // void deleteGoodsBySpuId(Long spuId);
    //
    // void addGoods(Spu spu);
    //
    // void updateGoods(Spu spu);
    //
    // void handleSaleable(Spu spu);
    //
    // Spu querySpuBySpuId(Long spuId);
    //
    // List<Sku> querySkusByIds(List<Long> ids);
    //
    // void decreaseStock(List<CartDto> cartDtos);
}
