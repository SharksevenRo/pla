package com.pla.dao.support;

import com.pla.query.Or;
import com.pla.query.SQLCriterion;
import com.pla.utils.TypeUtil;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CriteriaConvertor {
    private org.hibernate.Criteria criteria;

    private ProjectionList projectionList;
    private List<String> aliasList;

    private CriteriaConvertor(org.hibernate.Criteria criteria) {
        this.criteria = criteria;
    }

    public static CriteriaConvertor getInstance(org.hibernate.Criteria criteria) {
        return new CriteriaConvertor(criteria);
    }

    public org.hibernate.Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(org.hibernate.Criteria criteria) {
        this.criteria = criteria;
    }

    public org.hibernate.Criteria convert(Criteria criteria) throws Exception {
        this.convertWithOutOrder(criteria);
        this.convertOrderBy(criteria);
        return this.criteria;
    }

    public org.hibernate.Criteria convertWithOutOrder(Criteria criteria) throws Exception {
        Class clazz = this.getClass();

        List<Criterion> criteriaTmp = criteria.criteria;
        for (Criterion criterion : criteriaTmp) {
            Method method = clazz.getDeclaredMethod(criterion.getExpression(), Criterion.class);
            method.invoke(this, criterion);
        }

        List<Join> joins = criteria.joins;
        if (joins != null && !joins.isEmpty()) {
            for (Join join : joins) {
                if (join.criterion == null) {
                    this.criteria.createAlias(join.associationPath, join.alias, join.joinType);
                } else {
                    this.criteria.createAlias(join.associationPath, join.alias, join.joinType, join.criterion);
                }
            }
        }

        List<GroupBy> groupBys = criteria.groupBys;
        if (groupBys != null && !groupBys.isEmpty()) {
            for (GroupBy groupBy : groupBys) {
                Method method = clazz.getDeclaredMethod(groupBy.getExpression(), GroupBy.class);
                method.invoke(this, groupBy);
            }
        }

        return this.criteria;
    }

    private void convertOrderBy(Criteria criteria) {
        List<OrderBy> orderBys = criteria.orderBys;

        if (orderBys != null && !orderBys.isEmpty()) {
            for (OrderBy orderBy : orderBys) {
                if (orderBy.order == Order.ASC) {
                    this.criteria.addOrder(org.hibernate.criterion.Order.asc(orderBy.propertyName));
                } else if (orderBy.order == Order.DESC) {
                    this.criteria.addOrder(org.hibernate.criterion.Order.desc(orderBy.propertyName));
                }
            }
        }
    }

    public void idEq(Criterion criterion) {
        this.criteria.add(Restrictions.idEq(criterion.getValue()));
    }

    public void eq(Criterion criterion) {
        this.criteria.add(Restrictions.eq(criterion.getPropertyName(), criterion.getValue()));
    }

    public void eqOrIsNull(Criterion criterion) {
        this.criteria.add(Restrictions.eqOrIsNull(criterion.getPropertyName(), criterion.getValue()));
    }

    public void ne(Criterion criterion) {
        this.criteria.add(Restrictions.ne(criterion.getPropertyName(), criterion.getValue()));
    }

    public void neOrIsNotNull(Criterion criterion) {
        this.criteria.add(Restrictions.neOrIsNotNull(criterion.getPropertyName(), criterion.getValue()));
    }

    public void like(Criterion criterion) {
        this.criteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.ANYWHERE));
    }

    public void startLike(Criterion criterion) {
        this.criteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.START));
    }

    public void endLike(Criterion criterion) {
        this.criteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.END));
    }

    public void ilike(Criterion criterion) {
        this.criteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.ANYWHERE));
    }

    public void startIlike(Criterion criterion) {
        this.criteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.START));
    }

    public void endIlike(Criterion criterion) {
        this.criteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(), MatchMode.END));
    }

    public void gt(Criterion criterion) {
        this.criteria.add(Restrictions.gt(criterion.getPropertyName(), criterion.getValue()));
    }

    public void lt(Criterion criterion) {
        this.criteria.add(Restrictions.lt(criterion.getPropertyName(), criterion.getValue()));
    }

    public void le(Criterion criterion) {
        this.criteria.add(Restrictions.le(criterion.getPropertyName(), criterion.getValue()));
    }

    public void ge(Criterion criterion) {
        this.criteria.add(Restrictions.ge(criterion.getPropertyName(), criterion.getValue()));
    }

    public void between(Criterion criterion) {
        if (criterion.getValue() instanceof Object[]) {
            Object[] value = (Object[]) criterion.getValue();
            if (value.length == 2)
                this.criteria.add(Restrictions.between(criterion.getPropertyName(), value[0], value[1]));
        }
    }

    public void in(Criterion criterion) {
        if (criterion.getValue() instanceof Object[]) {
            this.criteria.add(Restrictions.in(criterion.getPropertyName(), (Object[]) criterion.getValue()));
        } else if (criterion.getValue() instanceof Collection) {
            this.criteria.add(Restrictions.in(criterion.getPropertyName(), (Collection) criterion.getValue()));
        }
    }

    public void isNull(Criterion criterion) {
        this.criteria.add(Restrictions.isNull(criterion.getPropertyName()));
    }

    public void isNotNull(Criterion criterion) {
        this.criteria.add(Restrictions.isNotNull(criterion.getPropertyName()));
    }

    public void eqProperty(Criterion criterion) {
        this.criteria.add(Restrictions.eqProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void neProperty(Criterion criterion) {
        this.criteria.add(Restrictions.neProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void ltProperty(Criterion criterion) {
        this.criteria.add(Restrictions.ltProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void leProperty(Criterion criterion) {
        this.criteria.add(Restrictions.leProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void gtProperty(Criterion criterion) {
        this.criteria.add(Restrictions.gtProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void geProperty(Criterion criterion) {
        this.criteria.add(Restrictions.geProperty(criterion.getPropertyName(), (String) criterion.getValue()));
    }

    public void sqlRestriction(Criterion criterion) {
        String sql = criterion.getPropertyName();
        if (criterion.getValue() == null) {
            this.criteria.add(new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        } else if (criterion.getValue() instanceof Object[]) {
            Object[] values = (Object[]) criterion.getValue();
            if (values != null) {

                List<Object> vals = new ArrayList<Object>();
                List<Type> types = new ArrayList<Type>();
                for (Object val : values) {
                    Type type = TypeUtil.getType(val);
                    if (type != null) {
                        vals.add(criterion.getValue());
                        types.add(type);
                    }
                }
                this.criteria.add(new SQLCriterion(sql, vals.toArray(), types.toArray(new Type[0])));
            }
        }
    }

    public void allEq(Criterion criterion) {
        this.criteria.add(Restrictions.allEq((Map) criterion.getValue()));
    }

    public void isEmpty(Criterion criterion) {
        this.criteria.add(Restrictions.isEmpty(criterion.getPropertyName()));
    }

    public void isNotEmpty(Criterion criterion) {
        this.criteria.add(Restrictions.isNotEmpty(criterion.getPropertyName()));
    }

    public void sizeEq(Criterion criterion) {
        this.criteria.add(Restrictions.sizeEq(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void sizeNe(Criterion criterion) {
        this.criteria.add(Restrictions.sizeNe(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void sizeGt(Criterion criterion) {
        this.criteria.add(Restrictions.sizeGt(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void sizeLt(Criterion criterion) {
        this.criteria.add(Restrictions.sizeLt(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void sizeGe(Criterion criterion) {
        this.criteria.add(Restrictions.sizeGe(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void sizeLe(Criterion criterion) {
        this.criteria.add(Restrictions.sizeLe(criterion.getPropertyName(), (Integer) criterion.getValue()));
    }

    public void or(Criterion criterion) {
        if (criterion.getValue() instanceof Or) {
            Or or = (Or) criterion.getValue();
            List<org.hibernate.criterion.Criterion> list = or.getOrList();
            if (list != null && !list.isEmpty()) {
                criteria.add(Restrictions.or(list.toArray(new org.hibernate.criterion.Criterion[0])));
            }
        }
    }

    public List<String> getAliasList() {
        if (aliasList == null)
            aliasList = new ArrayList<String>();
        return aliasList;
    }

    public ProjectionList getProjectionList() {
        if (projectionList == null)
            projectionList = Projections.projectionList();
        return projectionList;
    }

    public void groupBy(GroupBy groupBy) {
        getProjectionList().add(Projections.groupProperty(groupBy.getPropertyName()));
        this.criteria.setProjection(getProjectionList());
        getAliasList().add(groupBy.getPropertyName());
    }

    public void countAll(GroupBy groupBy) {
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.rowCount(), alias);
        else
            getProjectionList().add(Projections.rowCount());
        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add("cnt");
        else
            getAliasList().add(alias);
    }

    public void count(GroupBy groupBy) {
        String propertyName = groupBy.getPropertyName();
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.count(propertyName), alias);
        else
            getProjectionList().add(Projections.count(propertyName));
        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
    }

    public void max(GroupBy groupBy) {
        String propertyName = groupBy.getPropertyName();
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.max(propertyName), alias);
        else
            getProjectionList().add(Projections.max(propertyName));

        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
    }

    public void min(GroupBy groupBy) {
        String propertyName = groupBy.getPropertyName();
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.min(propertyName), alias);
        else
            getProjectionList().add(Projections.min(propertyName));
        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
    }

    public void sum(GroupBy groupBy) {
        String propertyName = groupBy.getPropertyName();
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.sum(propertyName), alias);
        else
            getProjectionList().add(Projections.sum(propertyName));
        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);
        else
            getAliasList().add(alias);
    }

    public void avg(GroupBy groupBy) {
        String propertyName = groupBy.getPropertyName();
        String alias = (String) groupBy.getValue();
        if (alias != null)
            getProjectionList().add(Projections.avg(propertyName), alias);
        else
            getProjectionList().add(Projections.avg(propertyName));
        this.criteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(propertyName);

        else
            getAliasList().add(alias);
    }

    public void distinct(GroupBy groupBy) {
        /*if (groupBy.getValue() == null) {
            Field[] fields = ModelUtil.getEntityProperties(this.clazz);
            ProjectionList projectionList = Projections.projectionList();
            for (Field field : fields) {
                projectionList.add(Projections.property(field.getName()));
                getAliasList().add(field.getName());
            }
            this.criteria.setProjection(Projections.distinct(projectionList));
        } else*/
        if (groupBy.getValue() != null && groupBy.getValue() instanceof String[]) {
            String[] properties = (String[]) groupBy.getValue();
            ProjectionList projectionList = Projections.projectionList();
            for (String property : properties) {
                projectionList.add(Projections.property(property));
                getAliasList().add(property);
            }
            this.criteria.setProjection(Projections.distinct(projectionList));
        }
    }

}
