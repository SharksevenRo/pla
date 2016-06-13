package com.pla.query;

import com.pla.utils.TypeUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.sql.JoinType;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class QueryImpl<T> extends QueryExcutorImpl<T> implements Query<T> {
    protected T t;
    protected Class<T> clazz;
    private Criteria criteria;
    protected Session session;

    public Criteria getCriteria() {
        if (criteria != null) {
            return criteria;
        } else {
            criteria = session.createCriteria(this.clazz);
            return criteria;
        }
    }

    public Class<T> getClazz(){
        return this.clazz;
    }

    //-------------------------- Common Where --------------------------
    public Query<T> between(String propertyName, Object lo, Object hi) {
        if (lo != null && hi != null)
            getCriteria().add(Restrictions.between(propertyName, lo, hi));
        return this;
    }

    public Query<T> in(String propertyName, Object[] values) {
        if (values != null)
            getCriteria().add(Restrictions.in(propertyName, values));
        return this;
    }

    public Query<T> in(String propertyName, Collection values) {
        if (values != null)
            getCriteria().add(Restrictions.in(propertyName, values));
        return this;
    }

    public Query<T> isNull(String propertyName) {
        getCriteria().add(Restrictions.isNull(propertyName));
        return this;
    }

    public Query<T> isNotNull(String propertyName) {
        getCriteria().add(Restrictions.isNotNull(propertyName));
        return this;
    }

    public Query<T> eqProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.eqProperty(propertyName, otherPropertyName));
        return this;
    }

    public Query<T> neProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.neProperty(propertyName, otherPropertyName));
        return this;
    }

    public Query<T> ltProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.ltProperty(propertyName, otherPropertyName));
        return this;
    }

    public Query<T> leProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.leProperty(propertyName, otherPropertyName));
        return this;
    }

    public Query<T> gtProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.gtProperty(propertyName, otherPropertyName));
        return this;
    }

    public Query<T> geProperty(String propertyName, String otherPropertyName) {
        getCriteria().add(Restrictions.geProperty(propertyName, otherPropertyName));
        return this;
    }


    public Query<T> sqlRestriction(String sql, Object... values) {
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

            getCriteria().add(new SQLCriterion(sql, vals.toArray(), types.toArray(new Type[0])));
        }
        return this;
    }

    public Query<T> sqlRestriction(String sql) {
        getCriteria().add(new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY));
        return this;
    }

    public Query<T> allEq(Map propertyNameValues) {
        getCriteria().add(Restrictions.allEq(propertyNameValues));
        return this;
    }

    public Query<T> isEmpty(String propertyName) {
        getCriteria().add(Restrictions.isEmpty(propertyName));
        return this;
    }

    public Query<T> isNotEmpty(String propertyName) {
        getCriteria().add(Restrictions.isNotEmpty(propertyName));
        return this;
    }

    public Query<T> sizeEq(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeEq(propertyName, size));
        return this;
    }

    public Query<T> sizeNe(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeNe(propertyName, size));
        return this;
    }

    public Query<T> sizeGt(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeGt(propertyName, size));
        return this;
    }

    public Query<T> sizeLt(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeLt(propertyName, size));
        return this;
    }

    public Query<T> sizeGe(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeGe(propertyName, size));
        return this;
    }

    public Query<T> sizeLe(String propertyName, int size) {
        getCriteria().add(Restrictions.sizeLe(propertyName, size));
        return this;
    }

    public Query<T> or(Or or) {
        List<Criterion> list = or.getOrList();
        if (list != null && !list.isEmpty()) {
            getCriteria().add(Restrictions.or(list.toArray(new Criterion[0])));
        }
        return this;
    }


    //-------------------------- Join --------------------------
    private List<JoinSub> joinList;

    protected List<JoinSub> getJoinList() {
        if (joinList == null) {
            joinList = new ArrayList<JoinSub>();
        }
        return joinList;
    }

    public Query<T> on(Criterion criterion) {
        if (joinList != null && !joinList.isEmpty() && criterion != null) {
            joinList.get(joinList.size() - 1).on(criterion);
        }
        return this;
    }

    public Query<T> join(String associationPath) {
        return this.join(associationPath, associationPath);
    }

    public Query<T> join(String associationPath, String alias) {
        return this.leftJoin(associationPath, alias);
    }

    public Query<T> leftJoin(String associationPath) {
        return this.leftJoin(associationPath, associationPath);
    }

    public Query<T> leftJoin(String associationPath, String alias) {
        JoinSub join = new JoinSub(associationPath, alias, JoinType.LEFT_OUTER_JOIN);
        getJoinList().add(join);
        return this;
    }

    public Query<T> rightJoin(String associationPath) {
        return this.rightJoin(associationPath, associationPath);
    }

    public Query<T> rightJoin(String associationPath, String alias) {
        JoinSub join = new JoinSub(associationPath, alias, JoinType.RIGHT_OUTER_JOIN);
        getJoinList().add(join);
        return this;
    }

    public Query<T> innerJoin(String associationPath) {
        return this.innerJoin(associationPath, associationPath);
    }

    public Query<T> innerJoin(String associationPath, String alias) {
        JoinSub join = new JoinSub(associationPath, alias, JoinType.INNER_JOIN);
        getJoinList().add(join);
        return this;
    }

    public Query<T> fullJoin(String associationPath) {
        return this.fullJoin(associationPath, associationPath);
    }

    public Query<T> fullJoin(String associationPath, String alias) {
        JoinSub join = new JoinSub(associationPath, alias, JoinType.FULL_JOIN);
        getJoinList().add(join);
        return this;
    }

    public Query<T> batch(String property){
        getBatchList().add(property);
        return this;
    }

    protected void generateJoin() {
        if (joinList != null && !joinList.isEmpty()) {
            for (JoinSub join : joinList) {
                join.generateJoin(getCriteria());
            }
        }
    }

    //-------------------------- OrderBy --------------------------
    public Query<T> asc(String propertyName) {
        if (ascList == null)
            ascList = new ArrayList<String>();
        ascList.add(propertyName);
        return this;
    }

    public Query<T> desc(String propertyName) {
        if (descList == null)
            descList = new ArrayList<String>();
        descList.add(propertyName);
        return this;
    }

    protected void generateOrderBy() {
        if (ascList != null) {
            for (String asc : ascList) {
                getCriteria().addOrder(Order.asc(asc));
            }
        }
        if (descList != null) {
            for (String desc : descList) {
                getCriteria().addOrder(Order.desc(desc));
            }
        }
    }
}
