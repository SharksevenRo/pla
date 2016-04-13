package com.pla.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public interface QueryByModel<T> extends QueryByClass<T> {
    //-------------------------- Where --------------------------
    QueryByModel<T> idEq();

    QueryByModel<T> eq(String propertyName);

    QueryByModel<T> eqOrIsNull(String propertyName);

    QueryByModel<T> ne(String propertyName);

    QueryByModel<T> neOrIsNotNull(String propertyName);

    QueryByModel<T> like(String propertyName);

    QueryByModel<T> startLike(String propertyName);

    QueryByModel<T> endLike(String propertyName);

    QueryByModel<T> like(String propertyName, MatchMode matchMode);

    QueryByModel<T> ilike(String propertyName, MatchMode matchMode);

    QueryByModel<T> ilike(String propertyName);

    QueryByModel<T> startIlike(String propertyName);

    QueryByModel<T> endIlike(String propertyName);

    QueryByModel<T> gt(String propertyName);

    QueryByModel<T> lt(String propertyName);

    QueryByModel<T> le(String propertyName);

    QueryByModel<T> ge(String propertyName);

    //--------------------------By Class Where --------------------------
    @Override
    QueryByModel<T> idEq(Serializable id);

    @Override
    QueryByModel<T> eq(String propertyName, Object value);

    @Override
    QueryByModel<T> eqOrIsNull(String propertyName, Object value);

    @Override
    QueryByModel<T> ne(String propertyName, Object value);

    @Override
    QueryByModel<T> neOrIsNotNull(String propertyName, Object value);

    @Override
    QueryByModel<T> like(String propertyName, String value);

    @Override
    QueryByModel<T> startLike(String propertyName, String value);

    @Override
    QueryByModel<T> endLike(String propertyName, String value);

    @Override
    QueryByModel<T> like(String propertyName, String value, MatchMode matchMode);

    @Override
    QueryByModel<T> ilike(String propertyName, String value, MatchMode matchMode);

    @Override
    QueryByModel<T> ilike(String propertyName, String value);

    @Override
    QueryByModel<T> startIlike(String propertyName, String value);

    @Override
    QueryByModel<T> endIlike(String propertyName, String value);

    @Override
    QueryByModel<T> gt(String propertyName, Object value);

    @Override
    QueryByModel<T> lt(String propertyName, Object value);

    @Override
    QueryByModel<T> le(String propertyName, Object value);

    @Override
    QueryByModel<T> ge(String propertyName, Object value);

    //--------------------------Common Where --------------------------
    @Override
    QueryByModel<T> between(String propertyName, Object lo, Object hi);

    @Override
    QueryByModel<T> in(String propertyName, Object[] values);

	@Override
    QueryByModel<T> in(String propertyName, Collection values);

    @Override
    QueryByModel<T> isNull(String propertyName);

    @Override
    QueryByModel<T> isNotNull(String propertyName);

    @Override
    QueryByModel<T> eqProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> neProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> ltProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> leProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> gtProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> geProperty(String propertyName, String otherPropertyName);

    @Override
    QueryByModel<T> sqlRestriction(String sql, Object... values);

    @Override
    QueryByModel<T> sqlRestriction(String sql);

    @Override
    QueryByModel<T> allEq(Map propertyNameValues);

    @Override
    QueryByModel<T> isEmpty(String propertyName);

    @Override
    QueryByModel<T> isNotEmpty(String propertyName);

    @Override
    QueryByModel<T> sizeEq(String propertyName, int size);

    @Override
    QueryByModel<T> sizeNe(String propertyName, int size);

    @Override
    QueryByModel<T> sizeGt(String propertyName, int size);

    @Override
    QueryByModel<T> sizeLt(String propertyName, int size);

    @Override
    QueryByModel<T> sizeGe(String propertyName, int size);

    @Override
    QueryByModel<T> sizeLe(String propertyName, int size);

    @Override
    QueryByModel<T> or(Or or);

    //-------------------------- Join --------------------------
    @Override
    QueryByModel<T> on(Criterion criterion);

    @Override
    QueryByModel<T> join(String associationPath);

	@Override
    QueryByModel<T> join(String associationPath, String alias);

    @Override
    QueryByModel<T> leftJoin(String associationPath);

    @Override
    QueryByModel<T> leftJoin(String associationPath, String alias);

    @Override
    QueryByModel<T> rightJoin(String associationPath);

    @Override
    QueryByModel<T> rightJoin(String associationPath, String alias);

    @Override
    QueryByModel<T> innerJoin(String associationPath);

    @Override
    QueryByModel<T> innerJoin(String associationPath, String alias);

    @Override
    QueryByModel<T> fullJoin(String associationPath);

    @Override
    QueryByModel<T> fullJoin(String associationPath, String alias);

    //-------------------------- OrderBy --------------------------
    @Override
    QueryByModel<T> asc(String propertyName);

    @Override
    QueryByModel<T> desc(String propertyName);
}
