package com.pla.dao;


import java.io.Serializable;

public class OrCriterion implements Serializable {
    private static final long serialVersionUID = 6842333904624393842L;

    private String expression;
    private String propertyName;
    private String otherPropertyName;
    private Object value;
    private Object[] values;
    private String sql;

    public static OrCriterion create(){
        return new OrCriterion();
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
}
