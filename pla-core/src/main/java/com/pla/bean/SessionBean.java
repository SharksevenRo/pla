package com.pla.bean;

import com.pla.transaction.SessionFactoryTransaction;
import com.pla.transaction.SessionTransaction;
import com.pla.utils.SpringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionBean {
    public static final String SF_BEANID = "sessionFactory";
    private String beanId;
    private Session session;
    private boolean inTransaction = false;

    public SessionBean() {
    }

    public SessionBean(String beanId) {
        this.beanId = beanId;
    }

    public boolean inTransaction() {
        return inTransaction;
    }

    public Session getSession() {
        if (session != null)
            return session;

        String sessionBeanId = beanId == null ? SF_BEANID : beanId;
        SessionFactory sfTrans = SessionFactoryTransaction.get(sessionBeanId);
        if (sfTrans != null) {
            this.inTransaction = true;
            return sfTrans.getCurrentSession();
        }
        Session sessionTrans = SessionTransaction.get();
        if (sessionTrans != null) {
            this.inTransaction = true;
            return sessionTrans;
        }

        if (beanId != null) {
            Object bean = SpringUtil.getBean(beanId);
            if (bean != null) {
                if (bean instanceof SessionFactory) {
                    session = ((SessionFactory) bean).openSession();
                    return session;
                }
            }
        } else {
            SessionFactory sessionFactory = SpringUtil.getBean(SF_BEANID);
            session = sessionFactory.openSession();
            return session;
        }
        return session;
    }

    public void close() {
        if (session != null && session.isOpen()) {
            session.close();
            session = null;
        }
    }
}
