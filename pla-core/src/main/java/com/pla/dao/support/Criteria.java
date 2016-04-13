package com.pla.dao.support;

import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machey on 2016/3/27.
 */
public abstract class Criteria {
    public static CriteriaClazz create() {
        CriteriaClazz criteriaClazz = new CriteriaClazz();
        return criteriaClazz;
    }

    public static CriteriaModel create(Object model) {
        CriteriaModel criteriaModel = new CriteriaModel(model);
        return criteriaModel;
    }

    List<Criterion> criterions = new ArrayList<Criterion>();
    List<Join> joins;
    List<OrderBy> orderBys;
    List<GroupBy> groupBys;

    protected void add(String expression, String propertyName, Object value) {
        criterions.add(new Criterion(expression, propertyName, value));
    }

    protected void addJoin(String associationPath, String alias, JoinType joinType) {
        if (joins == null) {
            joins = new ArrayList<Join>();
        }
        joins.add(new Join(associationPath, alias, joinType));
    }

    protected void addOrderBy(String properyName, Order order) {
        if (orderBys == null) {
            orderBys = new ArrayList<OrderBy>();
        }
        orderBys.add(new OrderBy(properyName, order));
    }

    protected void addGroupBy(String expression, String propertyName, Object value) {
        if (groupBys == null) {
            groupBys = new ArrayList<GroupBy>();
        }
        groupBys.add(new GroupBy(expression, propertyName, value));
    }
}


