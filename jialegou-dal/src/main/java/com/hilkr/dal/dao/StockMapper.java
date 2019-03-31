package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {

    @Update("update stock set stock = stock - #{num} where sku_id = #{skuId} and stock >= #{num}")
    int decreaseStock(@Param("skuId") Long skuId, @Param("num") Integer num);
    // int deleteByPrimaryKey(Long skuId);
    //
    // int insert(Stock record);
    //
    // int insertSelective(Stock record);
    //
    // Stock selectByPrimaryKey(Long skuId);
    //
    // int updateByPrimaryKeySelective(Stock record);
    //
    // int updateByPrimaryKey(Stock record);
    int insertList(@Param("stocks") List<Stock> stocks);

}