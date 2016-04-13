package com.pla.transaction;

import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class SessionTransaction {
    private static ThreadLocal<Map<String, SessionFactory>> sessionThreadLocal = new ThreadLocal<Map<String, SessionFactory>>();

    public static void setSession(String beanId, SessionFactory sessionFactory) {
        Map<String, SessionFactory> sessionMap = sessionThreadLocal.get();
        if (sessionMap == null) {
            sessionMap = new HashMap<String, SessionFactory>();
        }
        sessionMap.put(beanId, sessionFactory);
        sessionThreadLocal.set(sessionMap);
    }

    public static SessionFactory getSession(String beanId) {
        Map<String, SessionFactory> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null) {
            SessionFactory sessionFactory = sessionMap.get(beanId);
            if (sessionFactory != null)
                return sessionFactory;
            else if (!sessionMap.isEmpty())
                return sessionMap.get("");
        }
        return null;
    }

    public static void remove(String beanId) {
        Map<String, SessionFactory> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null && !sessionMap.isEmpty()) {
            sessionMap.remove(beanId);
        }
        if (sessionMap != null && sessionMap.isEmpty()) {
            sessionThreadLocal.remove();
        }
    }

    public static void cleanAll() {
        Map<String, SessionFactory> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null && !sessionMap.isEmpty()) {
            for (String key : sessionMap.keySet()) {
                sessionMap.remove(key);
            }
        }
        sessionThreadLocal.remove();
    }
}
