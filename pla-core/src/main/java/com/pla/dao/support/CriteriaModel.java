package com.pla.dao.support;

import com.pla.query.Or;
import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CriteriaModel extends CriteriaClazz {
    private Object model;

    public CriteriaModel(Object model) {
        this.model = model;
    }

    public CriteriaModel idEq() {
        Serializable idValue = ModelUtil.getIdValue(model);
        return (CriteriaModel) super.idEq(idValue);
    }

    public CriteriaModel eq(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.eq(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel eqOrIsNull(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.eqOrIsNull(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel ne(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.ne(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel neOrIsNotNull(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.neOrIsNotNull(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel like(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.like(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel startLike(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.startLike(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel endLike(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.endLike(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel ilike(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.ilike(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel startIlike(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.startIlike(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel endIlike(String propertyName) {
        try {
            String value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.endIlike(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel gt(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.gt(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel lt(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.lt(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel le(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.le(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CriteriaModel ge(String propertyName) {
        try {
            Object value = SimplePropertyUtil.getProperty(model, propertyName);
            return (CriteriaModel) super.ge(propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //-------------------------- By Class Where --------------------------
    @Override
    public CriteriaModel idEq(Serializable value) {
        return (CriteriaModel) super.idEq(value);
    }

    @Override
    public CriteriaModel eq(String propertyName, Object value) {
        return (CriteriaModel) super.eq(propertyName, value);
    }

    @Override
    public CriteriaModel eqOrIsNull(String propertyName, Object value) {
        return (CriteriaModel) super.eqOrIsNull(propertyName, value);
    }

    @Override
    public CriteriaModel ne(String propertyName, Object value) {
        return (CriteriaModel) super.ne(propertyName, value);
    }

    @Override
    public CriteriaModel neOrIsNotNull(String propertyName, Object value) {
        return (CriteriaModel) super.neOrIsNotNull(propertyName, value);
    }

    @Override
    public CriteriaModel like(String propertyName, String value) {
        return (CriteriaModel) super.like(propertyName, value);
    }

    @Override
    public CriteriaModel startLike(String propertyName, String value) {
        return (CriteriaModel) super.startLike(propertyName, value);
    }

    @Override
    public CriteriaModel endLike(String propertyName, String value) {
        return (CriteriaModel) super.endLike(propertyName, value);
    }

    @Override
    public CriteriaModel ilike(String propertyName, String value) {
        return (CriteriaModel) super.ilike(propertyName, value);
    }

    @Override
    public CriteriaModel startIlike(String propertyName, String value) {
        return (CriteriaModel) super.startIlike(propertyName, value);
    }

    @Override
    public CriteriaModel endIlike(String propertyName, String value) {
        return (CriteriaModel) super.endIlike(propertyName, value);
    }

    @Override
    public CriteriaModel gt(String propertyName, Object value) {
        return (CriteriaModel) super.gt(propertyName, value);
    }

    @Override
    public CriteriaModel lt(String propertyName, Object value) {
        return (CriteriaModel) super.lt(propertyName, value);
    }

    @Override
    public CriteriaModel le(String propertyName, Object value) {
        return (CriteriaModel) super.le(propertyName, value);
    }

    @Override
    public CriteriaModel ge(String propertyName, Object value) {
        return (CriteriaModel) super.ge(propertyName, value);
    }

    @Override
    public CriteriaModel between(String propertyName, Object lo, Object hi) {
        return (CriteriaModel) super.between(propertyName, lo, hi);
    }

    @Override
    public CriteriaModel in(String propertyName, Object[] values) {
        return (CriteriaModel) super.in(propertyName, values);
    }

	@Override
    public CriteriaModel in(String propertyName, Collection values) {
        return (CriteriaModel) super.in(propertyName, values);
    }

    @Override
    public CriteriaModel isNull(String propertyName) {
        return (CriteriaModel) super.isNull(propertyName);
    }

    @Override
    public CriteriaModel isNotNull(String propertyName) {
        return (CriteriaModel) super.isNotNull(propertyName);
    }

    @Override
    public CriteriaModel eqProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.eqProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel neProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.neProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel ltProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.ltProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel leProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.leProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel gtProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.gtProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel geProperty(String propertyName, String otherPropertyName) {
        return (CriteriaModel) super.geProperty(propertyName, otherPropertyName);
    }

    @Override
    public CriteriaModel sqlRestriction(String sql, Object... values) {
        return (CriteriaModel) super.sqlRestriction(sql, values);
    }

    @Override
    public CriteriaModel sqlRestriction(String sql) {
        return (CriteriaModel) super.sqlRestriction(sql);
    }

    @Override
    public CriteriaModel allEq(Map propertyNameValues) {
        return (CriteriaModel) super.allEq(propertyNameValues);
    }

    @Override
    public CriteriaModel isEmpty(String propertyName) {
        return (CriteriaModel) super.isEmpty(propertyName);
    }

    @Override
    public CriteriaModel isNotEmpty(String propertyName) {
        return (CriteriaModel) super.isNotEmpty(propertyName);
    }

    @Override
    public CriteriaModel sizeEq(String propertyName, int size) {
        return (CriteriaModel) super.sizeEq(propertyName, size);
    }

    @Override
    public CriteriaModel sizeNe(String propertyName, int size) {
        return (CriteriaModel) super.sizeNe(propertyName, size);
    }

    @Override
    public CriteriaModel sizeGt(String propertyName, int size) {
        return (CriteriaModel) super.sizeGt(propertyName, size);
    }

    @Override
    public CriteriaModel sizeLt(String propertyName, int size) {
        return (CriteriaModel) super.sizeLt(propertyName, size);
    }

    @Override
    public CriteriaModel sizeGe(String propertyName, int size) {
        return (CriteriaModel) super.sizeGe(propertyName, size);
    }

    @Override
    public CriteriaModel sizeLe(String propertyName, int size) {
        return (CriteriaModel) super.sizeLe(propertyName, size);
    }

    @Override
    public CriteriaModel or(Or or) {
        return (CriteriaModel) super.or(or);
    }

    //-------------------------- Join --------------------------
    @Override
    public CriteriaModel join(String associationPath) {
        return (CriteriaModel) super.join(associationPath);
    }

    @Override
    public CriteriaModel join(String associationPath, String alias) {
        return (CriteriaModel) super.join(associationPath, alias);
    }

    @Override
    public CriteriaModel leftJoin(String associationPath) {
        return (CriteriaModel) super.leftJoin(associationPath);
    }

    @Override
    public CriteriaModel leftJoin(String associationPath, String alias) {
        return (CriteriaModel) super.leftJoin(associationPath, alias);
    }

    @Override
    public CriteriaModel rightJoin(String associationPath) {
        return (CriteriaModel) super.rightJoin(associationPath);
    }

    @Override
    public CriteriaModel rightJoin(String associationPath, String alias) {
        return (CriteriaModel) super.rightJoin(associationPath, alias);
    }

    @Override
    public CriteriaModel innerJoin(String associationPath) {
        return (CriteriaModel) super.innerJoin(associationPath);
    }

    @Override
    public CriteriaModel innerJoin(String associationPath, String alias) {
        return (CriteriaModel) super.innerJoin(associationPath, alias);
    }

    @Override
    public CriteriaModel fullJoin(String associationPath) {
        return (CriteriaModel) super.fullJoin(associationPath);
    }

    @Override
    public CriteriaModel fullJoin(String associationPath, String alias) {
        return (CriteriaModel) super.fullJoin(associationPath, alias);
    }


    //-------------------------- OrderBy --------------------------
    @Override
    public CriteriaModel asc(String propertyName) {
        return (CriteriaModel) super.asc(propertyName);
    }

    @Override
    public CriteriaModel desc(String propertyName) {
        return (CriteriaModel) super.desc(propertyName);
    }

    //-------------------------- GroupBy --------------------------
    @Override
    public CriteriaModel groupBy(String propertyName) {
        return (CriteriaModel) super.groupBy(propertyName);
    }

    @Override
    public CriteriaModel countAll() {
        return (CriteriaModel) super.countAll();
    }

    @Override
    public CriteriaModel countAll(String alias) {
        return (CriteriaModel) super.countAll(alias);
    }

    @Override
    public CriteriaModel count(String propertyName) {
        return (CriteriaModel) super.count(propertyName);
    }

    @Override
    public CriteriaModel count(String propertyName, String alias) {
        return (CriteriaModel) super.count(propertyName, alias);
    }

    @Override
    public CriteriaModel max(String propertyName) {
        return (CriteriaModel) super.max(propertyName);
    }

    @Override
    public CriteriaModel max(String propertyName, String alias) {
        return (CriteriaModel) super.max(propertyName, alias);
    }

    @Override
    public CriteriaModel min(String propertyName) {
        return (CriteriaModel) super.min(propertyName);
    }

    @Override
    public CriteriaModel min(String propertyName, String alias) {
        return (CriteriaModel) super.min(propertyName, alias);
    }

    @Override
    public CriteriaModel sum(String propertyName) {
        return (CriteriaModel) super.sum(propertyName);
    }

    @Override
    public CriteriaModel sum(String propertyName, String alias) {
        return (CriteriaModel) super.sum(propertyName, alias);
    }

    @Override
    public CriteriaModel avg(String propertyName) {
        return (CriteriaModel) super.avg(propertyName);
    }

    @Override
    public CriteriaModel avg(String propertyName, String alias) {
        return (CriteriaModel) super.avg(propertyName, alias);
    }

    @Override
    public CriteriaModel distinct(String... properties) {
        return (CriteriaModel) super.distinct(properties);
    }
}
