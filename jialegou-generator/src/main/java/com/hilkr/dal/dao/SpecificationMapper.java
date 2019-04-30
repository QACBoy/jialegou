package com.hilkr.dal.dao;

import com.hilkr.dal.model.Specification;

public interface SpecificationMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(Specification record);

    int insertSelective(Specification record);

    Specification selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);
}