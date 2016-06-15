package com.pla.query;

import com.pla.utils.SimplePropertyUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class QueryExcutorImpl<T> extends QueryGroupbyImpl<T> implements QueryExcutor<T> {
    protected List<String> ascList;
    protected List<String> descList;

    private List<String> batchList;

    // -------------------------- Query Opration --------------------------
    protected abstract void close();

    protected List<String> getBatchList() {
        if (batchList == null)
            batchList = new ArrayList<String>();
        return batchList;
    }

    public T load(Serializable id) {
        try {
            generateJoin();
            T t = (T) getCriteria().add(Restrictions.idEq(id)).uniqueResult();
            if (t != null && batchList != null) {
                batchData(t);
            }
            return t;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public T first() {
        List<T> list = this.list(0, 1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<T> list() {
        try {
            generateJoin();
            generateOrderBy();
            List<T> list = getCriteria().list();
            if (list != null && batchList != null) {
                for (T t : list) {
                    batchData(t);
                }
            }
            return list;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public List<T> list(int offset, int size) {
        try {
            generateJoin();
            generateOrderBy();
            getCriteria().setFirstResult(offset);
            getCriteria().setMaxResults(size);
            List<T> list = getCriteria().list();
            if (list != null && batchList != null) {
                for (T t : list) {
                    batchData(t);
                }
            }
            return list;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public int count() {
        try {
            generateJoin();
            getCriteria().setProjection(Projections.rowCount());
            Long count = (Long) getCriteria().uniqueResult();
            return count.intValue();
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public T uniqueResult() {
        try {
            generateJoin();
            T t = (T) getCriteria().uniqueResult();
            if (t != null && batchList != null) {
                batchData(t);
            }
            return t;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public Pager<T> pager(int pageNo, int pageSize) {
        try {
            generateJoin();
            Pager<T> pager = new Pager<T>(pageNo, pageSize);

            getCriteria().setProjection(Projections.rowCount());
            Long count = (Long) getCriteria().uniqueResult();
            pager.setTotalCount(count.intValue());

            getCriteria().setProjection(null);
            getCriteria().setResultTransformer(Criteria.ROOT_ENTITY);

            generateOrderBy();
            getCriteria().setFirstResult(pager.getOffset());
            getCriteria().setMaxResults(pager.getPageSize());
            List<T> list = getCriteria().list();
            if (list != null && batchList != null) {
                for (T t : list) {
                    batchData(t);
                }
            }
            pager.setList(list);
            return pager;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    private void batchData(T t) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (String batch : batchList) {
            Object obj = SimplePropertyUtil.getProperty(t, batch);
            if (obj != null)
                obj.hashCode();
        }
    }

    // -------------------------- QueryOp4Parts --------------------------
    private void addPorjectionList(String... propertyNames) {
        for (String propertyName : propertyNames) {
            int idx = propertyName.indexOf(" as ");
            if (idx > 0) {
                propertyName = propertyName.substring(0, idx).trim();
            }

            getProjectionList().add(Projections.property(propertyName));
        }
    }

    private void setPropertites(Object obj, T t, String propertyName) throws Exception {
        int idx = propertyName.indexOf(" as ");
        if (idx > 0) {
            propertyName = propertyName.substring(idx + 4).trim();
        }
        SimplePropertyUtil.setProperty(t, propertyName, obj);
    }

    private void setPropertites(Object obj, T t, String... propertyNames) throws Exception {
        if (obj instanceof Object[]) {
            Object[] objects = (Object[]) obj;
            for (int i = 0; i < objects.length; i++) {
                setPropertites(objects[i], t, propertyNames[i]);
            }
        } else {
            setPropertites(obj, t, propertyNames[0]);
        }
    }

    public T uniqueResult(String... propertyNames) {
        try {
            generateJoin();
            if (propertyNames == null || propertyNames.length == 0)
                return null;

            addPorjectionList(propertyNames);

            getCriteria().setProjection(getProjectionList());

            Object obj = getCriteria().uniqueResult();
            T t = getClazz().newInstance();
            setPropertites(obj, t, propertyNames);
            return t;
        } catch (HibernateException e) {
            throw e;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public T first(String... propertyNames) {
        List<T> list = list(0, 1, propertyNames);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<T> list(String... propertyNames) {
        return list(null, null, propertyNames);
    }

    public List<T> list(Integer offset, Integer size, String... propertyNames) {
        try {
            generateJoin();
            if (propertyNames == null || propertyNames.length == 0)
                return null;

            addPorjectionList(propertyNames);

            getCriteria().setProjection(getProjectionList());

            generateOrderBy();
            if (offset != null)
                getCriteria().setFirstResult(offset);
            if (size != null)
                getCriteria().setMaxResults(size);
            List list = getCriteria().list();
            List<T> records = new ArrayList<T>();
            for (Object obj : list) {
                T t = getClazz().newInstance();
                setPropertites(obj, t, propertyNames);
                records.add(t);
            }
            return records;
        } catch (HibernateException e) {
            throw e;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public Pager<T> pager(int pageNo, int pageSize, String... propertyNames) {
        try {
            generateJoin();
            if (propertyNames == null || propertyNames.length == 0)
                return null;

            Pager<T> pager = new Pager<T>(pageNo, pageSize);

            getCriteria().setProjection(Projections.rowCount());
            Long count = (Long) getCriteria().uniqueResult();
            pager.setTotalCount(count.intValue());

            addPorjectionList(propertyNames);
            getCriteria().setProjection(getProjectionList());

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
            getCriteria().setFirstResult(pager.getOffset());
            getCriteria().setMaxResults(pager.getPageSize());
            List list = getCriteria().list();
            List<T> records = new ArrayList<T>();
            for (Object obj : list) {
                T t = getClazz().newInstance();
                setPropertites(obj, t, propertyNames);
                records.add(t);
            }
            pager.setList(records);
            return pager;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }
}
