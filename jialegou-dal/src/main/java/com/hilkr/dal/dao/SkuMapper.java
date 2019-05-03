package com.hilkr.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.dal.model.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 根据id查询sku信息
     * @param id
     * @return
     */
    @Select("SELECT a.*,b.stock FROM sku a,stock b WHERE a.id=b.sku_id AND a.spu_id=#{id}")
    List<Sku> queryById(@Param("id") Long id);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);
}