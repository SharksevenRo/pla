package com.pla.model;


import com.pla.finder.Finder;
import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;

import java.lang.reflect.Method;

public class PModel<T> {
    private T t;

    public PModel(T t) {
        this.t = t;
    }

    public static <T> PModel convert(T t) {
        return new PModel<T>(t);
    }

    public void save() {
        //invoke init method before save
        try {
            Method[] methods = t.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ("init".equals(method.getName())) {
                    method.invoke(t);
                    break;
                }
            }
        } catch (Exception e) {
        }

        getDao().save(t);
    }

    public void update() {
        getDao().update(t);
    }

    public void update(String... fields) {
        getDao().update(t, fields);
    }

    public void saveOrUpdate() {
        getDao().saveOrUpdate(t);
    }

    public void delete() {
        getDao().delete(t);
    }

    private ModelDao<T> getDao() {
        return ModelDaoFactory.getModelDao(t.getClass());
    }

    public QueryByModel<T> finder() {
        return new Finder<T>().from(t);
    }

    public static <T> QueryByClass<T> finder(Class<T> clazz) {
        return new Finder<T>().from(clazz);
    }

    public Or or() {
        return Or.create(t);
    }
}
