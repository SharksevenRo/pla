package com.pla.service;

import com.pla.finder.Finder;
import com.pla.model.ModelDao;
import com.pla.model.ModelDaoFactory;
import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;

import java.lang.reflect.Method;

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

    public final <M> QueryByModel<M> finder(M m) {
        return new Finder<M>().from(m);
    }

    public final <M> QueryByClass<M> finder(Class<M> clazz) {
        return new Finder<M>().from(clazz);
    }

    public <M> Or or(M m) {
        return Or.create(m);
    }
}
