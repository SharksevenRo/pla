package com.pla.finder;

import com.pla.dao.Criteria;
import com.pla.dao.Or;
import com.pla.query.Pager;
import com.pla.query.Record;
import com.pla.service.CommonService;
import com.pla.utils.SpringUtil;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public class DFinder<M> extends Criteria implements Serializable {
    private static final long serialVersionUID = 3638237321915801747L;

    private Class<M> clazz;

    public DFinder(Class<M> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    private final CommonService getService(){
        CommonService commonService = SpringUtil.getBean("commonService");
        return commonService;
    }

    public static <M> DFinder<M> createDFinder(Class<M> clazz) {
        return new DFinder<M>(clazz);
    }

    public M load(Serializable id) {
        return getService().load(this, clazz, id);
    }

    public M first() {
        return getService().first(this, clazz);
    }

    public List<M> list() {
        return getService().list(this, clazz);
    }

    public List<M> list(int offset, int size) {
        return getService().list(this, clazz, offset, size);
    }

    public int count() {
        return getService().count(this, clazz);
    }

    public M uniqueResult() {
        return getService().uniqueResult(this, clazz);
    }

    public Pager<M> pager(int pageNo, int pageSize) {
        return getService().pager(this, clazz, pageNo, pageSize);
    }

    // -------------------------- QueryOp4Parts --------------------------
    public M uniqueResult(String... propertyNames) {
        return getService().uniqueResult(this, clazz, propertyNames);
    }

    public M first(String... propertyNames) {
        return getService().first(this, clazz, propertyNames);
    }

    public List<M> list(String... propertyNames) {
        return getService().list(this, clazz, propertyNames);
    }

    public List<M> list(Integer offset, Integer size, String... propertyNames) {
        return getService().list(this, clazz, offset, size, propertyNames);
    }

    public Pager<M> pager(int pageNo, int pageSize, String... propertyNames) {
        return getService().pager(this, clazz, pageNo, pageSize, propertyNames);
    }


    // -------------------------- Group By --------------------------
    public Record record() {
        return getService().record(this, clazz);
    }

    public List<Record> recordList() {
        return getService().recordList(this, clazz, null, null);
    }

    public List<Record> recordList(Integer offset, Integer size) {
        return getService().recordList(this, clazz, offset, size);
    }

    public Record recordFirst() {
        return getService().recordFirst(this, clazz);
    }


    // -------------------------- Criteria --------------------------
    @Override
    public DFinder<M> idEq(Serializable value) {
        return (DFinder<M>) super.idEq(value);
    }

    @Override
    public DFinder<M> eq(String propertyName, Object value) {
        return (DFinder<M>) super.eq(propertyName, value);
    }

    @Override
    public DFinder<M> eqOrIsNull(String propertyName, Object value) {
        return (DFinder<M>) super.eqOrIsNull(propertyName, value);
    }

    @Override
    public DFinder<M> ne(String propertyName, Object value) {
        return (DFinder<M>) super.ne(propertyName, value);
    }

    @Override
    public DFinder<M> neOrIsNotNull(String propertyName, Object value) {
        return (DFinder<M>) super.neOrIsNotNull(propertyName, value);
    }


    @Override
    public DFinder<M> like(String propertyName, String value) {
        return (DFinder<M>) super.like(propertyName, value);
    }

    @Override
    public DFinder<M> startLike(String propertyName, String value) {
        return (DFinder<M>) super.startLike(propertyName, value);
    }

    @Override
    public DFinder<M> endLike(String propertyName, String value) {
        return (DFinder<M>) super.endLike(propertyName, value);
    }

    @Override
    public DFinder<M> ilike(String propertyName, String value) {
        return (DFinder<M>) super.ilike(propertyName, value);
    }

    @Override
    public DFinder<M> startIlike(String propertyName, String value) {
        return (DFinder<M>) super.startIlike(propertyName, value);
    }

    @Override
    public DFinder<M> endIlike(String propertyName, String value) {
        return (DFinder<M>) super.endIlike(propertyName, value);
    }

    @Override
    public DFinder<M> gt(String propertyName, Object value) {
        return (DFinder<M>) super.gt(propertyName, value);
    }

    @Override
    public DFinder<M> lt(String propertyName, Object value) {
        return (DFinder<M>) super.lt(propertyName, value);
    }

    @Override
    public DFinder<M> le(String propertyName, Object value) {
        return (DFinder<M>) super.le(propertyName, value);
    }

    @Override
    public DFinder<M> ge(String propertyName, Object value) {
        return (DFinder<M>) super.ge(propertyName, value);
    }

    @Override
    public DFinder<M> between(String propertyName, Object lo, Object hi) {
        return (DFinder<M>) super.between(propertyName, lo, hi);
    }

    @Override
    public DFinder<M> in(String propertyName, Object... values) {
        return (DFinder<M>) super.in(propertyName, values);
    }

    @Override
    public DFinder<M> isNull(String propertyName) {
        return (DFinder<M>) super.isNull(propertyName);
    }

    @Override
    public DFinder<M> isNotNull(String propertyName) {
        return (DFinder<M>) super.isNotNull(propertyName);
    }

    @Override
    public DFinder<M> eqProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder<M> neProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder<M> ltProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder<M> leProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder<M> gtProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder<M> geProperty(String propertyName, String otherPropertyName) {
        return (DFinder<M>) super.geProperty(propertyName, otherPropertyName);
    }


    @Override
    public DFinder<M> sqlRestriction(String sql, Object... values) {
        return (DFinder<M>) super.sqlRestriction(sql, values);
    }

    @Override
    public DFinder<M> sqlRestriction(String sql) {
        return (DFinder<M>) super.sqlRestriction(sql);
    }

    @Override
    public DFinder<M> isEmpty(String propertyName) {
        return (DFinder<M>) super.isEmpty(propertyName);
    }

    @Override
    public DFinder<M> isNotEmpty(String propertyName) {
        return (DFinder<M>) super.isNotEmpty(propertyName);
    }

    @Override
    public DFinder<M> sizeEq(String propertyName, int size) {
        return (DFinder<M>) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinder<M> sizeNe(String propertyName, int size) {
        return (DFinder<M>) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinder<M> sizeGt(String propertyName, int size) {
        return (DFinder<M>) super.sizeGt(propertyName, size);
    }

    @Override
    public DFinder<M> sizeLt(String propertyName, int size) {
        return (DFinder<M>) super.sizeLt(propertyName, size);
    }

    @Override
    public DFinder<M> sizeGe(String propertyName, int size) {
        return (DFinder<M>) super.sizeGe(propertyName, size);
    }

    @Override
    public DFinder<M> sizeLe(String propertyName, int size) {
        return (DFinder<M>) super.sizeLe(propertyName, size);
    }

    @Override
    public DFinder<M> or(Or or) {
        return (DFinder<M>) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public DFinder<M> join(String associationPath) {
        return (DFinder<M>) super.join(associationPath);
    }

    @Override
    public DFinder<M> join(String associationPath, String alias) {
        return (DFinder<M>) super.join(associationPath, alias);
    }

    @Override
    public DFinder<M> leftJoin(String associationPath) {
        return (DFinder<M>) super.leftJoin(associationPath);
    }

    @Override
    public DFinder<M> leftJoin(String associationPath, String alias) {
        return (DFinder<M>) super.leftJoin(associationPath, alias);
    }

    @Override
    public DFinder<M> rightJoin(String associationPath) {
        return (DFinder<M>) super.rightJoin(associationPath);
    }

    @Override
    public DFinder<M> rightJoin(String associationPath, String alias) {
        return (DFinder<M>) super.rightJoin(associationPath, alias);
    }

    @Override
    public DFinder<M> innerJoin(String associationPath) {
        return (DFinder<M>) super.innerJoin(associationPath);
    }

    @Override
    public DFinder<M> innerJoin(String associationPath, String alias) {
        return (DFinder<M>) super.innerJoin(associationPath, alias);
    }

    @Override
    public DFinder<M> fullJoin(String associationPath) {
        return (DFinder<M>) super.fullJoin(associationPath);
    }

    @Override
    public DFinder<M> fullJoin(String associationPath, String alias) {
        return (DFinder<M>) super.fullJoin(associationPath, alias);
    }

    //-------------------------- OrderBy --------------------------
    @Override
    public DFinder<M> asc(String propertyName) {
        return (DFinder<M>) super.asc(propertyName);
    }

    @Override
    public DFinder<M> desc(String propertyName) {
        return (DFinder<M>) super.desc(propertyName);
    }

    //-------------------------- GroupBy --------------------------
    @Override
    public DFinder<M> groupBy(String propertyName) {
        return (DFinder<M>) super.groupBy(propertyName);
    }

    @Override
    public DFinder<M> countAll() {
        return (DFinder<M>) super.countAll();
    }

    @Override
    public DFinder<M> countAll(String alias) {
        return (DFinder<M>) super.countAll(alias);
    }

    @Override
    public DFinder<M> count(String propertyName) {
        return (DFinder<M>) super.countAll(propertyName);
    }

    @Override
    public DFinder<M> count(String propertyName, String alias) {
        return (DFinder<M>) super.count(propertyName, alias);
    }

    @Override
    public DFinder<M> max(String propertyName) {
        return (DFinder<M>) super.max(propertyName);
    }

    @Override
    public DFinder<M> max(String propertyName, String alias) {
        return (DFinder<M>) super.max(propertyName, alias);
    }

    @Override
    public DFinder<M> min(String propertyName) {
        return (DFinder<M>) super.min(propertyName);
    }

    @Override
    public DFinder<M> min(String propertyName, String alias) {
        return (DFinder<M>) super.min(propertyName, alias);
    }

    @Override
    public DFinder<M> sum(String propertyName) {
        return (DFinder<M>) super.sum(propertyName);
    }

    @Override
    public DFinder<M> sum(String propertyName, String alias) {
        return (DFinder<M>) super.sum(propertyName, alias);
    }

    @Override
    public DFinder<M> avg(String propertyName) {
        return (DFinder<M>) super.avg(propertyName);
    }

    @Override
    public DFinder<M> avg(String propertyName, String alias) {
        return (DFinder<M>) super.avg(propertyName, alias);
    }

    @Override
    public DFinder<M> distinct(String... properties) {
        return (DFinder<M>) super.distinct(properties);
    }

    @Override
    public DFinder<M> distinct() {
        return (DFinder<M>) super.distinct();
    }
}
