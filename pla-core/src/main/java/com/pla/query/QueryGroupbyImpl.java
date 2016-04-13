package com.pla.query;

import com.pla.utils.ModelUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class QueryGroupbyImpl<T> implements QueryGroupby<T> {
    protected List<String> aliasList;
    private ProjectionList projectionList;

    protected List<String> getAliasList() {
        if (aliasList == null)
            aliasList = new ArrayList<String>();
        return aliasList;
    }

    protected ProjectionList getProjectionList() {
        if (projectionList == null)
            projectionList = Projections.projectionList();
        return projectionList;
    }

    public abstract Criteria getCriteria();

    public abstract Class<T> getClazz();

    protected abstract void generateJoin();

    protected abstract void close();

    protected abstract void generateOrderBy();

    public QueryGroupby<T> groupBy(String propertyName) {
        getProjectionList().add(Projections.groupProperty(propertyName));
        getCriteria().setProjection(getProjectionList());
        getAliasList().add(propertyName);
        return this;
    }

    public QueryGroupby<T> countAll() {
        this.countAll(null);
        return this;
    }

    public QueryGroupby<T> countAll(String alias) {
        if (alias != null)
            getProjectionList().add(Projections.rowCount(), alias);
        else
            getProjectionList().add(Projections.rowCount());
        getCriteria().setProjection(getProjectionList());
        if (alias == null)
            getAliasList().add("cnt");
        else
            getAliasList().add(alias);
        return this;
    }


    public QueryGroupby<T> count(String propertyName) {
        this.count(propertyName, null);

        return this;
    }

    public QueryGroupby<T> count(String propertyName, String alias) {
        if (alias != null) {
            getProjectionList().add(Projections.count(propertyName), alias);
            getAliasList().add(alias);
        } else {
            getProjectionList().add(Projections.count(propertyName));
            getAliasList().add(propertyName);
        }
        getCriteria().setProjection(getProjectionList());

        return this;
    }

    public QueryGroupby<T> max(String propertyName) {
        this.max(propertyName, null);
        return this;
    }

    public QueryGroupby<T> max(String propertyName, String alias) {
        if (alias != null) {
            getProjectionList().add(Projections.max(propertyName), alias);
            getAliasList().add(alias);
        } else {
            getProjectionList().add(Projections.max(propertyName));
            getAliasList().add(propertyName);
        }
        getCriteria().setProjection(getProjectionList());

        return this;
    }

    public QueryGroupby<T> min(String propertyName) {
        this.min(propertyName, null);
        return this;
    }

    public QueryGroupby<T> min(String propertyName, String alias) {
        if (alias != null) {
            getProjectionList().add(Projections.min(propertyName), alias);
            getAliasList().add(alias);
        } else {
            getProjectionList().add(Projections.min(propertyName));
            getAliasList().add(propertyName);
        }
        getCriteria().setProjection(getProjectionList());

        return this;
    }

    public QueryGroupby<T> sum(String propertyName) {
        this.sum(propertyName, null);
        return this;
    }

    public QueryGroupby<T> sum(String propertyName, String alias) {
        if (alias != null) {
            getProjectionList().add(Projections.sum(propertyName), alias);
            getAliasList().add(alias);
        } else {
            getProjectionList().add(Projections.sum(propertyName));
            getAliasList().add(propertyName);
        }
        getCriteria().setProjection(getProjectionList());

        return this;
    }

    public QueryGroupby<T> avg(String propertyName) {
        this.avg(propertyName, null);
        return this;
    }

    public QueryGroupby<T> avg(String propertyName, String alias) {
        if (alias != null) {
            getProjectionList().add(Projections.avg(propertyName), alias);
            getAliasList().add(alias);
        } else {
            getProjectionList().add(Projections.avg(propertyName));
            getAliasList().add(propertyName);
        }
        getCriteria().setProjection(getProjectionList());

        return this;
    }

    public QueryGroupby<T> distinct(String... properties) {
        ProjectionList projectionList = Projections.projectionList();
        for (String property : properties) {
            projectionList.add(Projections.property(property));
            getAliasList().add(property);
        }
        getCriteria().setProjection(Projections.distinct(projectionList));
        return this;
    }

    public QueryGroupby<T> distinct() {
        Field[] fields = ModelUtil.getEntityProperties(getClazz());
        ProjectionList projectionList = Projections.projectionList();
        for (Field field : fields) {
            projectionList.add(Projections.property(field.getName()));
            getAliasList().add(field.getName());
        }
        getCriteria().setProjection(Projections.distinct(projectionList));
        return this;
    }

    public Record record() {
        try {
            generateJoin();
            if (aliasList == null) {
                throw new HibernateException("It is not Object[] result.");
            }
            Object obj = getCriteria().uniqueResult();
            Record record = new Record();
            if (obj instanceof Object[]) {
                Object[] objects = (Object[]) obj;
                for (int i = 0; i < objects.length; i++) {
                    record.put(aliasList.get(i), objects[i]);
                }
            } else {
                record.put(aliasList.get(0), obj);
            }
            return record;
        } catch (HibernateException e) {
            throw e;
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            close();
        }
    }

    public List<Record> recordList() {
        return recordList(null, null);
    }

    public List<Record> recordList(Integer offset, Integer size) {
        try {
            generateJoin();
            if (aliasList == null) {
                throw new HibernateException("It is not Object[] results.");
            }
            generateOrderBy();
            if (offset != null)
                getCriteria().setFirstResult(offset);
            if (size != null)
                getCriteria().setMaxResults(size);
            List list = getCriteria().list();
            List<Record> records = new ArrayList<Record>();
            for (Object obj : list) {
                Record record = new Record();
                if (obj instanceof Object[]) {
                    Object[] objects = (Object[]) obj;
                    for (int i = 0; i < objects.length; i++) {
                        record.put(aliasList.get(i), objects[i]);
                    }
                } else {
                    record.put(aliasList.get(0), obj);
                }
                records.add(record);
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

    public Record recordFirst() {
        List<Record> list = recordList(0, 1);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
