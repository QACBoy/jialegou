package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.SeckillSku;

public interface SeckillSkuMapper extends BaseMapper<SeckillSku> {
    int deleteByPrimaryKey(Integer id);

    int insert(SeckillSku record);

    int insertSelective(SeckillSku record);

    SeckillSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeckillSku record);

    int updateByPrimaryKey(SeckillSku record);
}