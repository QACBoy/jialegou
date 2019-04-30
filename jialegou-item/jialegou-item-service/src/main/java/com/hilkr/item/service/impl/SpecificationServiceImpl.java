package com.hilkr.item.service.impl;

import com.hilkr.dal.dao.SpecificationMapper;
import com.hilkr.dal.model.Specification;
import com.hilkr.item.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-24
 */
@Service
public class SpecificationServiceImpl implements ISpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public Specification queryById(Long id) {
        return this.specificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveSpecification(Specification specification) {
        this.specificationMapper.insert(specification);
    }

    @Override
    public void updateSpecification(Specification specification) {
        this.specificationMapper.updateByPrimaryKeySelective(specification);
    }

    @Override
    public void deleteSpecification(Specification specification) {
        this.specificationMapper.deleteByPrimaryKey(specification.getCategoryId());
    }
}


