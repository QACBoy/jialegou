package com.hilkr.item.api;

import com.hilkr.dal.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RequestMapping("category")
public interface CategoryApi {

    /**
     * 根据id，查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids);

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("all")
    ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids")List<Long> ids);
}
