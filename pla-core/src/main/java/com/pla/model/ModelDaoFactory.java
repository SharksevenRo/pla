package com.pla.model;

import com.pla.bean.SessionBean;
import com.pla.utils.ModelUtil;

public class ModelDaoFactory {
    public static <T> ModelDao<T> getModelDao(Class clazz) {
//        Class<T> modelClass = PojoUtil.getSuperClassGenricType(clazz);
        String factoryBeanId = ModelUtil.getFactoryBeanId(clazz);
        SessionBean sessionBean = new SessionBean(factoryBeanId);
        return new HibernateModelDao<T>(sessionBean);
    }
}
