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
public class DFinder extends Criteria implements Serializable {
    private static final long serialVersionUID = 3638237321915801747L;


    public DFinder(Class clazz) {
        super(clazz);
    }

    private final CommonService getService() {
        CommonService commonService = SpringUtil.getBean("commonService");
        return commonService;
    }

    public static DFinder createDFinder(Class clazz) {
        return new DFinder(clazz);
    }

    public <M> M load(Serializable id) {
        return (M) getService().load(this, getClazz(), id);
    }

    public <M> M first() {
        return (M) getService().first(this, getClazz());
    }

    public <M> List<M> list() {
        return getService().list(this, getClazz());
    }

    public <M> List<M> list(int offset, int size) {
        return getService().list(this, getClazz(), offset, size);
    }

    public int count() {
        return getService().count(this, getClazz());
    }

    public <M> M uniqueResult() {
        return (M) getService().uniqueResult(this, getClazz());
    }

    public <M> Pager<M> pager(int pageNo, int pageSize) {
        return getService().pager(this, getClazz(), pageNo, pageSize);
    }

    // -------------------------- QueryOp4Parts --------------------------
    public <M> M uniqueResult(String... propertyNames) {
        return (M) getService().uniqueResult(this, getClazz(), propertyNames);
    }

    public <M> M first(String... propertyNames) {
        return (M) getService().first(this, getClazz(), propertyNames);
    }

    public <M> List<M> list(String... propertyNames) {
        return getService().list(this, getClazz(), propertyNames);
    }

    public <M> List<M> list(Integer offset, Integer size, String... propertyNames) {
        return getService().list(this, getClazz(), offset, size, propertyNames);
    }

    public <M> Pager<M> pager(int pageNo, int pageSize, String... propertyNames) {
        return getService().pager(this, getClazz(), pageNo, pageSize, propertyNames);
    }


    // -------------------------- Group By --------------------------
    public Record record() {
        return getService().record(this, getClazz());
    }

    public List<Record> recordList() {
        return getService().recordList(this, getClazz(), null, null);
    }

    public List<Record> recordList(Integer offset, Integer size) {
        return getService().recordList(this, getClazz(), offset, size);
    }

    public Record recordFirst() {
        return getService().recordFirst(this, getClazz());
    }


    // -------------------------- Criteria --------------------------
    @Override
    public DFinder idEq(Serializable value) {
        return (DFinder) super.idEq(value);
    }

    @Override
    public DFinder eq(String propertyName, Object value) {
        return (DFinder) super.eq(propertyName, value);
    }

    @Override
    public DFinder eqOrIsNull(String propertyName, Object value) {
        return (DFinder) super.eqOrIsNull(propertyName, value);
    }

    @Override
    public DFinder ne(String propertyName, Object value) {
        return (DFinder) super.ne(propertyName, value);
    }

    @Override
    public DFinder neOrIsNotNull(String propertyName, Object value) {
        return (DFinder) super.neOrIsNotNull(propertyName, value);
    }


    @Override
    public DFinder like(String propertyName, String value) {
        return (DFinder) super.like(propertyName, value);
    }

    @Override
    public DFinder startLike(String propertyName, String value) {
        return (DFinder) super.startLike(propertyName, value);
    }

    @Override
    public DFinder endLike(String propertyName, String value) {
        return (DFinder) super.endLike(propertyName, value);
    }

    @Override
    public DFinder ilike(String propertyName, String value) {
        return (DFinder) super.ilike(propertyName, value);
    }

    @Override
    public DFinder startIlike(String propertyName, String value) {
        return (DFinder) super.startIlike(propertyName, value);
    }

    @Override
    public DFinder endIlike(String propertyName, String value) {
        return (DFinder) super.endIlike(propertyName, value);
    }

    @Override
    public DFinder gt(String propertyName, Object value) {
        return (DFinder) super.gt(propertyName, value);
    }

    @Override
    public DFinder lt(String propertyName, Object value) {
        return (DFinder) super.lt(propertyName, value);
    }

    @Override
    public DFinder le(String propertyName, Object value) {
        return (DFinder) super.le(propertyName, value);
    }

    @Override
    public DFinder ge(String propertyName, Object value) {
        return (DFinder) super.ge(propertyName, value);
    }

    @Override
    public DFinder between(String propertyName, Object lo, Object hi) {
        return (DFinder) super.between(propertyName, lo, hi);
    }

    @Override
    public DFinder in(String propertyName, Object... values) {
        return (DFinder) super.in(propertyName, values);
    }

    @Override
    public DFinder isNull(String propertyName) {
        return (DFinder) super.isNull(propertyName);
    }

    @Override
    public DFinder isNotNull(String propertyName) {
        return (DFinder) super.isNotNull(propertyName);
    }

