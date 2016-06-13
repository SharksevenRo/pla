package com.pla.query;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface Query<T> extends QueryExcutor<T> {
    //-------------------------- Where --------------------------
    Query<T> between(String propertyName, Object lo, Object hi);

    Query<T> in(String propertyName, Object[] values);

    Query<T> in(String propertyName, Collection values);

    Query<T> isNull(String propertyName);

    Query<T> isNotNull(String propertyName);

    Query<T> eqProperty(String propertyName, String otherPropertyName);

    Query<T> neProperty(String propertyName, String otherPropertyName);

    Query<T> ltProperty(String propertyName, String otherPropertyName);

    Query<T> leProperty(String propertyName, String otherPropertyName);

    Query<T> gtProperty(String propertyName, String otherPropertyName);

    Query<T> geProperty(String propertyName, String otherPropertyName);

    Query<T> sqlRestriction(String sql, Object... values);

    Query<T> sqlRestriction(String sql);

    Query<T> allEq(Map propertyNameValues);

    Query<T> isEmpty(String propertyName);

    Query<T> isNotEmpty(String propertyName);

    Query<T> sizeEq(String propertyName, int size);

    Query<T> sizeNe(String propertyName, int size);

    Query<T> sizeGt(String propertyName, int size);

    Query<T> sizeLt(String propertyName, int size);

    Query<T> sizeGe(String propertyName, int size);

    Query<T> sizeLe(String propertyName, int size);

    Query<T> or(Or or);

    //-------------------------- Join --------------------------
    Query<T> on(Criterion criterion);

    <Q extends Query<T>> Q join(String associationPath);

    <Q extends Query<T>> Q join(String associationPath, String alias);

    Query<T> leftJoin(String associationPath);

    Query<T> leftJoin(String associationPath, String alias);

    Query<T> rightJoin(String associationPath);

    Query<T> rightJoin(String associationPath, String alias);

    Query<T> innerJoin(String associationPath);

    Query<T> innerJoin(String associationPath, String alias);

    Query<T> fullJoin(String associationPath);

    Query<T> fullJoin(String associationPath, String alias);

    Query<T> batch(String property);

    //-------------------------- OrderBy --------------------------
    Query<T> asc(String propertyName);

    Query<T> desc(String propertyName);


    //-------------------------- QueryOp --------------------------
    T load(Serializable id);

    T first();

    List<T> list();

    List<T> list(int offset, int size);

    int count();

    T uniqueResult();

    Pager<T> pager(int pageNo, int pageSize);
}
