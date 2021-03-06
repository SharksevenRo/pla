package com.pla.finder;

import com.pla.bean.SessionBean;
import com.pla.query.Query;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;
import com.pla.query.QueryByModelImpl;
import com.pla.utils.ModelUtil;

@SuppressWarnings("unchecked")
public class Finder<T> extends QueryByModelImpl<T> implements Query<T> {
    private SessionBean sessionBean;
    private String beanId;

    public Finder() {
    }

    public Finder(String beanId) {
        this.beanId = beanId;
    }

    public void init(Class<T> clazz) {
        if (beanId == null) {
            beanId = ModelUtil.getFactoryBeanId(clazz);
        }
        this.sessionBean = new SessionBean(beanId);
        this.session = this.sessionBean.getSession();
    }

    public QueryByClass<T> from(Class<T> clazz) {
        this.clazz = clazz;
        init(this.clazz);
        return this;
    }

    public QueryByModel<T> from(T t) {
        if (t == null) {
            throw new RuntimeException();
        }
        this.t = t;
        this.clazz = (Class<T>) t.getClass();
        init(this.clazz);
        return this;
    }

    public QueryByModel<T> from(T t, Class<T> clazz) {
        if (t == null || clazz == null) {
            throw new RuntimeException();
        }
        this.t = t;
        this.clazz = clazz;
        init(this.clazz);
        return this;
    }

    @Override
    protected void close() {
        sessionBean.close();
    }
}
