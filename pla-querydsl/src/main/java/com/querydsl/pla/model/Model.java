package com.querydsl.pla.model;

import com.pla.finder.Finder;
import com.pla.model.ModelDao;
import com.pla.model.ModelDaoFactory;
import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;
import com.pla.utils.ModelUtil;
import com.pla.utils.PojoUtil;

@SuppressWarnings("unchecked")
public abstract class Model<T> {
    public QueryByModel<T> finder() {
        return new Finder<T>().from((T) this);
    }

    public static <T> QueryByClass<T> finder(Class<T> clazz) {
        return new Finder<T>().from(clazz);
    }

    public Or or() {
        return Or.create(this);
    }


    /**
     * It will be called before save data.
     */
    protected void init() {
    }

    public void save() {
        init();
        getDao().save((T) this);
    }

    public void update() {
        getDao().update((T) this);
    }

    public void update(String... fields) {
        getDao().update((T) this, fields);
    }

    public void saveOrUpdate() {
        getDao().saveOrUpdate((T) this);
    }

    public void delete() {
        getDao().delete((T) this);
    }

    private ModelDao<T> getDao() {
        Class<T> clazz= PojoUtil.getSuperClassGenricType(getClass());
        String factoryBeanId = ModelUtil.getFactoryBeanId(clazz);
        return ModelDaoFactory.getModelDao(factoryBeanId);
    }
}
