package com.pla.transaction;

import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class SessionFactoryTransaction {
    private static ThreadLocal<Map<String, SessionFactory>> sessionFactoryThreadLocal = new ThreadLocal<Map<String, SessionFactory>>();

    public static void set(String beanId, SessionFactory sessionFactory) {
        Map<String, SessionFactory> sfMap = sessionFactoryThreadLocal.get();
        if (sfMap == null) {
            sfMap = new HashMap<String, SessionFactory>();
        }
        sfMap.put(beanId, sessionFactory);
        sessionFactoryThreadLocal.set(sfMap);
    }

    public static SessionFactory get(String beanId) {
        Map<String, SessionFactory> sfMap = sessionFactoryThreadLocal.get();
        if (sfMap != null) {
            SessionFactory sessionFactory = sfMap.get(beanId);
            if (sessionFactory != null)
                return sessionFactory;
            else if (!sfMap.isEmpty())
                return sfMap.get("");
        }
        return null;
    }

    public static void remove(String beanId) {
        Map<String, SessionFactory> sfMap = sessionFactoryThreadLocal.get();
        if (sfMap != null && !sfMap.isEmpty()) {
            sfMap.remove(beanId);
        }
        if (sfMap != null && sfMap.isEmpty()) {
            sessionFactoryThreadLocal.remove();
        }
    }

    public static void cleanAll() {
        Map<String, SessionFactory> sfMap = sessionFactoryThreadLocal.get();
        if (sfMap != null && !sfMap.isEmpty()) {
            for (String key : sfMap.keySet()) {
                sfMap.remove(key);
            }
        }
        sessionFactoryThreadLocal.remove();
    }
}
