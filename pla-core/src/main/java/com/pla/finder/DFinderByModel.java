package com.pla.finder;

import com.pla.dao.Criteria;
import com.pla.dao.Or;
import com.pla.query.Pager;
import com.pla.query.Record;
import com.pla.service.CommonService;
import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import com.pla.utils.SpringUtil;

import java.io.Serializable;
import java.util.List;

public class DFinderByModel<M> {
    private M m;
    private Class<M> clazz;
    private Criteria criteria;

    public static <M> DFinderByModel<M> createDFByModel(M m) {
        return new DFinderByModel<M>(m);
    }

    public DFinderByModel(M m) {
        this.m = m;
        this.clazz = (Class<M>) m.getClass();
    }

    private Criteria getCriteria() {
        if (criteria == null)
            criteria = new Criteria(clazz);
        return criteria;
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
            getCriteria().idEq(id);
        return this;
    }

    public DFinderByModel<M> eq(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().eq(propertyName, value);
        return this;
    }

    public DFinderByModel<M> eqOrIsNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().eqOrIsNull(propertyName, value);
        return this;
    }

    public DFinderByModel<M> ne(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().ne(propertyName, value);
        return this;
    }

    public DFinderByModel<M> neOrIsNotNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().neOrIsNotNull(propertyName, value);
        return this;
    }

    public DFinderByModel<M> like(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> startLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> endLike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().like(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> ilike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> startIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> endIlike(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null) {
            getCriteria().ilike(propertyName, value.toString());
        }
        return this;
    }

    public DFinderByModel<M> gt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().gt(propertyName, value);
        return this;
    }

    public DFinderByModel<M> lt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().lt(propertyName, value);
        return this;
    }

    public DFinderByModel<M> le(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().le(propertyName, value);
        return this;
    }

    public DFinderByModel<M> ge(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            getCriteria().ge(propertyName, value);
        return this;
    }


    // -------------------------- Criteria --------------------------

    public DFinderByModel<M> idEq(Serializable value) {
        getCriteria().idEq(value);
        return this;
    }


    public DFinderByModel<M> eq(String propertyName, Object value) {
        getCriteria().eq(propertyName, value);
        return this;
    }


    public DFinderByModel<M> eqOrIsNull(String propertyName, Object value) {
        getCriteria().eqOrIsNull(propertyName, value);
        return this;
    }


    public DFinderByModel<M> ne(String propertyName, Object value) {
        getCriteria().ne(propertyName, value);
        return this;
    }


    public DFinderByModel<M> neOrIsNotNull(String propertyName, Object value) {
        getCriteria().neOrIsNotNull(propertyName, value);
        return this;
    }


    public DFinderByModel<M> like(String propertyName, String value) {
        getCriteria().like(propertyName, value);
        return this;
    }


    public DFinderByModel<M> startLike(String propertyName, String value) {
        getCriteria().startLike(propertyName, value);
        return this;
    }


    public DFinderByModel<M> endLike(String propertyName, String value) {
        getCriteria().endLike(propertyName, value);
        return this;
    }


    public DFinderByModel<M> ilike(String propertyName, String value) {
        getCriteria().ilike(propertyName, value);
        return this;
    }


    public DFinderByModel<M> startIlike(String propertyName, String value) {
        getCriteria().startIlike(propertyName, value);
        return this;
    }


    public DFinderByModel<M> endIlike(String propertyName, String value) {
        getCriteria().endIlike(propertyName, value);
        return this;
    }


    public DFinderByModel<M> gt(String propertyName, Object value) {
        getCriteria().gt(propertyName, value);
        return this;
    }


    public DFinderByModel<M> lt(String propertyName, Object value) {
        getCriteria().lt(propertyName, value);
        return this;
    }


    public DFinderByModel<M> le(String propertyName, Object value) {
        getCriteria().le(propertyName, value);
        return this;
    }


    public DFinderByModel<M> ge(String propertyName, Object value) {
        getCriteria().ge(propertyName, value);
        return this;
    }


    public DFinderByModel<M> between(String propertyName, Object lo, Object hi) {
        getCriteria().between(propertyName, lo, hi);
        return this;
    }


    public DFinderByModel<M> in(String propertyName, Object... values) {
        getCriteria().in(propertyName, values);
        return this;
    }


    public DFinderByModel<M> isNull(String propertyName) {
        getCriteria().isNull(propertyName);
        return this;
    }


    public DFinderByModel<M> isNotNull(String propertyName) {
        getCriteria().isNotNull(propertyName);
        return this;
    }


    public DFinderByModel<M> eqProperty(String propertyName, String otherPropertyName) {
        getCriteria().eqProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> neProperty(String propertyName, String otherPropertyName) {
        getCriteria().neProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> ltProperty(String propertyName, String otherPropertyName) {
        getCriteria().ltProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> leProperty(String propertyName, String otherPropertyName) {
        getCriteria().leProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> gtProperty(String propertyName, String otherPropertyName) {
        getCriteria().gtProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> geProperty(String propertyName, String otherPropertyName) {
        getCriteria().geProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinderByModel<M> sqlRestriction(String sql, Object... values) {
        getCriteria().sqlRestriction(sql, values);
        return this;
    }


    public DFinderByModel<M> sqlRestriction(String sql) {
        getCriteria().sqlRestriction(sql);
        return this;
    }


    public DFinderByModel<M> isEmpty(String propertyName) {
        getCriteria().isEmpty(propertyName);
        return this;
    }


    public DFinderByModel<M> isNotEmpty(String propertyName) {
        getCriteria().isNotEmpty(propertyName);
        return this;
    }


    public DFinderByModel<M> sizeEq(String propertyName, int size) {
        getCriteria().sizeEq(propertyName, size);
        return this;
    }


    public DFinderByModel<M> sizeNe(String propertyName, int size) {
        getCriteria().sizeEq(propertyName, size);
        return this;
    }


    public DFinderByModel<M> sizeGt(String propertyName, int size) {
        getCriteria().sizeGt(propertyName, size);
        return this;
    }


    public DFinderByModel<M> sizeLt(String propertyName, int size) {
        getCriteria().sizeLt(propertyName, size);
        return this;
    }


    public DFinderByModel<M> sizeGe(String propertyName, int size) {
        getCriteria().sizeGe(propertyName, size);
        return this;
    }


    public DFinderByModel<M> sizeLe(String propertyName, int size) {
        getCriteria().sizeLe(propertyName, size);
        return this;
    }


    public DFinderByModel<M> or(Or or) {
        getCriteria().or(or);
        return this;
    }

    //-------------------------- Join --------------------------

    public DFinderByModel<M> join(String associationPath) {
        getCriteria().join(associationPath);
        return this;
    }


    public DFinderByModel<M> join(String associationPath, String alias) {
        getCriteria().join(associationPath, alias);
        return this;
    }


    public DFinderByModel<M> leftJoin(String associationPath) {
        getCriteria().leftJoin(associationPath);
        return this;
    }


    public DFinderByModel<M> leftJoin(String associationPath, String alias) {
        getCriteria().leftJoin(associationPath, alias);
        return this;
    }


    public DFinderByModel<M> rightJoin(String associationPath) {
        getCriteria().rightJoin(associationPath);
        return this;
    }


    public DFinderByModel<M> rightJoin(String associationPath, String alias) {
        getCriteria().rightJoin(associationPath, alias);
        return this;
    }


    public DFinderByModel<M> innerJoin(String associationPath) {
        getCriteria().innerJoin(associationPath);
        return this;
    }


    public DFinderByModel<M> innerJoin(String associationPath, String alias) {
        getCriteria().innerJoin(associationPath, alias);
        return this;
    }


    public DFinderByModel<M> fullJoin(String associationPath) {
        getCriteria().fullJoin(associationPath);
        return this;
    }


    public DFinderByModel<M> fullJoin(String associationPath, String alias) {
        getCriteria().fullJoin(associationPath, alias);
        return this;
    }

    //-------------------------- OrderBy --------------------------

    public DFinderByModel<M> asc(String propertyName) {
        getCriteria().asc(propertyName);
        return this;
    }


    public DFinderByModel<M> desc(String propertyName) {
        getCriteria().desc(propertyName);
        return this;
    }

    //-------------------------- GroupBy --------------------------

    public DFinderByModel<M> groupBy(String propertyName) {
        getCriteria().groupBy(propertyName);
        return this;
    }


    public DFinderByModel<M> countAll() {
        getCriteria().countAll();
        return this;
    }


    public DFinderByModel<M> countAll(String alias) {
        getCriteria().countAll(alias);
        return this;
    }


    public DFinderByModel<M> count(String propertyName) {
        getCriteria().countAll(propertyName);
        return this;
    }


    public DFinderByModel<M> count(String propertyName, String alias) {
        getCriteria().count(propertyName, alias);
        return this;
    }


    public DFinderByModel<M> max(String propertyName) {
        getCriteria().max(propertyName);
        return this;
    }


    public DFinderByModel<M> max(String propertyName, String alias) {
        getCriteria().max(propertyName, alias);
        return this;
    }


    public DFinderByModel<M> min(String propertyName) {
        getCriteria().min(propertyName);
        return this;
    }


    public DFinderByModel<M> min(String propertyName, String alias) {
        getCriteria().min(propertyName, alias);
        return this;
    }


    public DFinderByModel<M> sum(String propertyName) {
        getCriteria().sum(propertyName);
        return this;
    }


    public DFinderByModel<M> sum(String propertyName, String alias) {
        getCriteria().sum(propertyName, alias);
        return this;
    }


    public DFinderByModel<M> avg(String propertyName) {
        getCriteria().avg(propertyName);
        return this;
    }


    public DFinderByModel<M> avg(String propertyName, String alias) {
        getCriteria().avg(propertyName, alias);
        return this;
    }


    public DFinderByModel<M> distinct(String... properties) {
        getCriteria().distinct(properties);
        return this;
    }


    public DFinderByModel<M> distinct() {
        getCriteria().distinct();
        return this;
    }


    //-------------------------- Query --------------------------
    private CommonService getService() {
        return SpringUtil.getBean("commonService");
    }

    public M load(Serializable id) {
        return getService().load(criteria, clazz, id);
    }

    public M first() {
        return getService().first(criteria, clazz);
    }

    public List<M> list() {
        return getService().list(criteria, clazz);
    }

    public List<M> list(int offset, int size) {
        return getService().list(criteria, clazz, offset, size);
    }

    public int count() {
        return getService().count(criteria, clazz);
    }

    public M uniqueResult() {
        return getService().uniqueResult(criteria, clazz);
    }

    public Pager<M> pager(int pageNo, int pageSize) {
        return getService().pager(criteria, clazz, pageNo, pageSize);
    }

    // -------------------------- QueryOp4Parts --------------------------
    public M uniqueResult(String... propertyNames) {
        return getService().uniqueResult(criteria, clazz, propertyNames);
    }

    public M first(String... propertyNames) {
        return getService().first(criteria, clazz, propertyNames);
    }

    public List<M> list(String... propertyNames) {
        return getService().list(criteria, clazz, propertyNames);
    }

    public List<M> list(Integer offset, Integer size, String... propertyNames) {
        return getService().list(criteria, clazz, offset, size, propertyNames);
    }

    public Pager<M> pager(int pageNo, int pageSize, String... propertyNames) {
        return getService().pager(criteria, clazz, pageNo, pageSize, propertyNames);
    }


    // -------------------------- Group By --------------------------
    public Record record() {
        return getService().record(criteria, clazz);
    }

    public List<Record> recordList() {
        return getService().recordList(criteria, clazz, null, null);
    }

    public List<Record> recordList(Integer offset, Integer size) {
        return getService().recordList(criteria, clazz, offset, size);
    }

    public Record recordFirst() {
        return getService().recordFirst(criteria, clazz);
    }
}
