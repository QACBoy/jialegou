package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 新增商品分类和品牌中间表数据
     *
     * @param cid 商品分类id
     * @param bid 品牌id
     * @return
     */
    @Insert("INSERT INTO category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);


    /**
     * 根据brand id删除中间表相关数据
     *
     * @param bid
     */
    @Delete("DELETE FROM category_brand WHERE brand_id = #{bid}")
    void deleteByBrandIdInCategoryBrand(@Param("bid") Long bid);

    /**
     * 根据category id查询brand,左连接
     *
     * @param cid
     * @return
     */
    @Select("SELECT b.* FROM brand b LEFT JOIN category_brand cb ON b.id=cb.brand_id WHERE cb.category_id=#{cid}")
    List<Brand> queryBrandByCategoryId(Long cid);

    int deleteByPrimaryKey(Long id);


    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}