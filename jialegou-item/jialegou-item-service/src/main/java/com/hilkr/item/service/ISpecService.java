package com.hilkr.item.service;

import com.hilkr.dal.model.SpecGroup;
import com.hilkr.dal.model.SpecParam;

import java.util.List;


/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-24
 */
public interface ISpecService {

    List<SpecGroup> querySpecGroupByCid(Long cid);

    void saveSpecGroup(SpecGroup specGroup);

    void deleteSpecGroup(Long id);

    void updateSpecGroup(SpecGroup specGroup);

    List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic);

    void saveSpecParam(SpecParam specParam);

    void updateSpecParam(SpecParam specParam);

    void deleteSpecParam(Long id);

    List<SpecGroup> querySpecsByCid(Long cid);
}
