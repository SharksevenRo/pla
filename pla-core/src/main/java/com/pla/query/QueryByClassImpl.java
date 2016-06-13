package com.pla.query;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class QueryByClassImpl<T> extends QueryImpl<T> implements QueryByClass<T> {
    public QueryByClass<T> idEq(Serializable value) {
        if (value != null)
            getCriteria().add(Restrictions.idEq(value));
        return this;
    }

    public QueryByClass<T> eq(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.eq(propertyName, value));
        return this;
    }

    public QueryByClass<T> eqOrIsNull(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.eqOrIsNull(propertyName, value));
        return this;
    }

    public QueryByClass<T> ne(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.ne(propertyName, value));
        return this;
    }

    public QueryByClass<T> neOrIsNotNull(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }


    public QueryByClass<T> like(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public QueryByClass<T> startLike(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.like(propertyName, value, MatchMode.START));
        return this;
    }

    public QueryByClass<T> endLike(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.like(propertyName, value, MatchMode.END));
        return this;
    }

    public QueryByClass<T> like(String propertyName, String value, MatchMode matchMode) {
        if (value != null)
            getCriteria().add(Restrictions.like(propertyName, value, matchMode));
        return this;
    }

    public QueryByClass<T> ilike(String propertyName, String value, MatchMode matchMode) {
        if (value != null)
            getCriteria().add(Restrictions.ilike(propertyName, value, matchMode));
        return this;
    }

    public QueryByClass<T> ilike(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public QueryByClass<T> startIlike(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.ilike(propertyName, value, MatchMode.START));
        return this;
    }

    public QueryByClass<T> endIlike(String propertyName, String value) {
        if (value != null)
            getCriteria().add(Restrictions.ilike(propertyName, value, MatchMode.END));
        return this;
    }

    public QueryByClass<T> gt(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.gt(propertyName, value));
        return this;
    }

    public QueryByClass<T> lt(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.lt(propertyName, value));
        return this;
    }

    public QueryByClass<T> le(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.le(propertyName, value));
        return this;
    }

    public QueryByClass<T> ge(String propertyName, Object value) {
        if (value != null)
            getCriteria().add(Restrictions.ge(propertyName, value));
        return this;
    }


    //-------------------------- Common Where --------------------------
    @Override
    public QueryByClass<T> between(String propertyName, Object lo, Object hi) {
        return (QueryByClass<T>) super.between(propertyName, lo, hi);
    }

    @Override
    public QueryByClass<T> in(String propertyName, Object[] values) {
        return (QueryByClass<T>) super.in(propertyName, values);
    }

    @Override
    public QueryByClass<T> in(String propertyName, Collection values) {
        return (QueryByClass<T>) super.in(propertyName, values);
    }

    @Override
    public QueryByClass<T> isNull(String propertyName) {
        return (QueryByClass<T>) super.isNull(propertyName);
    }

    @Override
    public QueryByClass<T> isNotNull(String propertyName) {
        return (QueryByClass<T>) super.isNotNull(propertyName);
    }

    @Override
    public QueryByClass<T> eqProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> neProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> ltProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> leProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> gtProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> geProperty(String propertyName, String otherPropertyName) {
        return (QueryByClass<T>) super.geProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByClass<T> sqlRestriction(String sql, Object... values) {
        return (QueryByClass<T>) super.sqlRestriction(sql, values);
    }

    @Override
    public QueryByClass<T> sqlRestriction(String sql) {
        return (QueryByClass<T>) super.sqlRestriction(sql);
    }

    @Override
    public QueryByClass<T> allEq(Map propertyNameValues) {
        return (QueryByClass<T>) super.allEq(propertyNameValues);
    }

    @Override
    public QueryByClass<T> isEmpty(String propertyName) {
        return (QueryByClass<T>) super.isEmpty(propertyName);
    }

    @Override
    public QueryByClass<T> isNotEmpty(String propertyName) {
        return (QueryByClass<T>) super.isNotEmpty(propertyName);
    }

    @Override
    public QueryByClass<T> sizeEq(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeEq(propertyName, size);
    }

    @Override
    public QueryByClass<T> sizeNe(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeNe(propertyName, size);
    }

    @Override
    public QueryByClass<T> sizeGt(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeGt(propertyName, size);
    }

    @Override
    public QueryByClass<T> sizeLt(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeLt(propertyName, size);
    }

    @Override
    public QueryByClass<T> sizeGe(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeGe(propertyName, size);
    }

    @Override
    public QueryByClass<T> sizeLe(String propertyName, int size) {
        return (QueryByClass<T>) super.sizeLe(propertyName, size);
    }

    @Override
    public QueryByClass<T> or(Or or) {
        return (QueryByClass<T>) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public QueryByClass<T> on(Criterion criterion) {
        return (QueryByClass<T>) super.on(criterion);
    }

    @Override
    public QueryByClass<T> join(String associationPath) {
        return (QueryByClass<T>) super.join(associationPath);
    }

    @Override
    public QueryByClass<T> join(String associationPath, String alias) {
        return (QueryByClass<T>) super.join(associationPath, alias);
    }

    @Override
    public QueryByClass<T> leftJoin(String associationPath) {
        return (QueryByClass<T>) super.leftJoin(associationPath);
    }

    @Override
    public QueryByClass<T> leftJoin(String associationPath, String alias) {
        return (QueryByClass<T>) super.leftJoin(associationPath, alias);
    }

    @Override
    public QueryByClass<T> rightJoin(String associationPath) {
        return (QueryByClass<T>) super.rightJoin(associationPath);
    }

    @Override
    public QueryByClass<T> rightJoin(String associationPath, String alias) {
        return (QueryByClass<T>) super.rightJoin(associationPath, alias);
    }

    @Override
    public QueryByClass<T> innerJoin(String associationPath) {
        return (QueryByClass<T>) super.innerJoin(associationPath);
    }

    @Override
    public QueryByClass<T> innerJoin(String associationPath, String alias) {
        return (QueryByClass<T>) super.innerJoin(associationPath, alias);
    }

    @Override
    public QueryByClass<T> fullJoin(String associationPath) {
        return (QueryByClass<T>) super.fullJoin(associationPath);
    }

    @Override
    public QueryByClass<T> fullJoin(String associationPath, String alias) {
        return (QueryByClass<T>) super.fullJoin(associationPath, alias);
    }

    @Override
    public QueryByClass<T> batch(String property) {
        return (QueryByClass<T>) super.batch(property);
    }

    //-------------------------- OrderBy --------------------------
    @Override
    public QueryByClass<T> asc(String propertyName) {
        return (QueryByClass<T>) super.asc(propertyName);
    }

    @Override
    public QueryByClass<T> desc(String propertyName) {
        return (QueryByClass<T>) super.desc(propertyName);
    }
}
