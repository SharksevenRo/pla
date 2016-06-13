package com.pla.query;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract  class QueryByModelImpl<T> extends QueryByClassImpl<T> implements QueryByModel<T> {
    private Object getValue(String propertyName) {
        try {
            return SimplePropertyUtil.getProperty(t, propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public QueryByModel<T> idEq() {
        Serializable id = ModelUtil.getIdValue(t);
        if (id != null)
            getCriteria().add(Restrictions.idEq(id));
        return this;
    }

    public QueryByModel<T> eq(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.eq(propertyName, value));
        return this;
    }

    public QueryByModel<T> eqOrIsNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.eqOrIsNull(propertyName, value));
        return this;
    }

    public QueryByModel<T> ne(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.ne(propertyName, value));
        return this;
    }

    public QueryByModel<T> neOrIsNotNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }

    public QueryByModel<T> like(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.like(propertyName, value.toString(), MatchMode.ANYWHERE));
        }
        return this;
    }

    public QueryByModel<T> startLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.like(propertyName, value.toString(), MatchMode.START));
        }
        return this;
    }

    public QueryByModel<T> endLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.like(propertyName, value.toString(), MatchMode.END));
        }
        return this;
    }

    public QueryByModel<T> like(String propertyName, MatchMode matchMode) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.like(propertyName, value.toString(), matchMode));
        }
        return this;
    }

    public QueryByModel<T> ilike(String propertyName, MatchMode matchMode) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.ilike(propertyName, value.toString(), matchMode));
        }
        return this;
    }

    public QueryByModel<T> ilike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.ilike(propertyName, value.toString(), MatchMode.ANYWHERE));
        }
        return this;
    }

    public QueryByModel<T> startIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.ilike(propertyName, value.toString(), MatchMode.START));
        }
        return this;
    }

    public QueryByModel<T> endIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().add(Restrictions.ilike(propertyName, value.toString(), MatchMode.END));
        }
        return this;
    }

    public QueryByModel<T> gt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.gt(propertyName, value));
        return this;
    }

    public QueryByModel<T> lt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.lt(propertyName, value));
        return this;
    }

    public QueryByModel<T> le(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.le(propertyName, value));
        return this;
    }

    public QueryByModel<T> ge(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().add(Restrictions.ge(propertyName, value));
        return this;
    }


    //-------------------------- By Class Where --------------------------
    @Override
    public QueryByModel<T> idEq(Serializable value) {
        return (QueryByModel<T>) super.idEq(value);
    }

    @Override
    public QueryByModel<T> eq(String propertyName, Object value) {
        return (QueryByModel<T>) super.eq(propertyName, value);
    }

    @Override
    public QueryByModel<T> eqOrIsNull(String propertyName, Object value) {
        return (QueryByModel<T>) super.eqOrIsNull(propertyName, value);
    }

    @Override
    public QueryByModel<T> ne(String propertyName, Object value) {
        return (QueryByModel<T>) super.ne(propertyName, value);
    }

    @Override
    public QueryByModel<T> neOrIsNotNull(String propertyName, Object value) {
        return (QueryByModel<T>) super.neOrIsNotNull(propertyName, value);
    }

    @Override
    public QueryByModel<T> like(String propertyName, String value) {
        return (QueryByModel<T>) super.like(propertyName, value);
    }

    @Override
    public QueryByModel<T> startLike(String propertyName, String value) {
        return (QueryByModel<T>) super.startLike(propertyName, value);
    }

    @Override
    public QueryByModel<T> endLike(String propertyName, String value) {
        return (QueryByModel<T>) super.endLike(propertyName, value);
    }

    @Override
    public QueryByModel<T> like(String propertyName, String value, MatchMode matchMode) {
        return (QueryByModel<T>) super.like(propertyName, value, matchMode);
    }

    @Override
    public QueryByModel<T> ilike(String propertyName, String value, MatchMode matchMode) {
        return (QueryByModel<T>) super.like(propertyName, value, matchMode);
    }

    @Override
    public QueryByModel<T> ilike(String propertyName, String value) {
        return (QueryByModel<T>) super.ilike(propertyName, value);
    }

    @Override
    public QueryByModel<T> startIlike(String propertyName, String value) {
        return (QueryByModel<T>) super.startIlike(propertyName, value);
    }

    @Override
    public QueryByModel<T> endIlike(String propertyName, String value) {
        return (QueryByModel<T>) super.endIlike(propertyName, value);
    }

    @Override
    public QueryByModel<T> gt(String propertyName, Object value) {
        return (QueryByModel<T>) super.gt(propertyName, value);
    }

    @Override
    public QueryByModel<T> lt(String propertyName, Object value) {
        return (QueryByModel<T>) super.lt(propertyName, value);
    }

    @Override
    public QueryByModel<T> le(String propertyName, Object value) {
        return (QueryByModel<T>) super.le(propertyName, value);
    }

    @Override
    public QueryByModel<T> ge(String propertyName, Object value) {
        return (QueryByModel<T>) super.ge(propertyName, value);
    }

    //-------------------------- Common Where --------------------------
    @Override
    public QueryByModel<T> between(String propertyName, Object lo, Object hi) {
        return (QueryByModel<T>) super.between(propertyName, lo, hi);
    }

    @Override
    public QueryByModel<T> in(String propertyName, Object[] values) {
        return (QueryByModel<T>) super.in(propertyName, values);
    }

	@Override
    public QueryByModel<T> in(String propertyName, Collection values) {
        return (QueryByModel<T>) super.in(propertyName, values);
    }

    @Override
    public QueryByModel<T> isNull(String propertyName) {
        return (QueryByModel<T>) super.isNull(propertyName);
    }

    @Override
    public QueryByModel<T> isNotNull(String propertyName) {
        return (QueryByModel<T>) super.isNotNull(propertyName);
    }

    @Override
    public QueryByModel<T> eqProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> neProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> ltProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> leProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> gtProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> geProperty(String propertyName, String otherPropertyName) {
        return (QueryByModel<T>) super.geProperty(propertyName, otherPropertyName);
    }

    @Override
    public QueryByModel<T> sqlRestriction(String sql, Object... values) {
        return (QueryByModel<T>) super.sqlRestriction(sql, values);
    }

    @Override
    public QueryByModel<T> sqlRestriction(String sql) {
        return (QueryByModel<T>) super.sqlRestriction(sql);
    }

    @Override
    public QueryByModel<T> allEq(Map propertyNameValues) {
        return (QueryByModel<T>) super.allEq(propertyNameValues);
    }

    @Override
    public QueryByModel<T> isEmpty(String propertyName) {
        return (QueryByModel<T>) super.isEmpty(propertyName);
    }

    @Override
    public QueryByModel<T> isNotEmpty(String propertyName) {
        return (QueryByModel<T>) super.isNotEmpty(propertyName);
    }

    @Override
    public QueryByModel<T> sizeEq(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeEq(propertyName, size);
    }

    @Override
    public QueryByModel<T> sizeNe(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeNe(propertyName, size);
    }

    @Override
    public QueryByModel<T> sizeGt(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeGt(propertyName, size);
    }

    @Override
    public QueryByModel<T> sizeLt(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeLt(propertyName, size);
    }

    @Override
    public QueryByModel<T> sizeGe(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeGe(propertyName, size);
    }

    @Override
    public QueryByModel<T> sizeLe(String propertyName, int size) {
        return (QueryByModel<T>) super.sizeLe(propertyName, size);
    }

    @Override
    public QueryByModel<T> or(Or or) {
        return (QueryByModel<T>) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public QueryByModel<T> on(Criterion criterion) {
        return (QueryByModel<T>) super.on(criterion);
    }

    @Override
    public QueryByModel<T> join(String associationPath) {
        return (QueryByModel<T>) super.join(associationPath);
    }

	@Override
    public QueryByModel<T> join(String associationPath, String alias) {
        return (QueryByModel<T>) super.join(associationPath, alias);
    }

    @Override
    public QueryByModel<T> leftJoin(String associationPath) {
        return (QueryByModel<T>) super.leftJoin(associationPath);
    }

    @Override
    public QueryByModel<T> leftJoin(String associationPath, String alias) {
        return (QueryByModel<T>) super.leftJoin(associationPath, alias);
    }

    @Override
    public QueryByModel<T> rightJoin(String associationPath) {
        return (QueryByModel<T>) super.rightJoin(associationPath);
    }

    @Override
    public QueryByModel<T> rightJoin(String associationPath, String alias) {
        return (QueryByModel<T>) super.rightJoin(associationPath, alias);
    }

    @Override
    public QueryByModel<T> innerJoin(String associationPath) {
        return (QueryByModel<T>) super.innerJoin(associationPath);
    }

    @Override
    public QueryByModel<T> innerJoin(String associationPath, String alias) {
        return (QueryByModel<T>) super.innerJoin(associationPath, alias);
    }

    @Override
    public QueryByModel<T> fullJoin(String associationPath) {
        return (QueryByModel<T>) super.fullJoin(associationPath);
    }

    @Override
    public QueryByModel<T> fullJoin(String associationPath, String alias) {
        return (QueryByModel<T>) super.fullJoin(associationPath, alias);
    }

    @Override
    public QueryByModel<T> batch(String property) {
        return (QueryByModel<T>) super.batch(property);
    }

    //-------------------------- OrderBy --------------------------
    @Override
    public QueryByModel<T> asc(String propertyName) {
        return (QueryByModel<T>) super.asc(propertyName);
    }

    @Override
    public QueryByModel<T> desc(String propertyName) {
        return (QueryByModel<T>) super.desc(propertyName);
    }

}
