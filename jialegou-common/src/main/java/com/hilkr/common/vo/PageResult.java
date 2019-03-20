package com.hilkr.common.vo;


import lombok.Data;

import java.util.List;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2019/3/4
 *
 */
@Data
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }


}
