package com.hilkr.item.api;

import com.hilkr.dal.model.Category;
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

    @GetMapping("list/ids")
    List<Category> queryByIds(@RequestParam("ids") List<Long> ids);
}
