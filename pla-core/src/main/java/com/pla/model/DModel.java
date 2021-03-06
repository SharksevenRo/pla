package com.pla.model;


import com.pla.finder.DFinder;
import com.pla.finder.DFinderByModel;
import com.pla.query.Or;
import com.pla.service.CommonService;
import com.pla.utils.SpringUtil;

public class DModel<M> {
    private M m;

    public DModel(M m) {
        this.m = m;
    }

    public static <M> DModel<M> create(M m) {
        return new DModel<M>(m);
    }

    public M getModel() {
        return m;
    }

    public M save() {
        return getService().save(m);
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

    public final DFinderByModel<M> finder() {
        return DFinderByModel.createDFByModel(m);
    }

    public final static <M> DFinder<M> finder(Class<M> clazz) {
        return DFinder.createDFinder(clazz);
    }

    public Or or() {
        return Or.create(m);
    }

    private final static CommonService getService() {
        CommonService service = SpringUtil.getBean("commonService");
        return service;
    }
}
