package com.motang.motangge.handler;

import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.common.exception.BookRedisException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @description 全局异常处理
 * @author liuhu
 * @Date 2020/12/15 16:18
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * @description 处理系统内部异常
     * @author liuhu
     * @param b
     * @date 2020/12/15 16:24
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(BookException.class)
    public ResponseEntity handleBookException(BookException b){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(b.getMessage());
    }

    /**
     * @description 处理系统redis异常
     * @author liuhu
     * @param b
     * @date 2020/12/15 16:24
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(BookRedisException.class)
    public ResponseEntity handleBookRedisException(BookRedisException b){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(b.getMessage());
    }
    /**
     * @description 处理系统异常
     * @author liuhu
     * @param b
     * @date 2020/12/15 16:23
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception b){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系统异常，请联系管理员！");
    }
}
