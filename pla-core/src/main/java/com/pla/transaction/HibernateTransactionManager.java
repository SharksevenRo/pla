package com.pla.transaction;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.ResourceTransactionManager;

@SuppressWarnings("serial")
public class HibernateTransactionManager extends org.springframework.orm.hibernate4.HibernateTransactionManager
        implements ResourceTransactionManager, BeanFactoryAware, InitializingBean {
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin(transaction, definition);
        SessionFactoryTransaction.set(sessionFactoryName == null ? "" : sessionFactoryName,
                this.getSessionFactory());
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        SessionFactoryTransaction.remove(sessionFactoryName == null ? "" : sessionFactoryName);
    }

    private String sessionFactoryName;

    public void setSessionFactoryName(String sessionFactoryName) {
        this.sessionFactoryName = sessionFactoryName;
    }
}
