package com.querydsl.pla.model;

import com.pla.model.ModelDao;
import com.pla.model.ModelDaoFactory;
import com.pla.utils.ModelUtil;
import com.pla.utils.PojoUtil;
import com.querydsl.jpa.hibernate.HibernateQuery;

@SuppressWarnings("unchecked")
public abstract class Model<T> {
    public static <T> HibernateQuery<T> query(Class<T> clazz) {
        String beanId = ModelUtil.getFactoryBeanId(clazz);
        return new HibernateQuery<T>(beanId);
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
        Class<T> clazz = PojoUtil.getSuperClassGenricType(getClass());
        String factoryBeanId = ModelUtil.getFactoryBeanId(clazz);
        return ModelDaoFactory.getModelDao(factoryBeanId);
    }
}
