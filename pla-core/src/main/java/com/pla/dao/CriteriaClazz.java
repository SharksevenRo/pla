package com.pla.dao;

import com.pla.query.Or;
import com.pla.query.SQLCriterion;
import com.pla.utils.ModelUtil;
import com.pla.utils.TypeUtil;
import org.hibernate.criterion.*;
import org.hibernate.criterion.Order;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.sql.JoinType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CriteriaClazz extends Criteria {
    private Class clazz;

    public CriteriaClazz(Class clazz) {
        this.clazz = clazz;
        detachedCriteria = DetachedCriteria.forClass(clazz);
    }

    public CriteriaClazz idEq(Serializable value) {
        detachedCriteria.add(Restrictions.idEq(value));
        return this;
    }

    public CriteriaClazz eq(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.eq(propertyName, value));
        return this;
    }

    public CriteriaClazz eqOrIsNull(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.eqOrIsNull(propertyName, value));
        return this;
    }

    public CriteriaClazz ne(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.ne(propertyName, value));
        return this;
    }

    public CriteriaClazz neOrIsNotNull(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }


    public CriteriaClazz like(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public CriteriaClazz startLike(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.like(propertyName, value, MatchMode.START));
        return this;
    }

    public CriteriaClazz endLike(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.like(propertyName, value, MatchMode.END));
        return this;
    }

    public CriteriaClazz ilike(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public CriteriaClazz startIlike(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.ilike(propertyName, value, MatchMode.START));
        return this;
    }

    public CriteriaClazz endIlike(String propertyName, String value) {
        if (value != null)
            detachedCriteria.add(Restrictions.ilike(propertyName, value, MatchMode.END));
        return this;
    }

    public CriteriaClazz gt(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.gt(propertyName, value));
        return this;
    }

    public CriteriaClazz lt(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.lt(propertyName, value));
        return this;
    }

    public CriteriaClazz le(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.le(propertyName, value));
        return this;
    }

    public CriteriaClazz ge(String propertyName, Object value) {
        if (value != null)
            detachedCriteria.add(Restrictions.ge(propertyName, value));
        return this;
    }

    public CriteriaClazz between(String propertyName, Object lo, Object hi) {
        if (lo != null && hi != null)
            detachedCriteria.add(Restrictions.between(propertyName, lo, hi));
        return this;
    }

    public CriteriaClazz in(String propertyName, Object[] values) {
        if (values != null)
            detachedCriteria.add(Restrictions.in(propertyName, values));
        return this;
    }

    public CriteriaClazz in(String propertyName, Collection values) {
        if (values != null)
            detachedCriteria.add(Restrictions.in(propertyName, values));
        return this;
    }

    public CriteriaClazz isNull(String propertyName) {
        detachedCriteria.add(Restrictions.isNull(propertyName));
        return this;
    }

    public CriteriaClazz isNotNull(String propertyName) {
        detachedCriteria.add(Restrictions.isNotNull(propertyName));
        return this;
    }

    public CriteriaClazz eqProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.eqProperty(propertyName, otherPropertyName));
        return this;
    }

    public CriteriaClazz neProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.neProperty(propertyName, otherPropertyName));
        return this;
    }

    public CriteriaClazz ltProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.ltProperty(propertyName, otherPropertyName));
        return this;
    }

    public CriteriaClazz leProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.leProperty(propertyName, otherPropertyName));
        return this;
    }

    public CriteriaClazz gtProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.gtProperty(propertyName, otherPropertyName));
        return this;
    }

    public CriteriaClazz geProperty(String propertyName, String otherPropertyName) {
        detachedCriteria.add(Restrictions.geProperty(propertyName, otherPropertyName));
        return this;
    }


    public CriteriaClazz sqlRestriction(String sql, Object... values) {
        if (values != null) {
            List<Type> types = new ArrayList<Type>();
            for (Object val : values) {
                Type type = TypeUtil.getType(val);
                if (type != null) {
                    types.add(type);
                }
            }
            detachedCriteria.add(new SQLCriterion(sql, values, types.toArray(new Type[0])));
        }
        return this;
    }

    public CriteriaClazz sqlRestriction(String sql) {
        detachedCriteria.add(new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        return this;
    }

    public CriteriaClazz allEq(Map propertyNameValues) {
        detachedCriteria.add(Restrictions.allEq(propertyNameValues));
        return this;
    }

    public CriteriaClazz isEmpty(String propertyName) {
        detachedCriteria.add(Restrictions.isEmpty(propertyName));
        return this;
    }

    public CriteriaClazz isNotEmpty(String propertyName) {
        detachedCriteria.add(Restrictions.isNotEmpty(propertyName));
        return this;
    }

    public CriteriaClazz sizeEq(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeEq(propertyName, size));
        return this;
    }

    public CriteriaClazz sizeNe(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeNe(propertyName, size));
        return this;
    }

    public CriteriaClazz sizeGt(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeGt(propertyName, size));
        return this;
    }

    public CriteriaClazz sizeLt(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeLt(propertyName, size));
        return this;
    }

    public CriteriaClazz sizeGe(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeGe(propertyName, size));
        return this;
    }

    public CriteriaClazz sizeLe(String propertyName, int size) {
        detachedCriteria.add(Restrictions.sizeLe(propertyName, size));
        return this;
    }

    public CriteriaClazz or(Or or) {
        List<org.hibernate.criterion.Criterion> list = or.getOrList();
        if (list != null && !list.isEmpty()) {
            detachedCriteria.add(Restrictions.or(list.toArray(new org.hibernate.criterion.Criterion[0])));
        }
        return this;
    }

    //-------------------------- Join --------------------------
    public CriteriaClazz join(String associationPath) {
        return this.join(associationPath, associationPath);
    }

    public CriteriaClazz join(String associationPath, String alias) {
        return this.leftJoin(associationPath, alias);
    }

    public CriteriaClazz leftJoin(String associationPath) {
        return this.leftJoin(associationPath, associationPath);
    }

    public CriteriaClazz leftJoin(String associationPath, String alias) {
        detachedCriteria.createAlias(associationPath, alias, JoinType.LEFT_OUTER_JOIN);
        return this;
    }

    public CriteriaClazz rightJoin(String associationPath) {
        return this.rightJoin(associationPath, associationPath);
    }

    public CriteriaClazz rightJoin(String associationPath, String alias) {
        detachedCriteria.createAlias(associationPath, alias, JoinType.RIGHT_OUTER_JOIN);
        return this;
    }

    public CriteriaClazz innerJoin(String associationPath) {
        return this.innerJoin(associationPath, associationPath);
    }

    public CriteriaClazz innerJoin(String associationPath, String alias) {
        detachedCriteria.createAlias(associationPath, alias, JoinType.INNER_JOIN);
        return this;
    }

    public CriteriaClazz fullJoin(String associationPath) {
        return this.fullJoin(associationPath, associationPath);
    }

    public CriteriaClazz fullJoin(String associationPath, String alias) {
        detachedCriteria.createAlias(associationPath, alias, JoinType.FULL_JOIN);
        return this;
    }


    //-------------------------- OrderBy --------------------------
    public CriteriaClazz asc(String propertyName) {
        if (ascList == null)
            ascList = new ArrayList<String>();
        ascList.add(propertyName);
        return this;
    }

    public CriteriaClazz desc(String propertyName) {
        if (descList == null)
            descList = new ArrayList<String>();
        descList.add(propertyName);
        return this;
    }

    @Override
    void generateOrderBy() {
        if (ascList != null) {
            for (String asc : ascList) {
                detachedCriteria.addOrder(org.hibernate.criterion.Order.asc(asc));
            }
        }
        if (descList != null) {
            for (String desc : descList) {
                detachedCriteria.addOrder(Order.desc(desc));
            }
        }
    }

    //-------------------------- GroupBy --------------------------
    public CriteriaClazz groupBy(String propertyName) {
        ProjectionList projections = this.getProjectionList();
        projections.add(Projections.groupProperty(propertyName));
        detachedCriteria.setProjection(projections);
        getAliasList().add(propertyName);
        return this;
    }

    public CriteriaClazz countAll() {
        this.countAll(null);
        return this;
    }

    public CriteriaClazz countAll(String alias) {
        ProjectionList projections = this.getProjectionList();
        if (alias != null)
            projections.add(Projections.rowCount(), alias);
        else
            projections.add(Projections.rowCount());
        detachedCriteria.setProjection(projections);
        if (alias == null)
            getAliasList().add("cnt");
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz count(String propertyName) {
        this.count(propertyName, null);
        return this;
    }

    public CriteriaClazz count(String propertyName, String alias) {
        ProjectionList projections = this.getProjectionList();
        if (alias != null)
            projections.add(Projections.count(propertyName), alias);
        else
            projections.add(Projections.count(propertyName));
        detachedCriteria.setProjection(projections);
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz max(String propertyName) {
        this.max(propertyName, null);
        return this;
    }

    public CriteriaClazz max(String propertyName, String alias) {
        if (alias != null)
            getProjectionList().add(Projections.max(propertyName), alias);
        else
            getProjectionList().add(Projections.max(propertyName));

        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz min(String propertyName) {
        this.min(propertyName, null);
        return this;
    }

    public CriteriaClazz min(String propertyName, String alias) {
        if (alias != null)
            getProjectionList().add(Projections.min(propertyName), alias);
        else
            getProjectionList().add(Projections.min(propertyName));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz sum(String propertyName) {
        this.sum(propertyName, null);
        return this;
    }

    public CriteriaClazz sum(String propertyName, String alias) {
        if (alias != null)
            getProjectionList().add(Projections.sum(propertyName), alias);
        else
            getProjectionList().add(Projections.sum(propertyName));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz avg(String propertyName) {
        this.avg(propertyName, null);
        return this;
    }

    public CriteriaClazz avg(String propertyName, String alias) {
        if (alias != null)
            getProjectionList().add(Projections.avg(propertyName), alias);
        else
            getProjectionList().add(Projections.avg(propertyName));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);

        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaClazz distinct(String... properties) {
        ProjectionList projectionList = Projections.projectionList();
        for (String property : properties) {
            projectionList.add(Projections.property(property));
            getAliasList().add(property);
        }
        detachedCriteria.setProjection(Projections.distinct(projectionList));
        return this;
    }

    public CriteriaClazz distinct() {
        Field[] fields = ModelUtil.getEntityProperties(this.clazz);
        ProjectionList projectionList = Projections.projectionList();
        for (Field field : fields) {
            projectionList.add(Projections.property(field.getName()));
            getAliasList().add(field.getName());
        }
        detachedCriteria.setProjection(Projections.distinct(projectionList));
        return this;
    }

}
