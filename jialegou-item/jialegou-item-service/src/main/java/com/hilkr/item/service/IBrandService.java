package com.hilkr.item.service;

import com.hilkr.common.dal.model.Brand;
import com.hilkr.common.dal.model.Category;
import com.hilkr.common.vo.PageResult;
import com.hilkr.item.vo.BrandVo;

import java.util.List;

/**
 * 描述: <br>
 * < TODO >
 *
 * @author linkr
 * @create 2019-03-21
 * @since TODO
 */
public interface IBrandService {

    PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(Brand brand, List<Long> cids);

    List<Category> queryCategoryByBid(Long bid);

    void updateBrand(BrandVo brandVo);

    void deleteBrand(Long bid);

    List<Brand> queryBrandByCid(Long cid);

    Brand queryBrandByBid(Long id);

    List<Brand> queryBrandByIds(List<Long> ids);
}
