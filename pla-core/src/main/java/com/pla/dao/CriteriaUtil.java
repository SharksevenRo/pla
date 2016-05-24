package com.pla.dao;

import com.pla.query.SQLCriterion;
import com.pla.utils.ModelUtil;
import com.pla.utils.TypeUtil;
import org.hibernate.criterion.*;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.sql.JoinType;
import org.hibernate.type.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CriteriaUtil {
    private DetachedCriteria detachedCriteria;
    private Class clazz;

    public CriteriaUtil(Class clazz) {
        this.clazz = clazz;
        detachedCriteria = DetachedCriteria.forClass(clazz);
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public static DetachedCriteria convert(Criteria criteria) {
        try {
            CriteriaUtil criteriaUtil = new CriteriaUtil(criteria.getClazz());
            List<Criterion> criterionList = criteria.getCriterionList();
            for (Criterion criterion : criterionList) {
                Method method = criteriaUtil.getClass().getDeclaredMethod(criterion.getExpression(), Criterion.class);
                method.invoke(criteriaUtil, criterion);
            }
            return criteriaUtil.getDetachedCriteria();
        } catch (Exception e) {
            return null;
        }
    }

    public static CriteriaUtil create(Criteria criteria) {
        try {
            CriteriaUtil criteriaUtil = new CriteriaUtil(criteria.getClazz());
            List<Criterion> criterionList = criteria.getCriterionList();
            for (Criterion criterion : criterionList) {
                Method method = criteriaUtil.getClass().getDeclaredMethod(criterion.getExpression(), Criterion.class);
                method.invoke(criteriaUtil, criterion);
            }
            return criteriaUtil;
        } catch (Exception e) {
            return null;
        }
    }

    public CriteriaUtil idEq(Criterion criterion) {
        detachedCriteria.add(Restrictions.idEq(criterion.getValue()));
        return this;
    }

    public CriteriaUtil eq(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.eq(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil eqOrIsNull(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.eqOrIsNull(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil ne(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.ne(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil neOrIsNotNull(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.neOrIsNotNull(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil like(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE));
        return this;
    }

    public CriteriaUtil startLike(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START));
        return this;
    }

    public CriteriaUtil endLike(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END));
        return this;
    }

    public CriteriaUtil ilike(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE));
        return this;
    }

    public CriteriaUtil startIlike(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START));
        return this;
    }

    public CriteriaUtil endIlike(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END));
        return this;
    }

    public CriteriaUtil gt(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.gt(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil lt(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.lt(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil le(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.le(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil ge(Criterion criterion) {
        if (criterion.getValue() != null)
            detachedCriteria.add(Restrictions.ge(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public CriteriaUtil between(Criterion criterion) {
        Object[] values = criterion.getValues();
        if (values == null || values.length != 2)
            return this;
        Object lo = values[0];
        Object hi = values[1];
        if (lo != null && hi != null)
            detachedCriteria.add(Restrictions.between(criterion.getPropertyName(), lo, hi));
        return this;
    }

    public CriteriaUtil in(Criterion criterion) {
        if (criterion.getValues() != null)
            detachedCriteria.add(Restrictions.in(criterion.getPropertyName(), criterion.getValues()));
        return this;
    }

    public CriteriaUtil isNull(Criterion criterion) {
        detachedCriteria.add(Restrictions.isNull(criterion.getPropertyName()));
        return this;
    }

    public CriteriaUtil isNotNull(Criterion criterion) {
        detachedCriteria.add(Restrictions.isNotNull(criterion.getPropertyName()));
        return this;
    }

    public CriteriaUtil eqProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.eqProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil neProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.neProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil ltProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.ltProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil leProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.leProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil gtProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.gtProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil geProperty(Criterion criterion) {
        detachedCriteria.add(Restrictions.geProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public CriteriaUtil sqlRestriction(Criterion criterion) {
        if (criterion.getValues() != null) {
            List<Type> types = new ArrayList<Type>();
            for (Object val : criterion.getValues()) {
                Type type = TypeUtil.getType(val);
                if (type != null) {
                    types.add(type);
                }
            }
            detachedCriteria.add(new SQLCriterion(criterion.getSql(), criterion.getValues(), types.toArray(new Type[0])));
        } else {
            detachedCriteria.add(new SQLCriterion(criterion.getSql(), ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        }
        return this;
    }

    public CriteriaUtil isEmpty(Criterion criterion) {
        detachedCriteria.add(Restrictions.isEmpty(criterion.getPropertyName()));
        return this;
    }

    public CriteriaUtil isNotEmpty(Criterion criterion) {
        detachedCriteria.add(Restrictions.isNotEmpty(criterion.getPropertyName()));
        return this;
    }

    public CriteriaUtil sizeEq(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeEq(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public CriteriaUtil sizeNe(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeNe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public CriteriaUtil sizeGt(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeGt(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public CriteriaUtil sizeLt(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeLt(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public CriteriaUtil sizeGe(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeGe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public CriteriaUtil sizeLe(Criterion criterion) {
        detachedCriteria.add(Restrictions.sizeLe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }
    //TODO
//    public CriteriaUtil or(Criterion criterion) {
//        Or or = criterion.getOr();
//
//        List<org.hibernate.criterion.Criterion> list = criterion.getOr().getOrList();
//        if (list != null && !list.isEmpty()) {
//            detachedCriteria.add(Restrictions.or(list.toArray(new org.hibernate.criterion.Criterion[0])));
//        }
//        return this;
//    }
//
    //-------------------------- Join --------------------------

    public CriteriaUtil join(Criterion criterion) {
        return this.leftJoin(criterion);
    }

    public CriteriaUtil leftJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        detachedCriteria.createAlias(criterion.getPropertyName(), alias, JoinType.LEFT_OUTER_JOIN);
        return this;
    }

    public CriteriaUtil rightJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        detachedCriteria.createAlias(criterion.getPropertyName(), alias, JoinType.RIGHT_OUTER_JOIN);
        return this;
    }

    public CriteriaUtil innerJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        detachedCriteria.createAlias(criterion.getPropertyName(), alias, JoinType.INNER_JOIN);
        return this;
    }

    public CriteriaUtil fullJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        detachedCriteria.createAlias(criterion.getPropertyName(), alias, JoinType.FULL_JOIN);
        return this;
    }


    //-------------------------- OrderBy --------------------------
    private List<String> ascList;
    private List<String> descList;

    public CriteriaUtil asc(Criterion criterion) {
        if (ascList == null)
            ascList = new ArrayList<String>();
        ascList.add(criterion.getPropertyName());
        return this;
    }

    public CriteriaUtil desc(Criterion criterion) {
        if (descList == null)
            descList = new ArrayList<String>();
        descList.add(criterion.getPropertyName());
        return this;
    }

    void generateOrderBy() {
        if (ascList != null) {
            for (String asc : ascList) {
                detachedCriteria.addOrder(Order.asc(asc));
            }
        }
        if (descList != null) {
            for (String desc : descList) {
                detachedCriteria.addOrder(Order.desc(desc));
            }
        }
    }

    //-------------------------- GroupBy --------------------------
    private ProjectionList projectionList;
    private List<String> aliasList;

    private ProjectionList getProjectionList() {
        if (projectionList == null)
            projectionList = Projections.projectionList();
        return projectionList;
    }

    public List<String> getAliasList() {
        if (aliasList == null)
            aliasList = new ArrayList<String>();
        return aliasList;
    }

    public CriteriaUtil groupBy(Criterion criterion) {
        ProjectionList projections = this.getProjectionList();
        projections.add(Projections.groupProperty(criterion.getPropertyName()));
        detachedCriteria.setProjection(projections);
        getAliasList().add(criterion.getPropertyName());
        return this;
    }

    public CriteriaUtil countAll(Criterion criterion) {
        String alias = criterion.getAlias();
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

    public CriteriaUtil count(Criterion criterion) {
        String alias = criterion.getAlias();
        ProjectionList projections = this.getProjectionList();
        if (alias != null)
            projections.add(Projections.count(criterion.getPropertyName()), alias);
        else
            projections.add(Projections.count(criterion.getPropertyName()));
        detachedCriteria.setProjection(projections);
        if (alias == null)
            getAliasList().add(criterion.getPropertyName());
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaUtil max(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias != null)
            getProjectionList().add(Projections.max(criterion.getPropertyName()), alias);
        else
            getProjectionList().add(Projections.max(criterion.getPropertyName()));

        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(criterion.getPropertyName());
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaUtil min(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias != null)
            getProjectionList().add(Projections.min(criterion.getPropertyName()), alias);
        else
            getProjectionList().add(Projections.min(criterion.getPropertyName()));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(criterion.getPropertyName());
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaUtil sum(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias != null)
            getProjectionList().add(Projections.sum(criterion.getPropertyName()), alias);
        else
            getProjectionList().add(Projections.sum(criterion.getPropertyName()));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(criterion.getPropertyName());
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaUtil avg(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias != null)
            getProjectionList().add(Projections.avg(criterion.getPropertyName()), alias);
        else
            getProjectionList().add(Projections.avg(criterion.getPropertyName()));
        detachedCriteria.setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add(criterion.getPropertyName());
        else
            getAliasList().add(alias);
        return this;
    }

    public CriteriaUtil distinct(Criterion criterion) {
        String[] properties = criterion.getPropertyNames();
        if (properties != null) {
            ProjectionList projectionList = Projections.projectionList();
            for (String property : properties) {
                projectionList.add(Projections.property(property));
                getAliasList().add(property);
            }
            detachedCriteria.setProjection(Projections.distinct(projectionList));
        } else {
            Field[] fields = ModelUtil.getEntityProperties(clazz);
            ProjectionList projectionList = Projections.projectionList();
            for (Field field : fields) {
                projectionList.add(Projections.property(field.getName()));
                getAliasList().add(field.getName());
            }
            detachedCriteria.setProjection(Projections.distinct(projectionList));
        }
        return this;
    }
}
