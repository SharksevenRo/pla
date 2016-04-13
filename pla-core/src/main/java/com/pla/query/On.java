package com.pla.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.Type;

import com.pla.utils.TypeUtil;

@SuppressWarnings("rawtypes")
public class On {
    public static Criterion idEq(Object value) {
        if (value != null) {
            return Restrictions.idEq(value);
        }
        return null;
    }

    public static Criterion eq(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.eq(propertyName, value);
        }
        return null;
    }

    public static Criterion eqOrIsNull(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.eqOrIsNull(propertyName, value);
        }
        return null;
    }

    public static Criterion ne(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.ne(propertyName, value);
        }
        return null;
    }

    public static Criterion neOrIsNotNull(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.neOrIsNotNull(propertyName, value);
        }
        return null;
    }

    public static Criterion like(String propertyName, String value) {
        if (value != null) {
            return Restrictions.like(propertyName, value, MatchMode.ANYWHERE);
        }
        return null;
    }

    public static Criterion startLike(String propertyName, String value) {
        if (value != null) {
            return Restrictions.like(propertyName, value, MatchMode.START);
        }
        return null;
    }

    public static Criterion endLike(String propertyName, String value) {
        if (value != null) {
            return Restrictions.like(propertyName, value, MatchMode.END);
        }
        return null;
    }

    public static Criterion like(String propertyName, String value, MatchMode matchMode) {
        if (value != null) {
            return Restrictions.like(propertyName, value, matchMode);
        }
        return null;
    }

    public static Criterion ilike(String propertyName, String value, MatchMode matchMode) {
        if (value != null) {
            return Restrictions.ilike(propertyName, value, matchMode);
        }
        return null;
    }

    public static Criterion ilike(String propertyName, String value) {
        if (value != null) {
            return Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE);
        }
        return null;
    }

    public static Criterion startIlike(String propertyName, String value) {
        if (value != null) {
            return Restrictions.ilike(propertyName, value, MatchMode.START);
        }
        return null;
    }

    public static Criterion endIlike(String propertyName, String value) {
        if (value != null) {
            return Restrictions.ilike(propertyName, value, MatchMode.END);
        }
        return null;
    }

    public static Criterion gt(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.gt(propertyName, value);
        }
        return null;
    }

    public static Criterion lt(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.lt(propertyName, value);
        }
        return null;
    }

    public static Criterion le(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.le(propertyName, value);
        }
        return null;
    }

    public static Criterion ge(String propertyName, Object value) {
        if (value != null) {
            return Restrictions.ge(propertyName, value);
        }
        return null;
    }

    public static Criterion between(String propertyName, Object lo, Object hi) {
        if (lo != null && hi != null) {
            return Restrictions.between(propertyName, lo, hi);
        }
        return null;
    }

    public static Criterion in(String propertyName, Object[] values) {
        if (values != null) {
            return Restrictions.in(propertyName, values);
        }
        return null;
    }

    public static Criterion in(String propertyName, Collection values) {
        if (values != null) {
            return Restrictions.in(propertyName, values);
        }
        return null;
    }

    public static Criterion isNull(String propertyName) {
        return Restrictions.isNull(propertyName);
    }

    public static Criterion isNotNull(String propertyName) {
        return Restrictions.isNotNull(propertyName);
    }

    public static Criterion eqProperty(String propertyName, String otherPropertyName) {
        return Restrictions.eqProperty(propertyName, otherPropertyName);
    }

    public static Criterion neProperty(String propertyName, String otherPropertyName) {
        return Restrictions.neProperty(propertyName, otherPropertyName);
    }

    public static Criterion ltProperty(String propertyName, String otherPropertyName) {
        return Restrictions.ltProperty(propertyName, otherPropertyName);
    }

    public static Criterion leProperty(String propertyName, String otherPropertyName) {
        return Restrictions.leProperty(propertyName, otherPropertyName);
    }

    public static Criterion gtProperty(String propertyName, String otherPropertyName) {
        return Restrictions.gtProperty(propertyName, otherPropertyName);
    }

    public static Criterion geProperty(String propertyName, String otherPropertyName) {
        return Restrictions.geProperty(propertyName, otherPropertyName);
    }

    public static Criterion sqlRestriction(String sql, Object... values) {
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
            return new SQLCriterion(sql, vals.toArray(), types.toArray(new Type[0]));
        }
        return null;
    }

    public static Criterion sqlRestriction(String sql) {
        return new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY);
    }

    public static Criterion allEq(Map propertyNameValues) {
        return Restrictions.allEq(propertyNameValues);
    }

    public static Criterion isEmpty(String propertyName) {
        return Restrictions.isEmpty(propertyName);
    }

    public static Criterion isNotEmpty(String propertyName) {
        return Restrictions.isNotEmpty(propertyName);
    }

    public static Criterion sizeEq(String propertyName, int size) {
        return Restrictions.sizeEq(propertyName, size);
    }

    public static Criterion sizeNe(String propertyName, int size) {
        return Restrictions.sizeNe(propertyName, size);
    }

    public static Criterion sizeGt(String propertyName, int size) {
        return Restrictions.sizeGt(propertyName, size);
    }

    public static Criterion sizeLt(String propertyName, int size) {
        return Restrictions.sizeLt(propertyName, size);
    }

    public static Criterion sizeGe(String propertyName, int size) {
        return Restrictions.sizeGe(propertyName, size);
    }

    public static Criterion sizeLe(String propertyName, int size) {
        return Restrictions.sizeLe(propertyName, size);
    }
}
