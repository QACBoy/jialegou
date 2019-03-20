package com.hilkr.common.advice;

import com.hilkr.common.exception.JialegouException;
import com.hilkr.common.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 *
 * 描述：
 * 自定义异常处理
 *
 * @author sam
 * @date 2019/3/4
 *
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(JialegouException.class)
    public ResponseEntity<ExceptionResult> handleException(JialegouException e) {
        return ResponseEntity.status(e.getExceptionEnum().value())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
