package com.pla.model;

import com.pla.bean.SessionBean;

public class ModelDaoFactory {
    public static <T> ModelDao<T> getModelDao(String beanId) {
        SessionBean sessionBean = new SessionBean(beanId);
        return new HibernateModelDao<T>(sessionBean);
    }
}
