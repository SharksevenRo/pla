package com.pla.bean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pla.transaction.SessionTransaction;
import com.pla.utils.SpringUtil;

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
		SessionFactory sessionFaoryTran = SessionTransaction.get(sessionBeanId);
		if (sessionFaoryTran != null) {
			this.inTransaction = true;
			return sessionFaoryTran.getCurrentSession();
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
