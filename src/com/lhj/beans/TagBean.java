package com.lhj.beans;

import java.io.Serializable;

/**
 * Created by Songzhihang on 2018/2/25.
 * 栏目栏栏目的实体
 */
public class TagBean implements Serializable{

    private int order;

    private int typeId;

    private String Value;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
