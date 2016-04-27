package com.pla.transaction;

import com.pla.bean.SessionBean;
import com.pla.utils.SpringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Trans {
    private SessionFactory sessionFactory;

    public Trans() {
        this(null);
    }

    public Trans(String beanId) {
        sessionFactory = SpringUtil.getBean(beanId == null ? SessionBean.SF_BEANID : beanId);
    }

    public static Trans create() {
        return new Trans();
    }

    public static Trans create(String beanId) {
        return new Trans(beanId);
    }

    public void tx(IAtom atom) {
        if (sessionFactory != null) {
            Transaction transaction = null;
            Session session = null;
            try {
                session = sessionFactory.openSession();
                SessionTransaction.set(session);
                transaction = session.getTransaction();
                transaction.begin();
                atom.run();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                throw new RuntimeException(e);
            } finally {
                SessionTransaction.remove();
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    }
}
