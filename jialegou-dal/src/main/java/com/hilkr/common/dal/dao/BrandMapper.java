package com.hilkr.common.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.common.dal.model.Brand;
import com.hilkr.common.dal.model.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {

    @Insert("insert into category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);


    @Select("select * from category where id in (select category_id from tb_category_brand where brand_id = #{bid})")
    List<Category> queryCategoryByBid(Long bid);

    @Delete("delete from category_brand where brand_id = #{bid}")
    int deleteCategoryBrandByBid(Long bid);

    @Select("select * from brand where id in (select brand_id from tb_category_brand where category_id = #{cid})")
    List<Brand> queryBrandByCid(Long cid);
    // int deleteByPrimaryKey(Long id);
    //
    // int insert(Brand record);
    //
    // int insertSelective(Brand record);
    //
    // Brand selectByPrimaryKey(Long id);
    //
    // int updateByPrimaryKeySelective(Brand record);
    //
    // int updateByPrimaryKey(Brand record);
}