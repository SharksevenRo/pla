package com.pla.model;

import com.pla.bean.SessionBean;
import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class HibernateModelDao<T> implements ModelDao<T> {
    private SessionBean sessionBean;

    HibernateModelDao(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public void save(T t) {
        Session session = sessionBean.getSession();
        if (!sessionBean.inTransaction()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                session.save(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new HibernateException(e);
            } finally {
                sessionBean.close();
            }
        } else {
            session.save(t);
        }
    }

    public void update(T t) {
        Session session = sessionBean.getSession();
        if (!sessionBean.inTransaction()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                session.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new HibernateException(e);
            } finally {
                sessionBean.close();
            }
        } else {
            session.update(t);
        }
    }

    public void update(T t, String... fields) {
        Session session = sessionBean.getSession();
        if (!sessionBean.inTransaction()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                updateOpration(t, fields);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new HibernateException(e);
            } finally {
                sessionBean.close();
            }
        } else {
            updateOpration(t, fields);
        }
    }

    private void updateOpration(T t, String... fields) {
        Session session = sessionBean.getSession();
        Serializable id = ModelUtil.getIdValue(t);
        if (id == null)
            return;

        T origT = (T) session.load(t.getClass(), id);
        if (origT == null)
            return;

        try {
            for (String fieldName : fields) {
                //ignore null
                if (fieldName.endsWith(":INULL")) {
                    fieldName = fieldName.substring(0, fieldName.length() - 6);
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
            throw new HibernateException(e);
        }
        session.update(origT);
    }

    public void saveOrUpdate(T t) {
        Session session = sessionBean.getSession();
        if (!sessionBean.inTransaction()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                session.saveOrUpdate(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new HibernateException(e);
            } finally {
                sessionBean.close();
            }
        } else {
            session.saveOrUpdate(t);
        }
    }


    public void delete(T t) {
        Session session = sessionBean.getSession();
        if (!sessionBean.inTransaction()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                Serializable id = ModelUtil.getIdValue(t);
                if (id == null)
                    return;
                T origT = (T) session.load(t.getClass(), id);
                if (origT == null)
                    return;
                session.delete(origT);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new HibernateException(e);
            } finally {
                sessionBean.close();
            }
        } else {
            Serializable id = ModelUtil.getIdValue(t);
            if (id == null)
                return;
            T origT = (T) session.load(t.getClass(), id);
            if (origT == null)
                return;
            session.delete(origT);
        }
    }
}
