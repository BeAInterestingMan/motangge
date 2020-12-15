package com.motang.motangge.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description mybatis-plus字段自动填充
 * @author liuhu
 * @Date 2020/12/15 16:36
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("创建时间自动填充开始........");
        this.strictInsertFill(metaObject, "createTime",LocalDateTime.class, LocalDateTime.now());
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("修改时间自动填充开始........");
        this.strictUpdateFill(metaObject, "updateTime",LocalDateTime.class, LocalDateTime.now());
}
}