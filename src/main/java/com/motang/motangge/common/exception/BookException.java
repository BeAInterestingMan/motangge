package com.motang.motangge.common.exception;
/**
 * @description 系统自定义异常  业务抛出
 * @author liuhu
 * @Date 2020/12/15 16:11
 */
public class BookException extends RuntimeException {
    public BookException(String message){
        super(message);
    }
}
