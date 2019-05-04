package com.hilkr.dal.model;

import lombok.Data;

@Data
public class Category {
    private Long id;

    private String name;

    private Long parentId;

    private Boolean isParent;

    private Integer sort;
}