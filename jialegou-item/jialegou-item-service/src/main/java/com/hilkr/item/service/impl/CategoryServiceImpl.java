package com.hilkr.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hilkr.dal.dao.CategoryMapper;
import com.hilkr.dal.model.Category;
import com.hilkr.common.enums.ExceptionEnum;
import com.hilkr.common.exception.JialegouException;
import com.hilkr.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author sam
 * @create 2019-03-20
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryByPid(Long pid) {

        List<Category> list = categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_id",pid));
        if (CollectionUtils.isEmpty(list)){
            throw new JialegouException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<Category> queryCategoryByIds(List<Long> ids) {
        return categoryMapper.selectBatchIds(ids);

    }

    @Override
    public List<Category> queryAllByCid3(Long id) {
        Category c3 = categoryMapper.selectById(id);
        Category c2 = categoryMapper.selectById(c3.getParentId());
        Category c1 = categoryMapper.selectById(c2.getParentId());
        List<Category> list = Arrays.asList(c1, c2, c3);
        if (CollectionUtils.isEmpty(list)) {
            throw new JialegouException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
