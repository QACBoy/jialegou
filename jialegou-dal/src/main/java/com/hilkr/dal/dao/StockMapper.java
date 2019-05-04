package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {
    int deleteByPrimaryKey(Long skuId);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long skuId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List<Stock> selectBatchSkuIdList(@Param("skuIdList") List<Long> skuIdList);

    /**
     * 更新对应商品的库存,且库存必须大于0，否则回滚。
     * @param skuId
     * @param num
     */
    @Update("update stock set stock = stock - #{num} where sku_id = #{skuId} and stock > 0")
    void reduceStock(@Param("skuId") Long skuId, @Param("num") Integer num);

    /**
     * 更新对应商品的秒杀库存,且库存必须大于0，否则回滚。
     * @param skuId
     * @param num
     */
    @Update("update stock set seckill_stock = seckill_stock - #{num} where sku_id = #{skuId} and seckill_stock > 0")
    void reduceSeckStock(@Param("skuId") Long skuId, @Param("num") Integer num);
}