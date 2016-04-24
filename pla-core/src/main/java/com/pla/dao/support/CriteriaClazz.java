package com.pla.dao.support;

import com.pla.query.Or;
import org.hibernate.sql.JoinType;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class CriteriaClazz extends Criteria {
    public CriteriaClazz idEq(Serializable value) {
            add("idEq", null, value);
        return this;
    }

    public CriteriaClazz eq(String propertyName, Object value) {
        if (value != null)
            add("eq", propertyName, value);
        return this;
    }

    public CriteriaClazz eqOrIsNull(String propertyName, Object value) {
        if (value != null)
            add("eqOrIsNull", propertyName, value);
        return this;
    }

    public CriteriaClazz ne(String propertyName, Object value) {
        if (value != null)
            add("ne", propertyName, value);
        return this;
    }

    public CriteriaClazz neOrIsNotNull(String propertyName, Object value) {
        if (value != null)
            add("neOrIsNotNull", propertyName, value);
        return this;
    }


    public CriteriaClazz like(String propertyName, String value) {
        if (value != null)
            add("like", propertyName, value);
        return this;
    }

    public CriteriaClazz startLike(String propertyName, String value) {
        if (value != null)
            add("startLike", propertyName, value);
        return this;
    }

    public CriteriaClazz endLike(String propertyName, String value) {
        if (value != null)
            add("endLike", propertyName, value);
        return this;
    }

    public CriteriaClazz ilike(String propertyName, String value) {
        if (value != null)
            add("ilike", propertyName, value);
        return this;
    }

    public CriteriaClazz startIlike(String propertyName, String value) {
        if (value != null)
            add("startIlike", propertyName, value);
        return this;
    }

    public CriteriaClazz endIlike(String propertyName, String value) {
        if (value != null)
            add("endIlike", propertyName, value);
        return this;
    }

    public CriteriaClazz gt(String propertyName, Object value) {
        if (value != null)
            add("endIlike", propertyName, value);
        return this;
    }

    public CriteriaClazz lt(String propertyName, Object value) {
        if (value != null)
            add("lt", propertyName, value);
        return this;
    }

    public CriteriaClazz le(String propertyName, Object value) {
        if (value != null)
            add("le", propertyName, value);
        return this;
    }

    public CriteriaClazz ge(String propertyName, Object value) {
        if (value != null)
            add("ge", propertyName, value);
        return this;
    }

    public CriteriaClazz between(String propertyName, Object lo, Object hi) {
        if (lo != null && hi != null)
            add("between", propertyName, new Object[]{lo, hi});
        return this;
    }

    public CriteriaClazz in(String propertyName, Object[] values) {
        if (values != null)
            add("in", propertyName, values);
        return this;
    }

	public CriteriaClazz in(String propertyName, Collection values) {
        if (values != null)
            add("in", propertyName, values);
        return this;
    }

    public CriteriaClazz isNull(String propertyName) {
        add("isNull", propertyName, null);
        return this;
    }

    public CriteriaClazz isNotNull(String propertyName) {
        add("isNotNull", propertyName, null);
        return this;
    }

    public CriteriaClazz eqProperty(String propertyName, String otherPropertyName) {
        add("eqProperty", propertyName, otherPropertyName);
        return this;
    }

    public CriteriaClazz neProperty(String propertyName, String otherPropertyName) {
        add("neProperty", propertyName, otherPropertyName);
        return this;
    }

    public CriteriaClazz ltProperty(String propertyName, String otherPropertyName) {
        add("ltProperty", propertyName, otherPropertyName);
        return this;
    }

    public CriteriaClazz leProperty(String propertyName, String otherPropertyName) {
        add("leProperty", propertyName, otherPropertyName);
        return this;
    }

    public CriteriaClazz gtProperty(String propertyName, String otherPropertyName) {
        add("gtProperty", propertyName, otherPropertyName);
        return this;
    }

    public CriteriaClazz geProperty(String propertyName, String otherPropertyName) {
        add("geProperty", propertyName, otherPropertyName);
        return this;
    }


    public CriteriaClazz sqlRestriction(String sql, Object... values) {
        if (values != null)
            add("sqlRestriction", sql, values);
        return this;
    }

    public CriteriaClazz sqlRestriction(String sql) {
        add("sqlRestriction", sql, null);
        return this;
    }

    public CriteriaClazz allEq(Map propertyNameValues) {
        add("allEq", null, propertyNameValues);
        return this;
    }

    public CriteriaClazz isEmpty(String propertyName) {
        add("isEmpty", propertyName, null);
        return this;
    }

    public CriteriaClazz isNotEmpty(String propertyName) {
        add("isNotEmpty", propertyName, null);
        return this;
    }

    public CriteriaClazz sizeEq(String propertyName, int size) {
        add("sizeEq", propertyName, size);
        return this;
    }

    public CriteriaClazz sizeNe(String propertyName, int size) {
        add("sizeNe", propertyName, size);
        return this;
    }

    public CriteriaClazz sizeGt(String propertyName, int size) {
        add("sizeGt", propertyName, size);
        return this;
    }

    public CriteriaClazz sizeLt(String propertyName, int size) {
        add("sizeGt", propertyName, size);
        return this;
    }

    public CriteriaClazz sizeGe(String propertyName, int size) {
        add("sizeGe", propertyName, size);
        return this;
    }

    public CriteriaClazz sizeLe(String propertyName, int size) {
        add("sizeLe", propertyName, size);
        return this;
    }

    public CriteriaClazz or(Or or) {
        add("or", null, or);
        return this;
    }

    //-------------------------- Join --------------------------
    public CriteriaClazz join(String associationPath) {
        return this.join(associationPath, associationPath);
    }

    public CriteriaClazz join(String associationPath, String alias) {
        return this.leftJoin(associationPath, alias);
    }

    public CriteriaClazz leftJoin(String associationPath) {
        return this.leftJoin(associationPath, associationPath);
    }

    public CriteriaClazz leftJoin(String associationPath, String alias) {
        addJoin(associationPath, alias, JoinType.LEFT_OUTER_JOIN);
        return this;
    }

    public CriteriaClazz rightJoin(String associationPath) {
        return this.rightJoin(associationPath, associationPath);
    }

    public CriteriaClazz rightJoin(String associationPath, String alias) {
        addJoin(associationPath, alias, JoinType.RIGHT_OUTER_JOIN);
        return this;
    }

    public CriteriaClazz innerJoin(String associationPath) {
        return this.innerJoin(associationPath, associationPath);
    }

    public CriteriaClazz innerJoin(String associationPath, String alias) {
        addJoin(associationPath, alias, JoinType.INNER_JOIN);
        return this;
    }

    public CriteriaClazz fullJoin(String associationPath) {
        return this.fullJoin(associationPath, associationPath);
    }

    public CriteriaClazz fullJoin(String associationPath, String alias) {
        addJoin(associationPath, alias, JoinType.FULL_JOIN);
        return this;
    }


    //-------------------------- OrderBy --------------------------
    public CriteriaClazz asc(String propertyName) {
        addOrderBy(propertyName, Order.ASC);
        return this;
    }

    public CriteriaClazz desc(String propertyName) {
        addOrderBy(propertyName, Order.DESC);
        return this;
    }

    //-------------------------- GroupBy --------------------------
    public CriteriaClazz groupBy(String propertyName) {
        addGroupBy("groupBy", propertyName, null);
        return this;
    }

    public CriteriaClazz countAll() {
        this.countAll(null);
        return this;
    }

    public CriteriaClazz countAll(String alias) {
        addGroupBy("countAll", null, alias);
        return this;
    }

    public CriteriaClazz count(String propertyName) {
        this.count(propertyName, null);
        return this;
    }

    public CriteriaClazz count(String propertyName, String alias) {
        addGroupBy("count", propertyName, alias);
        return this;
    }

    public CriteriaClazz max(String propertyName) {
        this.max(propertyName, null);
        return this;
    }

    public CriteriaClazz max(String propertyName, String alias) {
        addGroupBy("max", propertyName, alias);
        return this;
    }

    public CriteriaClazz min(String propertyName) {
        this.min(propertyName, null);
        return this;
    }

    public CriteriaClazz min(String propertyName, String alias) {
        addGroupBy("min", propertyName, alias);
        return this;
    }

    public CriteriaClazz sum(String propertyName) {
        this.sum(propertyName, null);
        return this;
    }

    public CriteriaClazz sum(String propertyName, String alias) {
        addGroupBy("sum", propertyName, alias);
        return this;
    }

    public CriteriaClazz avg(String propertyName) {
        this.avg(propertyName, null);
        return this;
    }

    public CriteriaClazz avg(String propertyName, String alias) {
        addGroupBy("avg", propertyName, alias);
        return this;
    }

    public CriteriaClazz distinct(String... properties) {
        addGroupBy("distinct", null, properties);
        return this;
    }

    /*public CriteriaClazz distinct() {
        addGroupBy("distinct", null, null);
        return this;
    }*/

}
