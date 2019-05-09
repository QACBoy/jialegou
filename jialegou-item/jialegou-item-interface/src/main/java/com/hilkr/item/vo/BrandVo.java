package com.hilkr.item.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandVo {

    private Long id;
    private String name;
    private String image;
    private List<Long> cids;
    private Character letter;
}
