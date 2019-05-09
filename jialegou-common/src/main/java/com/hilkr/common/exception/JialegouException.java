package com.hilkr.common.exception;

import com.hilkr.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Getter
public class JialegouException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public JialegouException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}
