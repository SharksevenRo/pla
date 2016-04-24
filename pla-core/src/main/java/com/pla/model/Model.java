package com.pla.model;

@SuppressWarnings("unchecked")
public abstract class Model<T> extends ModelFinder<T> {
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
        return ModelDaoFactory.getModelDao(getClass());
    }
}
