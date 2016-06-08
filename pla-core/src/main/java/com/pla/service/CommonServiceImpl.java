package com.pla.service;

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

    public final <M> DFinder<M> finder(Class<M> clazz) {
        return DFinder.createDFinder(clazz);
    }

    public <M> Or or(M m) {
        return Or.create(m);
    }

    private final <M> QueryByClass<M> getQuery(DFinder dFinder, Class<M> clazz) {
        DFinderUtil<M> du = new DFinderUtil<M>();
        return du.convert(dFinder, clazz);
    }

    public <M> M load(DFinder dFinder, Class<M> clazz, Serializable id) {
        return getQuery(dFinder, clazz).load(id);
    }

    public <M> M first(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).first();
    }

    public <M> List<M> list(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).list();
    }

    public <M> List<M> list(DFinder dFinder, Class<M> clazz, int offset, int size) {
        return getQuery(dFinder, clazz).list(offset, size);
    }

    public <M> int count(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).count();
    }

    public <M> M uniqueResult(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).uniqueResult();
    }

    public <M> Pager<M> pager(DFinder dFinder, Class<M> clazz, int pageNo, int pageSize) {
        return getQuery(dFinder, clazz).pager(pageNo, pageSize);
    }

    // -------------------------- QueryOp4Parts --------------------------
    public <M> M uniqueResult(DFinder dFinder, Class<M> clazz, String... propertyNames) {
        return getQuery(dFinder, clazz).uniqueResult(propertyNames);
    }

    public <M> M first(DFinder dFinder, Class<M> clazz, String... propertyNames) {
        return getQuery(dFinder, clazz).first(propertyNames);
    }

    public <M> List<M> list(DFinder dFinder, Class<M> clazz, String... propertyNames) {
        return getQuery(dFinder, clazz).list(propertyNames);
    }

    public <M> List<M> list(DFinder dFinder, Class<M> clazz, Integer offset, Integer size, String... propertyNames) {
        return getQuery(dFinder, clazz).list(offset, size, propertyNames);
    }

    public <M> Pager<M> pager(DFinder dFinder, Class<M> clazz, int pageNo, int pageSize, String... propertyNames) {
        return getQuery(dFinder, clazz).pager(pageNo, pageSize, propertyNames);
    }


    // -------------------------- Group By --------------------------
    public <M> Record record(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).record();
    }

    public <M> List<Record> recordList(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).recordList(null, null);
    }

    public <M> List<Record> recordList(DFinder dFinder, Class<M> clazz, Integer offset, Integer size) {
        return getQuery(dFinder, clazz).recordList(offset, size);
    }

    public <M> Record recordFirst(DFinder dFinder, Class<M> clazz) {
        return getQuery(dFinder, clazz).recordFirst();
    }
}
