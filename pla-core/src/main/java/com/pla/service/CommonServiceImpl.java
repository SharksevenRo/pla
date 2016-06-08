package com.pla.service;

import com.pla.dao.Criteria;
import com.pla.finder.DFinder;
import com.pla.finder.DFinderUtil;
import com.pla.model.ModelDao;
import com.pla.model.ModelDaoFactory;
import com.pla.query.Or;
import com.pla.query.Pager;
import com.pla.query.QueryByClass;
import com.pla.query.Record;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("unchecked")
public class CommonServiceImpl implements CommonService {
    public <M> M save(M m) {
        //invoke init method before save
        try {
            Method[] methods = m.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ("init".equals(method.getName())) {
                    method.invoke(m);
                    break;
                }
            }
        } catch (Exception e) {
        }
        getDao((Class<M>) m.getClass()).save(m);
        return m;
    }

    public <M> void update(M m) {
        getDao((Class<M>) m.getClass()).update(m);
    }

    public <M> void update(M m, String... fields) {
        getDao((Class<M>) m.getClass()).update(m, fields);
    }

    public <M> void saveOrUpdate(M m) {
        getDao((Class<M>) m.getClass()).saveOrUpdate(m);
    }

    public <M> void delete(M m) {
        getDao((Class<M>) m.getClass()).delete(m);
    }

    private final static <M> ModelDao<M> getDao(Class<M> clazz) {
        return ModelDaoFactory.getModelDao(clazz);
    }

    public final <M> DFinder finder(Class<M> clazz) {
        return DFinder.createDFinder(clazz);
    }

    public <M> Or or(M m) {
        return Or.create(m);
    }

    private final <M> QueryByClass<M> getQuery(Criteria criteria, Class<M> clazz) {
        DFinderUtil<M> du = new DFinderUtil<M>();
        return du.convert(criteria, clazz);
    }

    public <M> M load(Criteria criteria, Class<M> clazz, Serializable id) {
        return getQuery(criteria, clazz).load(id);
    }

    public <M> M first(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).first();
    }

    public <M> List<M> list(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).list();
    }

    public <M> List<M> list(Criteria criteria, Class<M> clazz, int offset, int size) {
        return getQuery(criteria, clazz).list(offset, size);
    }

    public <M> int count(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).count();
    }

    public <M> M uniqueResult(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).uniqueResult();
    }

    public <M> Pager<M> pager(Criteria criteria, Class<M> clazz, int pageNo, int pageSize) {
        return getQuery(criteria, clazz).pager(pageNo, pageSize);
    }

    // -------------------------- QueryOp4Parts --------------------------
    public <M> M uniqueResult(Criteria criteria, Class<M> clazz, String... propertyNames) {
        return getQuery(criteria, clazz).uniqueResult(propertyNames);
    }

    public <M> M first(Criteria criteria, Class<M> clazz, String... propertyNames) {
        return getQuery(criteria, clazz).first(propertyNames);
    }

    public <M> List<M> list(Criteria criteria, Class<M> clazz, String... propertyNames) {
        return getQuery(criteria, clazz).list(propertyNames);
    }

    public <M> List<M> list(Criteria criteria, Class<M> clazz, Integer offset, Integer size, String... propertyNames) {
        return getQuery(criteria, clazz).list(offset, size, propertyNames);
    }

    public <M> Pager<M> pager(Criteria criteria, Class<M> clazz, int pageNo, int pageSize, String... propertyNames) {
        return getQuery(criteria, clazz).pager(pageNo, pageSize, propertyNames);
    }


    // -------------------------- Group By --------------------------
    public <M> Record record(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).record();
    }

    public <M> List<Record> recordList(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).recordList(null, null);
    }

    public <M> List<Record> recordList(Criteria criteria, Class<M> clazz, Integer offset, Integer size) {
        return getQuery(criteria, clazz).recordList(offset, size);
    }

    public <M> Record recordFirst(Criteria criteria, Class<M> clazz) {
        return getQuery(criteria, clazz).recordFirst();
    }
}
