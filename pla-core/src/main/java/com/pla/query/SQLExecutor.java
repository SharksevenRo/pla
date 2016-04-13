package com.pla.query;

import com.pla.bean.SessionBean;
import com.pla.utils.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SQLExecutor {
    private SessionBean sessionBean;

    public SQLExecutor(String factoryBeanId) {
        sessionBean = new SessionBean(factoryBeanId);
    }

    public static SQLExecutor create() {
        SQLExecutor sqlExecutorutor = new SQLExecutor(null);
        return sqlExecutorutor;
    }

    public static SQLExecutor create(String factoryBeanId) {
        SQLExecutor sqlExecutorutor = new SQLExecutor(factoryBeanId);
        return sqlExecutorutor;
    }

    private Query sqlQuery(String sql, Object... paras) {
        Session session = sessionBean.getSession();
        Query query = session.createSQLQuery(sql);
        if (paras != null) {
            int i = 0;
            for (Object param : paras) {
                query.setParameter(i++, param);
            }
        }
        return query;
    }

    public int execute(String sql, Object... paras) {
        try {
            int res;
            if (!sessionBean.inTransaction()) {
                Transaction transaction = sessionBean.getSession().getTransaction();
                try {
                    transaction.begin();
                    Query query = this.sqlQuery(sql, paras);
                    res = query.executeUpdate();
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new RuntimeException(e);
                }
            } else {
                Query query = this.sqlQuery(sql, paras);
                res = query.executeUpdate();
            }

            return res;
        } finally {
            sessionBean.close();
        }

    }

    public int execute(String sql) {
        return execute(sql, Util.NULL_PARA_ARRAY);
    }
}
