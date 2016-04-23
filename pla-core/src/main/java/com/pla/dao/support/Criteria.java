package com.pla.dao.support;

import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;

public abstract class Criteria {
    public static CriteriaClazz create() {
        return new CriteriaClazz();
    }

    public static CriteriaModel create(Object model) {
        return new CriteriaModel(model);
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

    void addOrderBy(String properyName, Order order) {
        if (orderBys == null) {
            orderBys = new ArrayList<OrderBy>();
        }
        orderBys.add(new OrderBy(properyName, order));
    }

    void addGroupBy(String expression, String propertyName, Object value) {
        if (groupBys == null) {
            groupBys = new ArrayList<GroupBy>();
        }
        groupBys.add(new GroupBy(expression, propertyName, value));
    }
}


