package com.pla.dao.support;

/**
 * Created by machey on 2016/3/29.
 */
public class Criterion {
    String expression;
    String propertyName;
    Object value;

    public Criterion(String expression, String propertyName, Object value) {
        this.expression = expression;
        this.propertyName = propertyName;
        this.value = value;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
