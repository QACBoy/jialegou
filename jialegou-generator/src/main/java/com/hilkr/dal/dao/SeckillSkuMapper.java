package com.hilkr.dal.dao;

import com.hilkr.dal.model.SeckillSku;

public interface SeckillSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeckillSku record);

    int insertSelective(SeckillSku record);

    SeckillSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeckillSku record);

    int updateByPrimaryKey(SeckillSku record);
}