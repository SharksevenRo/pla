package com.pla.query;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.type.Type;

import java.util.Iterator;

@SuppressWarnings({"serial", "rawtypes"})
public class SQLCriterion implements Criterion {
    private final String sql;
    private final TypedValue[] typedValues;

	public String toSqlString(Criteria criteria, org.hibernate.criterion.CriteriaQuery criteriaQuery) throws HibernateException {
        CriteriaImpl rootCriteria;
        if (criteria instanceof CriteriaImpl) {
            rootCriteria = (CriteriaImpl) criteria;
        } else if (criteria instanceof CriteriaImpl.Subcriteria) {
            rootCriteria = (CriteriaImpl) ((CriteriaImpl.Subcriteria) criteria).getParent();
        } else {
            throw new HibernateException("not support the other instance of Criteria");
        }
        Iterator iterateSubcriteria = rootCriteria.iterateSubcriteria();
        String tempSql = sql;
        while (iterateSubcriteria.hasNext()) {
            CriteriaImpl.Subcriteria subCriteria = (CriteriaImpl.Subcriteria) iterateSubcriteria.next();
            tempSql = StringHelper.replace(tempSql, "{" + subCriteria.getAlias() + "}", criteriaQuery.getSQLAlias(subCriteria));
        }
        return StringHelper.replace(tempSql, "{this}", criteriaQuery.getSQLAlias(criteria));
    }

    public TypedValue[] getTypedValues(Criteria criteria, org.hibernate.criterion.CriteriaQuery criteriaQuery) throws HibernateException {
        return this.typedValues;
    }

    public String toString() {
        return this.sql;
    }

    public SQLCriterion(String sql, Object[] values, Type[] types) {
        this.sql = sql;
        this.typedValues = new TypedValue[values.length];

        for (int i = 0; i < this.typedValues.length; ++i) {
            this.typedValues[i] = new TypedValue(types[i], values[i], EntityMode.POJO);
        }

    }
}