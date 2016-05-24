package com.pla.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Or implements Serializable {
    private static final long serialVersionUID = 696877839420002123L;

    private List<OrCriterion> orCriterionList = new ArrayList<OrCriterion>();

    public List<OrCriterion> getOrCriterionList() {
        return orCriterionList;
    }

    private void set(String expression, String propertyName) {
        if (propertyName == null)
            return;
        OrCriterion orCriterion = OrCriterion.create();
        orCriterion.setExpression(expression);
        orCriterion.setPropertyName(propertyName);
        orCriterionList.add(orCriterion);
    }

    private void set(String expression, String propertyName, Object value) {
        if (value == null)
            return;
        OrCriterion orCriterion = OrCriterion.create();
        orCriterion.setExpression(expression);
        orCriterion.setPropertyName(propertyName);
        orCriterion.setValue(value);
        orCriterionList.add(orCriterion);
    }

    private void set(String expression, String propertyName, Object[] values) {
        if (values == null)
            return;
        OrCriterion orCriterion = OrCriterion.create();
        orCriterion.setExpression(expression);
        orCriterion.setPropertyName(propertyName);
        orCriterion.setValues(values);
        orCriterionList.add(orCriterion);
    }

    private void set(String expression, String propertyName, String otherPropertyName) {
        if (propertyName == null || otherPropertyName == null)
            return;
        OrCriterion orCriterion = OrCriterion.create();
        orCriterion.setExpression(expression);
        orCriterion.setPropertyName(propertyName);
        orCriterion.setOtherPropertyName(otherPropertyName);
        orCriterionList.add(orCriterion);
    }

    public Or idEq(Serializable value) {
        set("idEq", null, value);
        return this;
    }

    public Or eq(String propertyName, Object value) {
        set("eq", propertyName, value);
        return this;
    }

    public Or eqOrIsNull(String propertyName, Object value) {
        set("eqOrIsNull", propertyName, value);
        return this;
    }

    public Or ne(String propertyName, Object value) {
        set("ne", propertyName, value);
        return this;
    }

    public Or neOrIsNotNull(String propertyName, Object value) {
        set("neOrIsNotNull", propertyName, value);
        return this;
    }


    public Or like(String propertyName, String value) {
        set("like", propertyName, value);
        return this;
    }

    public Or startLike(String propertyName, String value) {
        set("startLike", propertyName, value);
        return this;
    }

    public Or endLike(String propertyName, String value) {
        set("endLike", propertyName, value);
        return this;
    }

    public Or ilike(String propertyName, String value) {
        set("ilike", propertyName, value);
        return this;
    }

    public Or startIlike(String propertyName, String value) {
        set("startIlike", propertyName, value);
        return this;
    }

    public Or endIlike(String propertyName, String value) {
        set("endIlike", propertyName, value);
        return this;
    }

    public Or gt(String propertyName, Object value) {
        set("gt", propertyName, value);
        return this;
    }

    public Or lt(String propertyName, Object value) {
        set("lt", propertyName, value);
        return this;
    }

    public Or le(String propertyName, Object value) {
        set("le", propertyName, value);
        return this;
    }

    public Or ge(String propertyName, Object value) {
        set("ge", propertyName, value);
        return this;
    }

    public Or between(String propertyName, Object lo, Object hi) {
        Object[] values = new Object[]{lo, hi};
        set("between", propertyName, values);
        return this;
    }

    public Or in(String propertyName, Object... values) {
        set("in", propertyName, values);
        return this;
    }

    public Or isNull(String propertyName) {
        set("isNull", propertyName);
        return this;
    }

    public Or isNotNull(String propertyName) {
        set("isNotNull", propertyName);
        return this;
    }

    public Or eqProperty(String propertyName, String otherPropertyName) {
        set("eqProperty", propertyName, otherPropertyName);
        return this;
    }

    public Or neProperty(String propertyName, String otherPropertyName) {
        set("neProperty", propertyName, otherPropertyName);
        return this;
    }

    public Or ltProperty(String propertyName, String otherPropertyName) {
        set("ltProperty", propertyName, otherPropertyName);
        return this;
    }

    public Or leProperty(String propertyName, String otherPropertyName) {
        set("leProperty", propertyName, otherPropertyName);
        return this;
    }

    public Or gtProperty(String propertyName, String otherPropertyName) {
        set("gtProperty", propertyName, otherPropertyName);
        return this;
    }

    public Or geProperty(String propertyName, String otherPropertyName) {
        set("geProperty", propertyName, otherPropertyName);
        return this;
    }


    public Or sqlRestriction(String sql, Object... values) {
        OrCriterion orCriterion = OrCriterion.create();
        orCriterion.setSql(sql);
        orCriterion.setValues(values);
        orCriterionList.add(orCriterion);
        return this;
    }

    public Or sqlRestriction(String sql) {
        sqlRestriction(sql, (Object[]) null);
        return this;
    }

    public Or isEmpty(String propertyName) {
        set("isEmpty", propertyName);
        return this;
    }

    public Or isNotEmpty(String propertyName) {
        set("isNotEmpty", propertyName);
        return this;
    }

    public Or sizeEq(String propertyName, int size) {
        set("sizeEq", propertyName, size);
        return this;
    }

    public Or sizeNe(String propertyName, int size) {
        set("sizeNe", propertyName, size);
        return this;
    }

    public Or sizeGt(String propertyName, int size) {
        set("sizeGt", propertyName, size);
        return this;
    }

    public Or sizeLt(String propertyName, int size) {
        set("sizeLt", propertyName, size);
        return this;
    }

    public Or sizeGe(String propertyName, int size) {
        set("sizeGe", propertyName, size);
        return this;
    }

    public Or sizeLe(String propertyName, int size) {
        set("sizeLe", propertyName, size);
        return this;
    }
}
