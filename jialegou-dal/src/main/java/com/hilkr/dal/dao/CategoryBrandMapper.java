package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Category;
import com.hilkr.dal.model.CategoryBrandKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryBrandMapper extends BaseMapper<CategoryBrandKey> {

    int deleteByPrimaryKey(CategoryBrandKey key);

    int insert(CategoryBrandKey record);

    int insertSelective(CategoryBrandKey record);
}