package com.pla.model;

public abstract class Model<T> extends ModelFinder<T> {
    /**
     * It will be called before save data.
     */
    protected void init() {
    }

    @SuppressWarnings("unchecked")
    public void save() {
        init();
        getDao().save((T) this);
    }

    @SuppressWarnings("unchecked")
    public void update() {
        getDao().update((T) this);
    }

    @SuppressWarnings("unchecked")
    public void update(String... fields) {
        getDao().update((T) this, fields);
    }

    @SuppressWarnings("unchecked")
    public void saveOrUpdate() {
        getDao().saveOrUpdate((T) this);
    }

    @SuppressWarnings("unchecked")
    public void delete() {
        getDao().delete((T) this);
    }

    private ModelDao<T> getDao() {
        return ModelDaoFactory.getModelDao(getClass());
    }
}
