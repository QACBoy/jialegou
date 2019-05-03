package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.SeckillOrder;

public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SeckillOrder record);

    SeckillOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);
}