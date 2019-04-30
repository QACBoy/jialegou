package com.hilkr.dal.dao;

import com.hilkr.dal.model.CategoryBrandKey;

public interface CategoryBrandMapper {
    int deleteByPrimaryKey(CategoryBrandKey key);

    int insert(CategoryBrandKey record);

    int insertSelective(CategoryBrandKey record);
}