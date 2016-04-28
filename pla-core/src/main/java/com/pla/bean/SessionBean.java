package com.pla.bean;

import com.pla.transaction.SessionTransaction;
import com.pla.utils.SpringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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

        //In spring transaction wrap
        if(TransactionSynchronizationManager.isSynchronizationActive()){
            SessionFactory sfTrans = SpringUtil.getBean(sessionBeanId);
            this.inTransaction = true;
            return sfTrans.getCurrentSession();
        }
        Session sessionTrans = SessionTransaction.get();
        if (sessionTrans != null && sessionTrans.isOpen()) {
            this.inTransaction = true;
            return sessionTrans;
        }

        SessionFactory sessionFactory = SpringUtil.getBean(sessionBeanId);
        session = sessionFactory.openSession();
        return session;
    }

    public void close() {
        if (session != null && session.isOpen()) {
            session.close();
            session = null;
        }
    }
}
