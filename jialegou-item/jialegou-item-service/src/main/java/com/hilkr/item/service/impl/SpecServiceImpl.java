package com.hilkr.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hilkr.common.enums.ExceptionEnum;
import com.hilkr.common.exception.JialegouException;
import com.hilkr.dal.dao.SpecGroupMapper;
import com.hilkr.dal.dao.SpecParamMapper;
import com.hilkr.dal.model.SpecGroup;
import com.hilkr.dal.model.SpecParam;
import com.hilkr.item.service.ISpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-24
 */
@Service
public class SpecServiceImpl implements ISpecService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    @Override
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        List<SpecGroup> specGroupList = specGroupMapper.selectList(new QueryWrapper<SpecGroup>().eq("cid",cid));
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new JialegouException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return specGroupList;
    }

    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.insert(specGroup);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.SPEC_GROUP_CREATE_FAILED);
        }
    }

    @Override
    public void deleteSpecGroup(Long id) {
        if (id == null) {
            throw new JialegouException(ExceptionEnum.INVALID_PARAM);
        }
        int count = specGroupMapper.deleteById(id);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.DELETE_SPEC_GROUP_FAILED);
        }
    }

    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.updateById(specGroup);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.UPDATE_SPEC_GROUP_FAILED);
        }
    }


    @Override
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        List<SpecParam> specParamList = specParamMapper.selectList(new QueryWrapper<SpecParam>().setEntity(specParam));
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new JialegouException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return specParamList;
    }

    @Override
    public void saveSpecParam(SpecParam specParam) {
        int count = specParamMapper.insert(specParam);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.SPEC_PARAM_CREATE_FAILED);
        }
    }

    @Override
    public void updateSpecParam(SpecParam specParam) {
        // int count = specParamMapper.updateByPrimaryKeySelective(specParam);
        // if (count != 1) {
        //     throw new JialegouException(ExceptionEnum.UPDATE_SPEC_PARAM_FAILED);
        // }
        return;
    }

    @Override
    public void deleteSpecParam(Long id) {
        if (id == null) {
            throw new JialegouException(ExceptionEnum.INVALID_PARAM);
        }
        int count = specParamMapper.deleteById(id);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.DELETE_SPEC_PARAM_FAILED);
        }
    }

    @Override
    public List<SpecGroup> querySpecsByCid(Long cid) {
        List<SpecGroup> specGroups = querySpecGroupByCid(cid);

        List<SpecParam> specParams = querySpecParams(null, cid, null, null);

        Map<Long, List<SpecParam>> map = new HashMap<>();
        //遍历specParams
        for (SpecParam param : specParams) {
            Long groupId = param.getGroupId();
            if (!map.keySet().contains(param.getGroupId())) {
                //map中key不包含这个组ID
                map.put(param.getGroupId(), new ArrayList<>());
            }
            //添加进map中
            map.get(param.getGroupId()).add(param);
        }

        // for (SpecGroup specGroup : specGroups) {
        //     specGroup.setParams(map.get(specGroup.getId()));
        // }

        return specGroups;
    }
}


