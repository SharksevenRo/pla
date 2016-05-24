package com.pla.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Criteria implements Serializable {
    private static final long serialVersionUID = -1959436287549702712L;

    private Class clazz;
    private List<Criterion> criterionList = new ArrayList<Criterion>();

    public Criteria(Class clazz) {
        this.clazz = clazz;
    }

    public static Criteria create(Class clazz) {
        return new Criteria(clazz);
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = criterionList;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    private void set(String expression, String propertyName) {
        if (propertyName == null)
            return;
        Criterion criterion = Criterion.create();
        criterion.setExpression(expression);
        criterion.setPropertyName(propertyName);
        criterionList.add(criterion);
    }

    private void set(String expression, String propertyName, Object value) {
        if (value == null)
            return;
        Criterion criterion = Criterion.create();
        criterion.setExpression(expression);
        criterion.setPropertyName(propertyName);
        criterion.setValue(value);
        criterionList.add(criterion);
    }

    private void set(String expression, String propertyName, Object[] values) {
        if (values == null)
            return;
        Criterion criterion = Criterion.create();
        criterion.setExpression(expression);
        criterion.setPropertyName(propertyName);
        criterion.setValues(values);
        criterionList.add(criterion);
    }

    private void setProperties(String expression, String propertyName, String otherPropertyName) {
        if (propertyName == null || otherPropertyName == null)
            return;
        Criterion criterion = Criterion.create();
        criterion.setExpression(expression);
        criterion.setPropertyName(propertyName);
        criterion.setOtherPropertyName(otherPropertyName);
        criterionList.add(criterion);
    }

    public Criteria idEq(Serializable value) {
        set("idEq", null, value);
        return this;
    }

    public Criteria eq(String propertyName, Object value) {
        set("eq", propertyName, value);
        return this;
    }

    public Criteria eqOrIsNull(String propertyName, Object value) {
        set("eqOrIsNull", propertyName, value);
        return this;
    }

    public Criteria ne(String propertyName, Object value) {
        set("ne", propertyName, value);
        return this;
    }

    public Criteria neOrIsNotNull(String propertyName, Object value) {
        set("neOrIsNotNull", propertyName, value);
        return this;
    }


    public Criteria like(String propertyName, String value) {
        set("like", propertyName, value);
        return this;
    }

    public Criteria startLike(String propertyName, String value) {
        set("startLike", propertyName, value);
        return this;
    }

    public Criteria endLike(String propertyName, String value) {
        set("endLike", propertyName, value);
        return this;
    }

    public Criteria ilike(String propertyName, String value) {
        set("ilike", propertyName, value);
        return this;
    }

    public Criteria startIlike(String propertyName, String value) {
        set("startIlike", propertyName, value);
        return this;
    }

    public Criteria endIlike(String propertyName, String value) {
        set("endIlike", propertyName, value);
        return this;
    }

    public Criteria gt(String propertyName, Object value) {
        set("gt", propertyName, value);
        return this;
    }

    public Criteria lt(String propertyName, Object value) {
        set("lt", propertyName, value);
        return this;
    }

    public Criteria le(String propertyName, Object value) {
        set("le", propertyName, value);
        return this;
    }

    public Criteria ge(String propertyName, Object value) {
        set("ge", propertyName, value);
        return this;
    }

    public Criteria between(String propertyName, Object lo, Object hi) {
        Object[] values = new Object[]{lo, hi};
        set("between", propertyName, values);
        return this;
    }

    public Criteria in(String propertyName, Object... values) {
        set("in", propertyName, values);
        return this;
    }

    public Criteria isNull(String propertyName) {
        set("isNull", propertyName);
        return this;
    }

    public Criteria isNotNull(String propertyName) {
        set("isNotNull", propertyName);
        return this;
    }

    public Criteria eqProperty(String propertyName, String otherPropertyName) {
        setProperties("eqProperty", propertyName, otherPropertyName);
        return this;
    }

    public Criteria neProperty(String propertyName, String otherPropertyName) {
        setProperties("neProperty", propertyName, otherPropertyName);
        return this;
    }

    public Criteria ltProperty(String propertyName, String otherPropertyName) {
        setProperties("ltProperty", propertyName, otherPropertyName);
        return this;
    }

    public Criteria leProperty(String propertyName, String otherPropertyName) {
        setProperties("leProperty", propertyName, otherPropertyName);
        return this;
    }

    public Criteria gtProperty(String propertyName, String otherPropertyName) {
        setProperties("gtProperty", propertyName, otherPropertyName);
        return this;
    }

    public Criteria geProperty(String propertyName, String otherPropertyName) {
        setProperties("geProperty", propertyName, otherPropertyName);
        return this;
    }


    public Criteria sqlRestriction(String sql, Object... values) {
        Criterion criterion = Criterion.create();
        criterion.setSql(sql);
        criterion.setValues(values);
        criterionList.add(criterion);
        return this;
    }

    public Criteria sqlRestriction(String sql) {
        sqlRestriction(sql, (Object[]) null);
        return this;
    }

    public Criteria isEmpty(String propertyName) {
        set("isEmpty", propertyName);
        return this;
    }

    public Criteria isNotEmpty(String propertyName) {
        set("isNotEmpty", propertyName);
        return this;
    }

    public Criteria sizeEq(String propertyName, int size) {
        set("sizeEq", propertyName, size);
        return this;
    }

    public Criteria sizeNe(String propertyName, int size) {
        set("sizeNe", propertyName, size);
        return this;
    }

    public Criteria sizeGt(String propertyName, int size) {
        set("sizeGt", propertyName, size);
        return this;
    }

    public Criteria sizeLt(String propertyName, int size) {
        set("sizeLt", propertyName, size);
        return this;
    }

    public Criteria sizeGe(String propertyName, int size) {
        set("sizeGe", propertyName, size);
        return this;
    }

    public Criteria sizeLe(String propertyName, int size) {
        set("sizeLe", propertyName, size);
        return this;
    }

    public Criteria or(Or or) {
        Criterion criterion = Criterion.create();
        criterion.setExpression("or");
        criterion.setOr(or);
        criterionList.add(criterion);
        return this;
    }

    //-------------------------- Join --------------------------
    private void setJoin(String joinEx, String associationPath, String alias) {
        Criterion criterion = Criterion.create();
        criterion.setJoinEx(joinEx);
        criterion.setPropertyName(associationPath);
        criterion.setAlias(alias);
        criterionList.add(criterion);
    }

    public Criteria join(String associationPath) {
        return this.join(associationPath, associationPath);
    }

    public Criteria join(String associationPath, String alias) {
        return this.leftJoin(associationPath, alias);
    }

    public Criteria leftJoin(String associationPath) {
        return this.leftJoin(associationPath, associationPath);
    }

    public Criteria leftJoin(String associationPath, String alias) {
        setJoin("leftJoin", associationPath, alias);
        return this;
    }

    public Criteria rightJoin(String associationPath) {
        return this.rightJoin(associationPath, associationPath);
    }

    public Criteria rightJoin(String associationPath, String alias) {
        setJoin("rightJoin", associationPath, alias);
        return this;
    }

    public Criteria innerJoin(String associationPath) {
        return this.innerJoin(associationPath, associationPath);
    }

    public Criteria innerJoin(String associationPath, String alias) {
        setJoin("innerJoin", associationPath, alias);
        return this;
    }

    public Criteria fullJoin(String associationPath) {
        return this.fullJoin(associationPath, associationPath);
    }

    public Criteria fullJoin(String associationPath, String alias) {
        setJoin("fullJoin", associationPath, alias);
        return this;
    }


    //-------------------------- OrderBy --------------------------
    public Criteria asc(String propertyName) {
        set("asc", propertyName);
        return this;
    }

    public Criteria desc(String propertyName) {
        set("desc", propertyName);
        return this;
    }

    //-------------------------- GroupBy --------------------------
    private void setGroupBy(String expression, String propertyName, String alias) {
        Criterion criterion = Criterion.create();
        criterion.setExpression(expression);
        criterion.setPropertyName(propertyName);
        criterion.setAlias(alias);
        criterionList.add(criterion);
    }

    public Criteria groupBy(String propertyName) {
        setGroupBy("groupBy", propertyName, null);
        return this;
    }

    public Criteria countAll() {
        this.countAll(null);
        return this;
    }

    public Criteria countAll(String alias) {
        setGroupBy("countAll", null, alias);
        return this;
    }

    public Criteria count(String propertyName) {
        this.count(propertyName, null);
        return this;
    }

    public Criteria count(String propertyName, String alias) {
        setGroupBy("count", propertyName, alias);
        return this;
    }

    public Criteria max(String propertyName) {
        this.max(propertyName, null);
        return this;
    }

    public Criteria max(String propertyName, String alias) {
        setGroupBy("max", propertyName, alias);
        return this;
    }

    public Criteria min(String propertyName) {
        this.min(propertyName, null);
        return this;
    }

    public Criteria min(String propertyName, String alias) {
        setGroupBy("min", propertyName, alias);
        return this;
    }

    public Criteria sum(String propertyName) {
        this.sum(propertyName, null);
        return this;
    }

    public Criteria sum(String propertyName, String alias) {
        setGroupBy("sum", propertyName, alias);
        return this;
    }

    public Criteria avg(String propertyName) {
        this.avg(propertyName, null);
        return this;
    }

    public Criteria avg(String propertyName, String alias) {
        setGroupBy("avg", propertyName, alias);
        return this;
    }

    public Criteria distinct(String... properties) {
        Criterion criterion = Criterion.create();
        criterion.setExpression("distinct");
        criterion.setPropertyNames(properties);
        criterionList.add(criterion);
        return this;
    }

    public Criteria distinct() {
        this.distinct((String[]) null);
        return this;
    }

    //    public static Criteria create(Class clazz) {
//        return new Criteria(clazz);
//    }
//
//    public static CriteriaModel create(Object model) {
//        return new CriteriaModel(model);
//    }
//
//    protected DetachedCriteria detachedCriteria;
//
//    private List<String> aliasList;
//    private ProjectionList projectionList;
//    protected List<String> ascList;
//    protected List<String> descList;
//
//    public DetachedCriteria getDetachedCriteria() {
//        return detachedCriteria;
//    }
//
//    public List<String> getAliasList() {
//        if (aliasList == null)
//            aliasList = new ArrayList<String>();
//        return aliasList;
//    }
//
//    public ProjectionList getProjectionList() {
//        if (projectionList == null)
//            projectionList = Projections.projectionList();
//        return projectionList;
//    }
//
//    abstract void generateOrderBy();
}


