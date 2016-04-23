package com.pla.model;

import com.pla.utils.ModelUtil;
import com.pla.utils.PojoUtil;

@SuppressWarnings("unchecked")
public abstract class Model<T> extends ModelFinder<T> {
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
