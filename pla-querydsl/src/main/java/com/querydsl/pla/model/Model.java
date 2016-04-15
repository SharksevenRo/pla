package com.querydsl.pla.model;

import com.pla.utils.ModelUtil;
import com.querydsl.jpa.hibernate.HibernateQuery;

public abstract class Model<T> extends com.pla.model.Model<T> {
    public static <T> HibernateQuery<T> query(Class<T> clazz) {
        String beanId = ModelUtil.getFactoryBeanId(clazz);
        return new HibernateQuery<T>(beanId);
    }
}
