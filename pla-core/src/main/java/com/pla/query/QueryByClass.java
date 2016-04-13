package com.pla.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public interface QueryByClass<T> extends Query<T> {
    //-------------------------- Where --------------------------
    QueryByClass<T> idEq(Serializable id);

    QueryByClass<T> eq(String propertyName, Object value);

    QueryByClass<T> eqOrIsNull(String propertyName, Object value);

    QueryByClass<T> ne(String propertyName, Object value);

    QueryByClass<T> neOrIsNotNull(String propertyName, Object value);

    QueryByClass<T> like(String propertyName, String value);

    QueryByClass<T> startLike(String propertyName, String value);

    QueryByClass<T> endLike(String propertyName, String value);

    QueryByClass<T> like(String propertyName, String value, MatchMode matchMode);

    QueryByClass<T> ilike(String propertyName, String value, MatchMode matchMode);

    QueryByClass<T> ilike(String propertyName, String value);

    QueryByClass<T> startIlike(String propertyName, String value);

    QueryByClass<T> endIlike(String propertyName, String value);

    QueryByClass<T> gt(String propertyName, Object value);

    QueryByClass<T> lt(String propertyName, Object value);

    QueryByClass<T> le(String propertyName, Object value);

    QueryByClass<T> ge(String propertyName, Object value);

    //--------------------------Common Where --------------------------
    @Override
    QueryByClass<T> between(String propertyName, Object lo, Object hi);
    @Override
    QueryByClass<T> in(String propertyName, Object[] values);
	@Override
    QueryByClass<T> in(String propertyName, Collection values);
    @Override
    QueryByClass<T> isNull(String propertyName);
    @Override
    QueryByClass<T> isNotNull(String propertyName);
    @Override
    QueryByClass<T> eqProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> neProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> ltProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> leProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> gtProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> geProperty(String propertyName, String otherPropertyName);
    @Override
    QueryByClass<T> sqlRestriction(String sql, Object... values);
    @Override
    QueryByClass<T> sqlRestriction(String sql);
    @Override
    QueryByClass<T> allEq(Map propertyNameValues);
    @Override
    QueryByClass<T> isEmpty(String propertyName);
    @Override
    QueryByClass<T> isNotEmpty(String propertyName);
    @Override
    QueryByClass<T> sizeEq(String propertyName, int size);
    @Override
    QueryByClass<T> sizeNe(String propertyName, int size);
    @Override
    QueryByClass<T> sizeGt(String propertyName, int size);
    @Override
    QueryByClass<T> sizeLt(String propertyName, int size);
    @Override
    QueryByClass<T> sizeGe(String propertyName, int size);
    @Override
    QueryByClass<T> sizeLe(String propertyName, int size);
    @Override
    QueryByClass<T> or(Or or);

    //-------------------------- Join --------------------------
    @Override
    QueryByClass<T> on(Criterion criterion);
    @Override
    QueryByClass<T> join(String associationPath);
	@Override
    QueryByClass<T> join(String associationPath, String alias);
    @Override
    QueryByClass<T> leftJoin(String associationPath);
    @Override
    QueryByClass<T> leftJoin(String associationPath, String alias);
    @Override
    QueryByClass<T> rightJoin(String associationPath);
    @Override
    QueryByClass<T> rightJoin(String associationPath, String alias);
    @Override
    QueryByClass<T> innerJoin(String associationPath);
    @Override
    QueryByClass<T> innerJoin(String associationPath, String alias);
    @Override
    QueryByClass<T> fullJoin(String associationPath);
    @Override
    QueryByClass<T> fullJoin(String associationPath, String alias);

    //-------------------------- OrderBy --------------------------
    @Override
    QueryByClass<T> asc(String propertyName);
    @Override
    QueryByClass<T> desc(String propertyName);
}
