package com.pla.dao;

import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class HibernateDAO<T> extends BaseDAO<T> implements IBaseDAO<T> {
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected Session getSession() {
        if (sessionFactory == null) {
            throw new RuntimeException("No sessionFactory found. ");
        }
        return sessionFactory.getCurrentSession();
    }

    public void save(T t) {
        getSession().save(t);
    }

    public void update(T t) {
        getSession().update(t);
    }

    public void update(T t, String... fields) {
        Serializable id = ModelUtil.getIdValue(t);
        if (id == null)
            return;

        T origT = (T) getSession().load(t.getClass(), id);
        if (origT == null)
            return;

        try {
            for (String fieldName : fields) {
                //ignore null
                if (fieldName.endsWith(":INULL")) {
                    fieldName = fieldName.substring(0, fieldName.length() - 8);
                    Object value = SimplePropertyUtil.getProperty(t, fieldName);
                    if (value != null) {
                        SimplePropertyUtil.setProperty(origT, fieldName, value);
                    }
                } else {
                    Object value = SimplePropertyUtil.getProperty(t, fieldName);
                    SimplePropertyUtil.setProperty(origT, fieldName, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getSession().update(origT);
    }

    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }

    public void delete(Serializable id) {
        if (id == null)
            return;
        T origT = (T) getSession().load(super.clazz, id);
        if (origT == null)
            return;
        getSession().delete(origT);
    }
}
