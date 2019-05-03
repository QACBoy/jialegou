package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据品牌id查询商品分类
     * @param bid
     * @return
     */
    @Select("SELECT * FROM category WHERE id IN (SELECT category_id FROM category_brand WHERE brand_id = #{bid}) ")
    List<Category> queryByBrandId(@Param("bid") Long bid);


    /**
     * 根据category id删除中间表相关数据
     * @param cid
     */
    @Delete("DELETE FROM category_brand WHERE category_id = #{cid}")
    void deleteByCategoryIdInCategoryBrand(@Param("cid") Long cid);

    /**
     * 根据id查名字
     * @param id
     * @return
     */
    @Select("SELECT name FROM category WHERE id = #{id}")
    String queryNameById(Long id);

    /**
     * 查询最后一条数据
     * @return
     */
    @Select("SELECT * FROM `category` WHERE id = (SELECT MAX(id) FROM category)")
    List<Category> selectLast();
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}