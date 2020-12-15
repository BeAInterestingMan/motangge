package com.motang.motangge.common.exception;

/**
 * @author liuhu
 * @description redis自定义异常
 * @Date 2020/12/15 16:11
 */
public class BookRedisException extends RuntimeException {
    public BookRedisException(String message) {
        super(message);
    }
}
