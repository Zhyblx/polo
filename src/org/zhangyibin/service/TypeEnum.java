package org.zhangyibin.service;

/**
 * 保养产品维度枚举类<br>
 * <p>
 * 维度包括：时间和里程
 *
 * @author zhangyibin
 */
public enum TypeEnum {

    时间("time"),
    里程("mileage");

    private String typeEnum = "";

    private TypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;

    }

    public String getTypeEnum() {
        return this.typeEnum;

    }
}
