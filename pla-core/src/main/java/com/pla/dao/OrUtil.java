package com.pla.dao;

import com.pla.query.SQLCriterion;
import com.pla.utils.TypeUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrUtil {
    private List<org.hibernate.criterion.Criterion> orList = new ArrayList<org.hibernate.criterion.Criterion>();

    public static List<org.hibernate.criterion.Criterion> convert(Or or) {
        try {
            OrUtil orUtil = new OrUtil();
            List<OrCriterion> criterionList = or.getOrCriterionList();
            for (OrCriterion criterion : criterionList) {
                Method method = orUtil.getClass().getDeclaredMethod(criterion.getExpression(), OrCriterion.class);
                method.invoke(orUtil, criterion);
            }
            return orUtil.orList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert 'or' criteria.");
        }
    }

    public OrUtil idEq(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.idEq(criterion.getValue()));
        return this;
    }

    public OrUtil eq(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.eq(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil eqOrIsNull(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.eqOrIsNull(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil ne(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.ne(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil neOrIsNotNull(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.neOrIsNotNull(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil like(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE));
        return this;
    }

    public OrUtil startLike(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START));
        return this;
    }

    public OrUtil endLike(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END));
        return this;
    }

    public OrUtil ilike(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE));
        return this;
    }

    public OrUtil startIlike(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START));
        return this;
    }

    public OrUtil endIlike(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END));
        return this;
    }

    public OrUtil gt(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.gt(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil lt(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.lt(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil le(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.le(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil ge(OrCriterion criterion) {
        if (criterion.getValue() != null)
            orList.add(Restrictions.ge(criterion.getPropertyName(), criterion.getValue()));
        return this;
    }

    public OrUtil between(OrCriterion criterion) {
        Object[] values = criterion.getValues();
        if (values == null || values.length != 2)
            return this;
        Object lo = values[0];
        Object hi = values[1];
        if (lo != null && hi != null)
            orList.add(Restrictions.between(criterion.getPropertyName(), lo, hi));
        return this;
    }

    public OrUtil in(OrCriterion criterion) {
        if (criterion.getValues() != null)
            orList.add(Restrictions.in(criterion.getPropertyName(), criterion.getValues()));
        return this;
    }

    public OrUtil isNull(OrCriterion criterion) {
        orList.add(Restrictions.isNull(criterion.getPropertyName()));
        return this;
    }

    public OrUtil isNotNull(OrCriterion criterion) {
        orList.add(Restrictions.isNotNull(criterion.getPropertyName()));
        return this;
    }

    public OrUtil eqProperty(OrCriterion criterion) {
        orList.add(Restrictions.eqProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil neProperty(OrCriterion criterion) {
        orList.add(Restrictions.neProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil ltProperty(OrCriterion criterion) {
        orList.add(Restrictions.ltProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil leProperty(OrCriterion criterion) {
        orList.add(Restrictions.leProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil gtProperty(OrCriterion criterion) {
        orList.add(Restrictions.gtProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil geProperty(OrCriterion criterion) {
        orList.add(Restrictions.geProperty(criterion.getPropertyName(), criterion.getOtherPropertyName()));
        return this;
    }

    public OrUtil sqlRestriction(OrCriterion criterion) {
        if (criterion.getValues() != null) {
            List<Type> types = new ArrayList<Type>();
            for (Object val : criterion.getValues()) {
                Type type = TypeUtil.getType(val);
                if (type != null) {
                    types.add(type);
                }
            }
            orList.add(new SQLCriterion(criterion.getSql(), criterion.getValues(), types.toArray(new Type[0])));
        } else {
            orList.add(new SQLCriterion(criterion.getSql(), ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        }
        return this;
    }

    public OrUtil isEmpty(OrCriterion criterion) {
        orList.add(Restrictions.isEmpty(criterion.getPropertyName()));
        return this;
    }

    public OrUtil isNotEmpty(OrCriterion criterion) {
        orList.add(Restrictions.isNotEmpty(criterion.getPropertyName()));
        return this;
    }

    public OrUtil sizeEq(OrCriterion criterion) {
        orList.add(Restrictions.sizeEq(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public OrUtil sizeNe(OrCriterion criterion) {
        orList.add(Restrictions.sizeNe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public OrUtil sizeGt(OrCriterion criterion) {
        orList.add(Restrictions.sizeGt(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public OrUtil sizeLt(OrCriterion criterion) {
        orList.add(Restrictions.sizeLt(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public OrUtil sizeGe(OrCriterion criterion) {
        orList.add(Restrictions.sizeGe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }

    public OrUtil sizeLe(OrCriterion criterion) {
        orList.add(Restrictions.sizeLe(criterion.getPropertyName(), (Integer) criterion.getValue()));
        return this;
    }
}
