package com.pla.dao;

import java.io.Serializable;

public class Criterion implements Serializable {
    private static final long serialVersionUID = 4454416160620435837L;
    private String expression;
    private String propertyName;
    private String otherPropertyName;
    private Object value;
    private Object[] values;
    private String sql;
    private Or or;
    private String alias;
    private String joinEx;
    private String orderByEx;
    private String groupByEx;
    private String[] propertyNames;

    public static Criterion create() {
        return new Criterion();
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

    public String getOtherPropertyName() {
        return otherPropertyName;
    }

    public void setOtherPropertyName(String otherPropertyName) {
        this.otherPropertyName = otherPropertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getJoinEx() {
        return joinEx;
    }

    public void setJoinEx(String joinEx) {
        this.joinEx = joinEx;
    }

    public String getOrderByEx() {
        return orderByEx;
    }

    public void setOrderByEx(String orderByEx) {
        this.orderByEx = orderByEx;
    }

    public String getGroupByEx() {
        return groupByEx;
    }

    public void setGroupByEx(String groupByEx) {
        this.groupByEx = groupByEx;
    }

    public String[] getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(String[] propertyNames) {
        this.propertyNames = propertyNames;
    }

    public Or getOr() {
        return or;
    }

    public void setOr(Or or) {
        this.or = or;
    }
}
