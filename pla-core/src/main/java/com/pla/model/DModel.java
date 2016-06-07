package com.pla.model;


import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;
import com.pla.service.CommonService;
import com.pla.utils.SpringUtil;

public class DModel<M> {
    private M m;

    public DModel(M m) {
        this.m = m;
    }

    public static <M> DModel create(M m) {
        return new DModel<M>(m);
    }

    public M getModel() {
        return m;
    }

    public void save() {
        getService().save(m);
    }

    public void update() {
        getService().update(m);
    }

    public void update(String... fields) {
        getService().update(m, fields);
    }

    public void saveOrUpdate() {
        getService().saveOrUpdate(m);
    }

    public void delete() {
        getService().delete(m);
    }

    public final QueryByModel<M> finder() {
        return getService().finder(m);
    }

    public final static <M> QueryByClass<M> finder(Class<M> clazz) {
        return  getService().finder(clazz);
    }

    public Or or() {
        return Or.create(m);
    }

    private final static CommonService getService() {
        CommonService service = SpringUtil.getBean("commonService");
        return service;
    }
}
