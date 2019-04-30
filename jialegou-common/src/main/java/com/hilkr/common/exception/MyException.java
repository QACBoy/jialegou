package com.hilkr.common.exception;

/**
 * @Author: 98050
 * @Time: 2018-11-05 16:07
 * @Feature:
 */
public class MyException extends RuntimeException {

    public MyException(JialegouException exception) {
        super(exception.toString());
    }
}
