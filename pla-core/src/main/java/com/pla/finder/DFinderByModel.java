package com.pla.finder;

import com.pla.dao.Or;
import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import org.hibernate.criterion.MatchMode;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class DFinderByModel<M> extends DFinder {
    private M m;

    public static <M> DFinderByModel<M> createDFByModel(M m) {
        return new DFinderByModel<M>(m);
    }

    public DFinderByModel(M m) {
        super(m.getClass());
        this.m = m;
    }

    private Object getValue(String propertyName) {
        try {
            return SimplePropertyUtil.getProperty(m, propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DFinderByModel<M> idEq() {
        Serializable id = ModelUtil.getIdValue(m);
        if (id != null)
            super.idEq(id);
        return this;
    }

    public DFinderByModel<M> eq(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.eq(propertyName, value);
        return this;
    }

    public DFinderByModel<M> eqOrIsNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.eqOrIsNull(propertyName, value);
        return this;
    }

    public DFinderByModel<M> ne(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.ne(propertyName, value);
        return this;
    }

    public DFinderByModel<M> neOrIsNotNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.neOrIsNotNull(propertyName, value);
        return this;
    }

    public DFinderByModel<M> like(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> startLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> endLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> like(String propertyName, MatchMode matchMode) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> ilike(String propertyName, MatchMode matchMode) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> ilike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> startIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> endIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            super.ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> gt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.gt(propertyName, value);
        return this;
    }

    public DFinderByModel<M> lt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.lt(propertyName, value);
        return this;
    }

    public DFinderByModel<M> le(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.le(propertyName, value);
        return this;
    }

    public DFinderByModel<M> ge(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            super.ge(propertyName, value);
        return this;
    }


    // -------------------------- Criteria --------------------------
    @Override
    public DFinderByModel<M> idEq(Serializable value) {
        return (DFinderByModel<M>) super.idEq(value);
    }

    @Override
    public DFinderByModel<M> eq(String propertyName, Object value) {
        return (DFinderByModel<M>) super.eq(propertyName, value);
    }

    @Override
    public DFinderByModel<M> eqOrIsNull(String propertyName, Object value) {
        return (DFinderByModel<M>) super.eqOrIsNull(propertyName, value);
    }

    @Override
    public DFinderByModel<M> ne(String propertyName, Object value) {
        return (DFinderByModel<M>) super.ne(propertyName, value);
    }

    @Override
    public DFinderByModel<M> neOrIsNotNull(String propertyName, Object value) {
        return (DFinderByModel<M>) super.neOrIsNotNull(propertyName, value);
    }


    @Override
    public DFinderByModel<M> like(String propertyName, String value) {
        return (DFinderByModel<M>) super.like(propertyName, value);
    }

    @Override
    public DFinderByModel<M> startLike(String propertyName, String value) {
        return (DFinderByModel<M>) super.startLike(propertyName, value);
    }

    @Override
    public DFinderByModel<M> endLike(String propertyName, String value) {
        return (DFinderByModel<M>) super.endLike(propertyName, value);
    }

    @Override
    public DFinderByModel<M> ilike(String propertyName, String value) {
        return (DFinderByModel<M>) super.ilike(propertyName, value);
    }

    @Override
    public DFinderByModel<M> startIlike(String propertyName, String value) {
        return (DFinderByModel<M>) super.startIlike(propertyName, value);
    }

    @Override
    public DFinderByModel<M> endIlike(String propertyName, String value) {
        return (DFinderByModel<M>) super.endIlike(propertyName, value);
    }

    @Override
    public DFinderByModel<M> gt(String propertyName, Object value) {
        return (DFinderByModel<M>) super.gt(propertyName, value);
    }

    @Override
    public DFinderByModel<M> lt(String propertyName, Object value) {
        return (DFinderByModel<M>) super.lt(propertyName, value);
    }

    @Override
    public DFinderByModel<M> le(String propertyName, Object value) {
        return (DFinderByModel<M>) super.le(propertyName, value);
    }

    @Override
    public DFinderByModel<M> ge(String propertyName, Object value) {
        return (DFinderByModel<M>) super.ge(propertyName, value);
    }

    @Override
    public DFinderByModel<M> between(String propertyName, Object lo, Object hi) {
        return (DFinderByModel<M>) super.between(propertyName, lo, hi);
    }

    @Override
    public DFinderByModel<M> in(String propertyName, Object... values) {
        return (DFinderByModel<M>) super.in(propertyName, values);
    }

    @Override
    public DFinderByModel<M> isNull(String propertyName) {
        return (DFinderByModel<M>) super.isNull(propertyName);
    }

    @Override
    public DFinderByModel<M> isNotNull(String propertyName) {
        return (DFinderByModel<M>) super.isNotNull(propertyName);
    }

    @Override
    public DFinderByModel<M> eqProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinderByModel<M> neProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinderByModel<M> ltProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinderByModel<M> leProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinderByModel<M> gtProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinderByModel<M> geProperty(String propertyName, String otherPropertyName) {
        return (DFinderByModel<M>) super.geProperty(propertyName, otherPropertyName);
    }


    @Override
    public DFinderByModel<M> sqlRestriction(String sql, Object... values) {
        return (DFinderByModel<M>) super.sqlRestriction(sql, values);
    }

    @Override
    public DFinderByModel<M> sqlRestriction(String sql) {
        return (DFinderByModel<M>) super.sqlRestriction(sql);
    }

    @Override
    public DFinderByModel<M> isEmpty(String propertyName) {
        return (DFinderByModel<M>) super.isEmpty(propertyName);
    }

    @Override
    public DFinderByModel<M> isNotEmpty(String propertyName) {
        return (DFinderByModel<M>) super.isNotEmpty(propertyName);
    }

    @Override
    public DFinderByModel<M> sizeEq(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinderByModel<M> sizeNe(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinderByModel<M> sizeGt(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeGt(propertyName, size);
    }

    @Override
    public DFinderByModel<M> sizeLt(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeLt(propertyName, size);
    }

    @Override
    public DFinderByModel<M> sizeGe(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeGe(propertyName, size);
    }

    @Override
    public DFinderByModel<M> sizeLe(String propertyName, int size) {
        return (DFinderByModel<M>) super.sizeLe(propertyName, size);
    }

    @Override
    public DFinderByModel<M> or(Or or) {
        return (DFinderByModel<M>) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public DFinderByModel<M> join(String associationPath) {
        return (DFinderByModel<M>) super.join(associationPath);
    }

    @Override
    public DFinderByModel<M> join(String associationPath, String alias) {
        return (DFinderByModel<M>) super.join(associationPath, alias);
    }

    @Override
    public DFinderByModel<M> leftJoin(String associationPath) {
        return (DFinderByModel<M>) super.leftJoin(associationPath);
    }

    @Override
    public DFinderByModel<M> leftJoin(String associationPath, String alias) {
        return (DFinderByModel<M>) super.leftJoin(associationPath, alias);
    }

    @Override
    public DFinderByModel<M> rightJoin(String associationPath) {
        return (DFinderByModel<M>) super.rightJoin(associationPath);
    }

    @Override
    public DFinderByModel<M> rightJoin(String associationPath, String alias) {
        return (DFinderByModel<M>) super.rightJoin(associationPath, alias);
    }

    @Override
    public DFinderByModel<M> innerJoin(String associationPath) {
        return (DFinderByModel<M>) super.innerJoin(associationPath);
    }

    @Override
    public DFinderByModel<M> innerJoin(String associationPath, String alias) {
        return (DFinderByModel<M>) super.innerJoin(associationPath, alias);
    }

    @Override
    public DFinderByModel<M> fullJoin(String associationPath) {
        return (DFinderByModel<M>) super.fullJoin(associationPath);
    }

    @Override
    public DFinderByModel<M> fullJoin(String associationPath, String alias) {
        return (DFinderByModel<M>) super.fullJoin(associationPath, alias);
    }

    //-------------------------- OrderBy --------------------------
    @Override
    public DFinderByModel<M> asc(String propertyName) {
        return (DFinderByModel<M>) super.asc(propertyName);
    }

    @Override
    public DFinderByModel<M> desc(String propertyName) {
        return (DFinderByModel<M>) super.desc(propertyName);
    }

    //-------------------------- GroupBy --------------------------
    @Override
    public DFinderByModel<M> groupBy(String propertyName) {
        return (DFinderByModel<M>) super.groupBy(propertyName);
    }

    @Override
    public DFinderByModel<M> countAll() {
        return (DFinderByModel<M>) super.countAll();
    }

    @Override
    public DFinderByModel<M> countAll(String alias) {
        return (DFinderByModel<M>) super.countAll(alias);
    }

    @Override
    public DFinderByModel<M> count(String propertyName) {
        return (DFinderByModel<M>) super.countAll(propertyName);
    }

    @Override
    public DFinderByModel<M> count(String propertyName, String alias) {
        return (DFinderByModel<M>) super.count(propertyName, alias);
    }

    @Override
    public DFinderByModel<M> max(String propertyName) {
        return (DFinderByModel<M>) super.max(propertyName);
    }

    @Override
    public DFinderByModel<M> max(String propertyName, String alias) {
        return (DFinderByModel<M>) super.max(propertyName, alias);
    }

    @Override
    public DFinderByModel<M> min(String propertyName) {
        return (DFinderByModel<M>) super.min(propertyName);
    }

    @Override
    public DFinderByModel<M> min(String propertyName, String alias) {
        return (DFinderByModel<M>) super.min(propertyName, alias);
    }

    @Override
    public DFinderByModel<M> sum(String propertyName) {
        return (DFinderByModel<M>) super.sum(propertyName);
    }

    @Override
    public DFinderByModel<M> sum(String propertyName, String alias) {
        return (DFinderByModel<M>) super.sum(propertyName, alias);
    }

    @Override
    public DFinderByModel<M> avg(String propertyName) {
        return (DFinderByModel<M>) super.avg(propertyName);
    }

    @Override
    public DFinderByModel<M> avg(String propertyName, String alias) {
        return (DFinderByModel<M>) super.avg(propertyName, alias);
    }

    @Override
    public DFinderByModel<M> distinct(String... properties) {
        return (DFinderByModel<M>) super.distinct(properties);
    }

    @Override
    public DFinderByModel<M> distinct() {
        return (DFinderByModel<M>) super.distinct();
    }
}
