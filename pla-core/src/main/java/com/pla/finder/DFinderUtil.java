package com.pla.finder;


import com.pla.dao.Criteria;
import com.pla.dao.Criterion;
import com.pla.dao.Or;
import com.pla.dao.OrUtil;
import com.pla.query.QueryByClass;
import org.hibernate.criterion.MatchMode;
import org.hibernate.internal.util.collections.ArrayHelper;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

public class DFinderUtil<M> {
    private QueryByClass<M> query;

    public static <M> DFinderUtil<M> create() {
        return new DFinderUtil<M>();
    }

    public QueryByClass<M> convert(Criteria criteria, Class<M> clazz) {
        try {
            query = new Finder<M>().from(clazz);
            if (criteria != null) {
                List<Criterion> criterionList = criteria.getCriterionList();
                for (Criterion criterion : criterionList) {
                    Method method = this.getClass().getDeclaredMethod(criterion.getExpression(), Criterion.class);
                    method.invoke(this, criterion);
                }
            }
            return query;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert criteria.");
        }
    }

    public void idEq(Criterion criterion) {
        query.idEq((Serializable) criterion.getValue());
    }

    public void eq(Criterion criterion) {
        if (criterion.getValue() != null)
            query.eq(criterion.getPropertyName(), criterion.getValue());
    }

    public void eqOrIsNull(Criterion criterion) {
        if (criterion.getValue() != null)
            query.eqOrIsNull(criterion.getPropertyName(), criterion.getValue());
    }

    public void ne(Criterion criterion) {
        if (criterion.getValue() != null)
            query.ne(criterion.getPropertyName(), criterion.getValue());
    }

    public void neOrIsNotNull(Criterion criterion) {
        if (criterion.getValue() != null)
            query.neOrIsNotNull(criterion.getPropertyName(), criterion.getValue());
    }

    public void like(Criterion criterion) {
        if (criterion.getValue() != null)
            query.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE);
    }

    public void startLike(Criterion criterion) {
        if (criterion.getValue() != null)
            query.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START);
    }

    public void endLike(Criterion criterion) {
        if (criterion.getValue() != null)
            query.like(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END);
    }

    public void ilike(Criterion criterion) {
        if (criterion.getValue() != null)
            query.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.ANYWHERE);
    }

    public void startIlike(Criterion criterion) {
        if (criterion.getValue() != null)
            query.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.START);
    }

    public void endIlike(Criterion criterion) {
        if (criterion.getValue() != null)
            query.ilike(criterion.getPropertyName(), (String) criterion.getValue(),
                    MatchMode.END);
    }

    public void gt(Criterion criterion) {
        if (criterion.getValue() != null)
            query.gt(criterion.getPropertyName(), criterion.getValue());
    }

    public void lt(Criterion criterion) {
        if (criterion.getValue() != null)
            query.lt(criterion.getPropertyName(), criterion.getValue());
    }

    public void le(Criterion criterion) {
        if (criterion.getValue() != null)
            query.le(criterion.getPropertyName(), criterion.getValue());
    }

    public void ge(Criterion criterion) {
        if (criterion.getValue() != null)
            query.ge(criterion.getPropertyName(), criterion.getValue());
    }

    public void between(Criterion criterion) {
        Object[] values = criterion.getValues();
        if (values == null || values.length != 2)
            return;
        Object lo = values[0];
        Object hi = values[1];
        if (lo != null && hi != null)
            query.between(criterion.getPropertyName(), lo, hi);
    }

    public void in(Criterion criterion) {
        if (criterion.getValues() != null)
            query.in(criterion.getPropertyName(), criterion.getValues());
    }

    public void isNull(Criterion criterion) {
        query.isNull(criterion.getPropertyName());
    }

    public void isNotNull(Criterion criterion) {
        query.isNotNull(criterion.getPropertyName());
    }

    public void eqProperty(Criterion criterion) {
        query.eqProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void neProperty(Criterion criterion) {
        query.neProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void ltProperty(Criterion criterion) {
        query.ltProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void leProperty(Criterion criterion) {
        query.leProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void gtProperty(Criterion criterion) {
        query.gtProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void geProperty(Criterion criterion) {
        query.geProperty(criterion.getPropertyName(), criterion.getOtherPropertyName());
    }

    public void sqlRestriction(Criterion criterion) {
        if (criterion.getValues() != null) {
            query.sqlRestriction(criterion.getSql(), criterion.getValues());
        } else {
            query.sqlRestriction(criterion.getSql(), ArrayHelper.EMPTY_OBJECT_ARRAY);
        }
    }

    public void isEmpty(Criterion criterion) {
        query.isEmpty(criterion.getPropertyName());
    }

    public void isNotEmpty(Criterion criterion) {
        query.isNotEmpty(criterion.getPropertyName());
    }

    public void sizeEq(Criterion criterion) {
        query.sizeEq(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void sizeNe(Criterion criterion) {
        query.sizeNe(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void sizeGt(Criterion criterion) {
        query.sizeGt(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void sizeLt(Criterion criterion) {
        query.sizeLt(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void sizeGe(Criterion criterion) {
        query.sizeGe(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void sizeLe(Criterion criterion) {
        query.sizeLe(criterion.getPropertyName(), (Integer) criterion.getValue());
    }

    public void or(Criterion criterion) {
        Or or = criterion.getOr();
        if (or != null) {
            List<org.hibernate.criterion.Criterion> list = OrUtil.convert(or);
            if (list != null && !list.isEmpty()) {
                com.pla.query.Or or1 = new com.pla.query.Or(list);
                query.or(or1);
            }
        }
    }

    //-------------------------- Join --------------------------

    public void join(Criterion criterion) {
        this.leftJoin(criterion);
    }

    public void leftJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        query.leftJoin(criterion.getPropertyName(), alias);
    }

    public void rightJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        query.rightJoin(criterion.getPropertyName(), alias);
    }

    public void innerJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        query.innerJoin(criterion.getPropertyName(), alias);
    }

    public void fullJoin(Criterion criterion) {
        String alias = criterion.getAlias();
        if (alias == null)
            alias = criterion.getPropertyName();
        query.fullJoin(criterion.getPropertyName(), alias);
    }


    //-------------------------- OrderBy --------------------------

    public void asc(Criterion criterion) {
        query.asc(criterion.getPropertyName());
    }

    public void desc(Criterion criterion) {
        query.desc(criterion.getPropertyName());
    }

    //-------------------------- GroupBy --------------------------

    public void groupBy(Criterion criterion) {
        query.groupBy(criterion.getPropertyName());
    }

    public void countAll(Criterion criterion) {
        query.countAll(criterion.getAlias());
    }

    public void count(Criterion criterion) {
        query.count(criterion.getPropertyName(), criterion.getAlias());
    }

    public void max(Criterion criterion) {
        query.max(criterion.getPropertyName(), criterion.getAlias());
    }

    public void min(Criterion criterion) {
        query.min(criterion.getPropertyName(), criterion.getAlias());
    }

    public void sum(Criterion criterion) {
        query.sum(criterion.getPropertyName(), criterion.getAlias());
    }

    public void avg(Criterion criterion) {
        query.avg(criterion.getPropertyName(), criterion.getAlias());
    }

    public void distinct(Criterion criterion) {
        String[] properties = criterion.getPropertyNames();
        if (properties != null) {
            query.distinct(properties);
        } else {
            query.distinct();
        }
    }
}
