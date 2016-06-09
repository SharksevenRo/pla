package com.pla.finder;

import com.pla.dao.Criteria;
import com.pla.dao.Or;
import com.pla.query.Pager;
import com.pla.query.Record;
import com.pla.service.CommonService;
import com.pla.utils.SpringUtil;

import java.io.Serializable;
import java.util.List;

public class DFinder<M> implements Serializable {
    private static final long serialVersionUID = 3638237321915801747L;

    private Criteria criteria;
    private Class<M> clazz;

    public DFinder(Class<M> clazz) {
        this.clazz = clazz;
    }

    private Criteria getCriteria() {
        if (criteria == null)
            criteria = new Criteria(clazz);
        return criteria;
    }

    private CommonService getService() {
        return SpringUtil.getBean("commonService");
    }

    public static <M> DFinder<M> createDFinder(Class<M> clazz) {
        return new DFinder<M>(clazz);
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


    // -------------------------- Criteria --------------------------

    public DFinder<M> idEq(Serializable value) {
        getCriteria().idEq(value);
        return this;
    }


    public DFinder<M> eq(String propertyName, Object value) {
        getCriteria().eq(propertyName, value);
        return this;
    }


    public DFinder<M> eqOrIsNull(String propertyName, Object value) {
        getCriteria().eqOrIsNull(propertyName, value);
        return this;
    }


    public DFinder<M> ne(String propertyName, Object value) {
        getCriteria().ne(propertyName, value);
        return this;
    }


    public DFinder<M> neOrIsNotNull(String propertyName, Object value) {
        getCriteria().neOrIsNotNull(propertyName, value);
        return this;
    }


    public DFinder<M> like(String propertyName, String value) {
        getCriteria().like(propertyName, value);
        return this;
    }


    public DFinder<M> startLike(String propertyName, String value) {
        getCriteria().startLike(propertyName, value);
        return this;
    }


    public DFinder<M> endLike(String propertyName, String value) {
        getCriteria().endLike(propertyName, value);
        return this;
    }


    public DFinder<M> ilike(String propertyName, String value) {
        getCriteria().ilike(propertyName, value);
        return this;
    }


    public DFinder<M> startIlike(String propertyName, String value) {
        getCriteria().startIlike(propertyName, value);
        return this;
    }


    public DFinder<M> endIlike(String propertyName, String value) {
        getCriteria().endIlike(propertyName, value);
        return this;
    }


    public DFinder<M> gt(String propertyName, Object value) {
        getCriteria().gt(propertyName, value);
        return this;
    }


    public DFinder<M> lt(String propertyName, Object value) {
        getCriteria().lt(propertyName, value);
        return this;
    }


    public DFinder<M> le(String propertyName, Object value) {
        getCriteria().le(propertyName, value);
        return this;
    }


    public DFinder<M> ge(String propertyName, Object value) {
        getCriteria().ge(propertyName, value);
        return this;
    }


    public DFinder<M> between(String propertyName, Object lo, Object hi) {
        getCriteria().between(propertyName, lo, hi);
        return this;
    }


    public DFinder<M> in(String propertyName, Object... values) {
        getCriteria().in(propertyName, values);
        return this;
    }


    public DFinder<M> isNull(String propertyName) {
        getCriteria().isNull(propertyName);
        return this;
    }


    public DFinder<M> isNotNull(String propertyName) {
        getCriteria().isNotNull(propertyName);
        return this;
    }


    public DFinder<M> eqProperty(String propertyName, String otherPropertyName) {
        getCriteria().eqProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> neProperty(String propertyName, String otherPropertyName) {
        getCriteria().neProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> ltProperty(String propertyName, String otherPropertyName) {
        getCriteria().ltProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> leProperty(String propertyName, String otherPropertyName) {
        getCriteria().leProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> gtProperty(String propertyName, String otherPropertyName) {
        getCriteria().gtProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> geProperty(String propertyName, String otherPropertyName) {
        getCriteria().geProperty(propertyName, otherPropertyName);
        return this;
    }


    public DFinder<M> sqlRestriction(String sql, Object... values) {
        getCriteria().sqlRestriction(sql, values);
        return this;
    }


    public DFinder<M> sqlRestriction(String sql) {
        getCriteria().sqlRestriction(sql);
        return this;
    }


    public DFinder<M> isEmpty(String propertyName) {
        getCriteria().isEmpty(propertyName);
        return this;
    }


    public DFinder<M> isNotEmpty(String propertyName) {
        getCriteria().isNotEmpty(propertyName);
        return this;
    }


    public DFinder<M> sizeEq(String propertyName, int size) {
        getCriteria().sizeEq(propertyName, size);
        return this;
    }


    public DFinder<M> sizeNe(String propertyName, int size) {
        getCriteria().sizeEq(propertyName, size);
        return this;
    }


    public DFinder<M> sizeGt(String propertyName, int size) {
        getCriteria().sizeGt(propertyName, size);
        return this;
    }


    public DFinder<M> sizeLt(String propertyName, int size) {
        getCriteria().sizeLt(propertyName, size);
        return this;
    }


    public DFinder<M> sizeGe(String propertyName, int size) {
        getCriteria().sizeGe(propertyName, size);
        return this;
    }


    public DFinder<M> sizeLe(String propertyName, int size) {
        getCriteria().sizeLe(propertyName, size);
        return this;
    }


    public DFinder<M> or(Or or) {
        getCriteria().or(or);
        return this;
    }

    //-------------------------- Join --------------------------

    public DFinder<M> join(String associationPath) {
        getCriteria().join(associationPath);
        return this;
    }


    public DFinder<M> join(String associationPath, String alias) {
        getCriteria().join(associationPath, alias);
        return this;
    }


    public DFinder<M> leftJoin(String associationPath) {
        getCriteria().leftJoin(associationPath);
        return this;
    }


    public DFinder<M> leftJoin(String associationPath, String alias) {
        getCriteria().leftJoin(associationPath, alias);
        return this;
    }


    public DFinder<M> rightJoin(String associationPath) {
        getCriteria().rightJoin(associationPath);
        return this;
    }


    public DFinder<M> rightJoin(String associationPath, String alias) {
        getCriteria().rightJoin(associationPath, alias);
        return this;
    }


    public DFinder<M> innerJoin(String associationPath) {
        getCriteria().innerJoin(associationPath);
        return this;
    }


    public DFinder<M> innerJoin(String associationPath, String alias) {
        getCriteria().innerJoin(associationPath, alias);
        return this;
    }


    public DFinder<M> fullJoin(String associationPath) {
        getCriteria().fullJoin(associationPath);
        return this;
    }


    public DFinder<M> fullJoin(String associationPath, String alias) {
        getCriteria().fullJoin(associationPath, alias);
        return this;
    }

    //-------------------------- OrderBy --------------------------

    public DFinder<M> asc(String propertyName) {
        getCriteria().asc(propertyName);
        return this;
    }


    public DFinder<M> desc(String propertyName) {
        getCriteria().desc(propertyName);
        return this;
    }

    //-------------------------- GroupBy --------------------------

    public DFinder<M> groupBy(String propertyName) {
        getCriteria().groupBy(propertyName);
        return this;
    }


    public DFinder<M> countAll() {
        getCriteria().countAll();
        return this;
    }


    public DFinder<M> countAll(String alias) {
        getCriteria().countAll(alias);
        return this;
    }


    public DFinder<M> count(String propertyName) {
        getCriteria().countAll(propertyName);
        return this;
    }


    public DFinder<M> count(String propertyName, String alias) {
        getCriteria().count(propertyName, alias);
        return this;
    }


    public DFinder<M> max(String propertyName) {
        getCriteria().max(propertyName);
        return this;
    }


    public DFinder<M> max(String propertyName, String alias) {
        getCriteria().max(propertyName, alias);
        return this;
    }


    public DFinder<M> min(String propertyName) {
        getCriteria().min(propertyName);
        return this;
    }


    public DFinder<M> min(String propertyName, String alias) {
        getCriteria().min(propertyName, alias);
        return this;
    }


    public DFinder<M> sum(String propertyName) {
        getCriteria().sum(propertyName);
        return this;
    }


    public DFinder<M> sum(String propertyName, String alias) {
        getCriteria().sum(propertyName, alias);
        return this;
    }


    public DFinder<M> avg(String propertyName) {
        getCriteria().avg(propertyName);
        return this;
    }


    public DFinder<M> avg(String propertyName, String alias) {
        getCriteria().avg(propertyName, alias);
        return this;
    }


    public DFinder<M> distinct(String... properties) {
        getCriteria().distinct(properties);
        return this;
    }


    public DFinder<M> distinct() {
        getCriteria().distinct();
        return this;
    }
}
