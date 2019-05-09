package com.hilkr.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hilkr.common.parameter.pojo.BrandQueryByPageParameter;
import com.hilkr.common.vo.PageResult;
import com.hilkr.dal.dao.BrandMapper;
import com.hilkr.dal.model.Brand;
import com.hilkr.item.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询
     *
     * @param brandQueryByPageParameter
     * @return
     */
    @Override
    public PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter brandQueryByPageParameter) {

        /**
         * 1.分页
         */
        Integer page = brandQueryByPageParameter.getPage();
        Integer rows = brandQueryByPageParameter.getRows();
        if (rows == -1) {
            rows = 100;
        }
        PageHelper.startPage(page, Math.min(rows, 100));

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        String key = brandQueryByPageParameter.getKey();
        String sortBy = brandQueryByPageParameter.getSortBy();
        if (StringUtils.isNotBlank(key)) {
            queryWrapper
                    .like("name", "%" + key + "%").or()
                    .eq("letter", key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)) {
            queryWrapper.orderBy(true, !brandQueryByPageParameter.getDesc(), sortBy);
        }
        List<Brand> list = brandMapper.selectList(queryWrapper);

        /**
         * 4.创建PageInfo
         */
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        /**
         * 5.返回分页结果
         */
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 品牌新增
     *
     * @param brand
     * @param categories
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> categories) {
        //System.out.println(brand);
        // 新增品牌信息
        this.brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : categories) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 品牌更新
     *
     * @param brand
     * @param categories
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBrand(Brand brand, List<Long> categories) {
        //删除原来的数据
        deleteByBrandIdInCategoryBrand(brand.getId());

        // 修改品牌信息
        this.brandMapper.updateByPrimaryKeySelective(brand);

        //维护品牌和分类中间表
        for (Long cid : categories) {
            //System.out.println("cid:"+cid+",bid:"+brand.getId());
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 品牌删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBrand(Long id) {
        //删除品牌信息
        this.brandMapper.deleteByPrimaryKey(id);

        //维护中间表
        this.brandMapper.deleteByBrandIdInCategoryBrand(id);
    }


    /**
     * 删除中间表中的数据
     *
     * @param bid
     */
    @Override
    public void deleteByBrandIdInCategoryBrand(Long bid) {
        this.brandMapper.deleteByBrandIdInCategoryBrand(bid);
    }

    /**
     * 根据category id查询brand
     *
     * @param cid
     * @return
     */
    @Override
    public List<Brand> queryBrandByCategoryId(Long cid) {

        return this.brandMapper.queryBrandByCategoryId(cid);
    }

    /**
     * 根据品牌id集合查询品牌信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<Brand> queryBrandByBrandIds(List<Long> ids) {
        return this.brandMapper.selectBatchIds(ids);
    }

}
