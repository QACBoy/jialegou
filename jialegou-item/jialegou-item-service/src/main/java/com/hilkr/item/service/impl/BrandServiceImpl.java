package com.hilkr.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hilkr.dal.dao.BrandMapper;
import com.hilkr.dal.model.Brand;
import com.hilkr.dal.model.Category;
import com.hilkr.common.enums.ExceptionEnum;
import com.hilkr.common.exception.JialegouException;
import com.hilkr.common.vo.PageResult;
import com.hilkr.item.service.IBrandService;
import com.hilkr.item.vo.BrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-03-21
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        if (rows == -1){
            rows = Integer.MAX_VALUE;
        }
        // 开启分页
        PageHelper.startPage(page, rows);
        // 过滤条件
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            queryWrapper
                    .like("name", "%" + key + "%").or()
                    .eq("letter", key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)) {
            queryWrapper.orderBy(true,!desc,sortBy);
        }
        List<Brand> brandList = brandMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(brandList)) {
            // TODO ： 缺少返回类型包装
            throw new JialegouException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);

        return new PageResult<>(pageInfo.getTotal(), brandList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        brand.setId(null);
        int resultCount = brandMapper.insert(brand);
        if (resultCount == 0) {
            throw new JialegouException(ExceptionEnum.BRAND_CREATE_FAILED);
        }
        //更新品牌分类表
        for (Long cid : cids) {
            resultCount = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (resultCount == 0) {
                throw new JialegouException(ExceptionEnum.BRAND_CREATE_FAILED);
            }
        }
    }
    //
    @Override
    public List<Category> queryCategoryByBid(Long bid) {
        return brandMapper.queryCategoryByBid(bid);
    }
    //
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBrand(BrandVo brandVo) {
        Brand brand = new Brand();
        brand.setId(brandVo.getId());
        brand.setName(brandVo.getName());
        brand.setImage(brandVo.getImage());
        brand.setLetter(brandVo.getLetter().toString());

        //更新
        int resultCount = brandMapper.updateById(brand);
        if (resultCount == 0) {
            throw new JialegouException(ExceptionEnum.UPDATE_BRAND_FAILED);
        }
        List<Long> cids = brandVo.getCids();
        //更新品牌分类表


        brandMapper.deleteCategoryBrandByBid(brandVo.getId());

        for (Long cid : cids) {
            resultCount = brandMapper.insertCategoryBrand(cid, brandVo.getId());
            if (resultCount == 0) {
                throw new JialegouException(ExceptionEnum.UPDATE_BRAND_FAILED);
            }

        }
    }
    //
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBrand(Long bid) {
        int result = brandMapper.deleteById(bid);
        if (result == 0) {
            throw new JialegouException(ExceptionEnum.DELETE_BRAND_EXCEPTION);
        }
        //删除中间表
        result = brandMapper.deleteCategoryBrandByBid(bid);
        if (result == 0) {
            throw new JialegouException(ExceptionEnum.DELETE_BRAND_EXCEPTION);
        }
    }
    //
    @Override
    public List<Brand> queryBrandByCid(Long cid) {
        List<Brand> brandList = brandMapper.queryBrandByCid(cid);
        if (CollectionUtils.isEmpty(brandList)) {
            throw new JialegouException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brandList;
    }
    //
    @Override
    public Brand queryBrandByBid(Long id) {
        // Brand brand = new Brand();
        // brand.setId(id);
        Brand b1 = brandMapper.selectById(id);
        if (b1 == null) {
            throw new JialegouException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return b1;
    }

    @Override
    public List<Brand> queryBrandByIds(List<Long> ids) {
        List<Brand> brands = brandMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(brands)) {
            throw new JialegouException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brands;
    }
}
