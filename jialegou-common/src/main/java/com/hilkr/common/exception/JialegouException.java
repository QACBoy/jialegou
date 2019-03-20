package com.hilkr.common.exception;

import com.hilkr.common.enums.ExceptionEnum;
import lombok.Getter;

/***
 *
 * 描述：
 * 自定义异常类
 *
 * @author sam
 * @date 2019/3/4
 *
 */
@Getter
public class JialegouException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public JialegouException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}
