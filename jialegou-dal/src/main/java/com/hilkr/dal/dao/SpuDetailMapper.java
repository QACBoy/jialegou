package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.SpuDetail;

public interface SpuDetailMapper extends BaseMapper<SpuDetail> {
    int deleteByPrimaryKey(Long spuId);

    int insertSelective(SpuDetail record);

    SpuDetail selectByPrimaryKey(Long spuId);

    int updateByPrimaryKeySelective(SpuDetail record);

    int updateByPrimaryKeyWithBLOBs(SpuDetail record);

    int updateByPrimaryKey(SpuDetail record);
}