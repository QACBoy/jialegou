package com.hilkr.item.api;

import com.hilkr.dal.model.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@RequestMapping("brand")
public interface BrandApi {
    /**
     * 根据品牌id集合，查询品牌信息
     *
     * @param ids
     * @return
     */
    @GetMapping("list")
    List<Brand> queryBrandByIds(@RequestParam("ids") List<Long> ids);
}
