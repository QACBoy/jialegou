package com.hilkr.item.service;


import com.hilkr.dal.model.Specification;


/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-24
 */
public interface ISpecificationService {

    /**
     * 根据category id查询规格参数模板
     *
     * @param id
     * @return
     */
    Specification queryById(Long id);

    /**
     * 添加规格参数模板
     *
     * @param specification
     */
    void saveSpecification(Specification specification);

    /**
     * 修改规格参数模板
     *
     * @param specification
     */
    void updateSpecification(Specification specification);

    /**
     * 删除规格参数模板
     *
     * @param specification
     */
    void deleteSpecification(Specification specification);
}
