package com.pla.query;

import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import com.pla.utils.TypeUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class Or {
    private Object model;

    private List<Criterion> orList;

    public List<Criterion> getOrList() {
        return orList;
    }

    public Or() {
        orList = new ArrayList<Criterion>();
    }

    public Or(List<Criterion> orList) {
        this.orList = orList;
    }

    public Or(Object model) {
        this.model = model;
    }

    public static Or create() {
        return new Or();
    }

    public static Or create(Object property) {
        return new Or(property);
    }

    private Object getValue(String propertyName) {
        try {
            return SimplePropertyUtil.getProperty(model, propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Or idEq(Object value) {
        if (value != null)
            orList.add(Restrictions.idEq(value));
        return this;
    }

    public Or idEq() {
        Serializable id = ModelUtil.getIdValue(model);
        if (id != null)
            orList.add(Restrictions.idEq(id));
        return this;
    }

    public Or eq(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.eq(propertyName, value));
        return this;
    }

    public Or eq(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.eq(propertyName, value));
        return this;
    }

    public Or eqOrIsNull(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.eqOrIsNull(propertyName, value));
        return this;
    }

    public Or eqOrIsNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.eqOrIsNull(propertyName, value));
        return this;
    }

    public Or ne(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.ne(propertyName, value));
        return this;
    }

    public Or ne(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ne(propertyName, value));
        return this;
    }

    public Or neOrIsNotNull(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }

    public Or neOrIsNotNull(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.neOrIsNotNull(propertyName, value));
        return this;
    }

    public Or like(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public Or like(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public Or startLike(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.START));
        return this;
    }

    public Or startLike(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.START));
        return this;
    }

    public Or endLike(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.END));
        return this;
    }

    public Or endLike(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, MatchMode.END));
        return this;
    }

    public Or like(String propertyName, MatchMode matchMode) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.like(propertyName, value, matchMode));
        return this;
    }

    public Or ilike(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public Or ilike(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE));
        return this;
    }

    public Or startIlike(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.START));
        return this;
    }

    public Or startIlike(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.START));
        return this;
    }

    public Or endIlike(String propertyName, String value) {
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.END));
        return this;
    }

    public Or endIlike(String propertyName) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, MatchMode.END));
        return this;
    }

    public Or ilike(String propertyName, String value, MatchMode matchMode) {
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, matchMode));
        return this;
    }

    public Or ilike(String propertyName, MatchMode matchMode) {
        String value = (String) getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ilike(propertyName, value, matchMode));
        return this;
    }


    public Or gt(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.gt(propertyName, value));
        return this;
    }

    public Or gt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.gt(propertyName, value));
        return this;
    }

    public Or lt(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.lt(propertyName, value));
        return this;
    }

    public Or lt(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.lt(propertyName, value));
        return this;
    }

    public Or le(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.le(propertyName, value));
        return this;
    }

    public Or le(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.le(propertyName, value));
        return this;
    }

    public Or ge(String propertyName, Object value) {
        if (value != null)
            orList.add(Restrictions.ge(propertyName, value));
        return this;
    }

    public Or ge(String propertyName) {
        Object value = getValue(propertyName);
        if (value != null)
            orList.add(Restrictions.ge(propertyName, value));
        return this;
    }

    public Or between(String propertyName, Object lo, Object hi) {
        if (lo != null && hi != null)
            orList.add(Restrictions.between(propertyName, lo, hi));
        return this;
    }

    public Or in(String propertyName, Object[] values) {
        if (values != null)
            orList.add(Restrictions.in(propertyName, values));
        return this;
    }

    public Or in(String propertyName, Collection values) {
        if (values != null)
            orList.add(Restrictions.in(propertyName, values));
        return this;
    }

    public Or isNull(String propertyName) {
        orList.add(Restrictions.isNull(propertyName));
        return this;
    }

    public Or isNotNull(String propertyName) {
        orList.add(Restrictions.isNotNull(propertyName));
        return this;
    }

    public Or eqProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.eqProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or neProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.neProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or ltProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.ltProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or leProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.leProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or gtProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.gtProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or geProperty(String propertyName, String otherPropertyName) {
        orList.add(Restrictions.geProperty(propertyName, otherPropertyName));
        return this;
    }

    public Or sqlRestriction(String sql, Object... values) {
        if (values != null) {
            List<Object> vals = new ArrayList<Object>();
            List<Type> types = new ArrayList<Type>();
            for (Object value : values) {
                Type type = TypeUtil.getType(value);
                if (type != null) {
                    vals.add(value);
                    types.add(type);
                }
            }

            orList.add(new SQLCriterion(sql, vals.toArray(), types.toArray(new Type[0])));
        }
        return this;
    }

    public Or sqlRestriction(String sql) {
        orList.add(new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        return this;
    }

    public Or allEq(Map propertyNameValues) {
        orList.add(Restrictions.allEq(propertyNameValues));
        return this;
    }

    public Or isEmpty(String propertyName) {
        orList.add(Restrictions.isEmpty(propertyName));
        return this;
    }

    public Or isNotEmpty(String propertyName) {
        orList.add(Restrictions.isNotEmpty(propertyName));
        return this;
    }

    public Or sizeEq(String propertyName, int size) {
        orList.add(Restrictions.sizeEq(propertyName, size));
        return this;
    }

    public Or sizeNe(String propertyName, int size) {
        orList.add(Restrictions.sizeNe(propertyName, size));
        return this;
    }

    public Or sizeGt(String propertyName, int size) {
        orList.add(Restrictions.sizeGt(propertyName, size));
        return this;
    }

    public Or sizeLt(String propertyName, int size) {
        orList.add(Restrictions.sizeLt(propertyName, size));
        return this;
    }

    public Or sizeGe(String propertyName, int size) {
        orList.add(Restrictions.sizeGe(propertyName, size));
        return this;
    }

    public Or sizeLe(String propertyName, int size) {
        orList.add(Restrictions.sizeLe(propertyName, size));
        return this;
    }
}
