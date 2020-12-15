package com.motang.motangge.common.commonEnum;
/**
 * @Description wrapper 枚举
 * @author liuhu
 * @Date 2020/12/15 20:55
 */
public enum  WrapperEnum {
    NAME("name","NAME");

    ;
    private String column;

    private String property;



    WrapperEnum(String column, String property) {
        this.column = column;
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public String getProperty() {
        return property;
    }
}
