package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {
    int deleteByPrimaryKey(Long skuId);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long skuId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List<Stock> selectBatchSkuIdList(@Param("skuIdList") List<Long> skuIdList);
}