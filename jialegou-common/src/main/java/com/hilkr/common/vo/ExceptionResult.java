package com.hilkr.common.vo;

import com.hilkr.common.enums.ExceptionEnum;
import lombok.Data;

/***
 *
 * 描述：
 * 自定义异常结果类
 *
 * @author sam
 * @date 2019/3/4
 *
 */
@Data
public class ExceptionResult {

    private int status;

    private String message;

    private long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.value();
        this.message = em.message();
        this.timestamp = System.currentTimeMillis();
    }
}
