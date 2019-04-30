package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Specification;

public interface SpecificationMapper extends BaseMapper<Specification> {
    int deleteByPrimaryKey(Long categoryId);

    int insert(Specification record);

    int insertSelective(Specification record);

    Specification selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);
}