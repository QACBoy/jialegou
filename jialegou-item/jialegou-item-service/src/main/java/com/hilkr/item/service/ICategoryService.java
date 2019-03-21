package com.hilkr.item.service;

import com.hilkr.dal.model.Category;

import java.util.List;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-21
 */
public interface ICategoryService {

    List<Category> queryCategoryByPid(Long pid);

    List<Category> queryCategoryByIds(List<Long> ids);

    List<Category> queryAllByCid3(Long id);
}