    @Override
    public DFinder eqProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder neProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder ltProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder leProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder gtProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public DFinder geProperty(String propertyName, String otherPropertyName) {
        return (DFinder) super.geProperty(propertyName, otherPropertyName);
    }


    @Override
    public DFinder sqlRestriction(String sql, Object... values) {
        return (DFinder) super.sqlRestriction(sql, values);
    }

    @Override
    public DFinder sqlRestriction(String sql) {
        return (DFinder) super.sqlRestriction(sql);
    }

    @Override
    public DFinder isEmpty(String propertyName) {
        return (DFinder) super.isEmpty(propertyName);
    }

    @Override
    public DFinder isNotEmpty(String propertyName) {
        return (DFinder) super.isNotEmpty(propertyName);
    }

    @Override
    public DFinder sizeEq(String propertyName, int size) {
        return (DFinder) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinder sizeNe(String propertyName, int size) {
        return (DFinder) super.sizeEq(propertyName, size);
    }

    @Override
    public DFinder sizeGt(String propertyName, int size) {
        return (DFinder) super.sizeGt(propertyName, size);
    }

    @Override
    public DFinder sizeLt(String propertyName, int size) {
        return (DFinder) super.sizeLt(propertyName, size);
    }

    @Override
    public DFinder sizeGe(String propertyName, int size) {
        return (DFinder) super.sizeGe(propertyName, size);
    }

    @Override
    public DFinder sizeLe(String propertyName, int size) {
        return (DFinder) super.sizeLe(propertyName, size);
    }

    @Override
    public DFinder or(Or or) {
        return (DFinder) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public DFinder join(String associationPath) {
        return (DFinder) super.join(associationPath);
    }

    @Override
    public DFinder join(String associationPath, String alias) {
        return (DFinder) super.join(associationPath, alias);
    }

    @Override
    public DFinder leftJoin(String associationPath) {
        return (DFinder) super.leftJoin(associationPath);
    }

    @Override
    public DFinder leftJoin(String associationPath, String alias) {
        return (DFinder) super.leftJoin(associationPath, alias);
    }

    @Override
    public DFinder rightJoin(String associationPath) {
        return (DFinder) super.rightJoin(associationPath);
    }

    @Override
    public DFinder rightJoin(String associationPath, String alias) {
        return (DFinder) super.rightJoin(associationPath, alias);
    }

    @Override
    public DFinder innerJoin(String associationPath) {
        return (DFinder) super.innerJoin(associationPath);
    }

    @Override
    public DFinder innerJoin(String associationPath, String alias) {
        return (DFinder) super.innerJoin(associationPath, alias);
    }

    @Override
    public DFinder fullJoin(String associationPath) {
        return (DFinder) super.fullJoin(associationPath);
    }

    @Override
    public DFinder fullJoin(String associationPath, String alias) {
        return (DFinder) super.fullJoin(associationPath, alias);
    }

    //-------------------------- OrderBy --------------------------
    @Override
    public DFinder asc(String propertyName) {
        return (DFinder) super.asc(propertyName);
    }

    @Override
    public DFinder desc(String propertyName) {
        return (DFinder) super.desc(propertyName);
    }

    //-------------------------- GroupBy --------------------------
    @Override
    public DFinder groupBy(String propertyName) {
        return (DFinder) super.groupBy(propertyName);
    }

    @Override
    public DFinder countAll() {
        return (DFinder) super.countAll();
    }

    @Override
    public DFinder countAll(String alias) {
        return (DFinder) super.countAll(alias);
    }

    @Override
    public DFinder count(String propertyName) {
        return (DFinder) super.countAll(propertyName);
    }

    @Override
    public DFinder count(String propertyName, String alias) {
        return (DFinder) super.count(propertyName, alias);
    }

    @Override
    public DFinder max(String propertyName) {
        return (DFinder) super.max(propertyName);
    }

    @Override
    public DFinder max(String propertyName, String alias) {
        return (DFinder) super.max(propertyName, alias);
    }

    @Override
    public DFinder min(String propertyName) {
        return (DFinder) super.min(propertyName);
    }

    @Override
    public DFinder min(String propertyName, String alias) {
        return (DFinder) super.min(propertyName, alias);
    }

    @Override
    public DFinder sum(String propertyName) {
        return (DFinder) super.sum(propertyName);
    }

    @Override
    public DFinder sum(String propertyName, String alias) {
        return (DFinder) super.sum(propertyName, alias);
    }

    @Override
    public DFinder avg(String propertyName) {
        return (DFinder) super.avg(propertyName);
    }

    @Override
    public DFinder avg(String propertyName, String alias) {
        return (DFinder) super.avg(propertyName, alias);
    }

    @Override
    public DFinder distinct(String... properties) {
        return (DFinder) super.distinct(properties);
    }

    @Override
    public DFinder distinct() {
        return (DFinder) super.distinct();
    }
}
