package org.zhangyibin.service;

/**
 * 保养产品枚举类
 *
 * @author zhangyibin
 */
public enum ProductEnum {

    轮胎("轮胎"), 空气滤芯("空气滤芯"), 空调滤芯("空调滤芯"),
    变速箱油("变速箱油"), 防冻液("防冻液"), 火花塞("火花塞"),
    汽油滤芯("汽油滤芯"), 刹车油("刹车油"), 机油("机油");

    private String productEnum = "";

    private ProductEnum(String productEnum) {
        this.productEnum = productEnum;

    }

    public String getProductEnum() {
        return this.productEnum;

    }
}
