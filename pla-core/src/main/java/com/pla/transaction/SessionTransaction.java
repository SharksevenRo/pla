package com.pla.transaction;

import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;

public class SessionTransaction {
    private static ThreadLocal<Map<String, Session>> sessionThreadLocal = new ThreadLocal<Map<String, Session>>();

    public static void set(String beanId, Session session) {
        Map<String, Session> sessionMap = sessionThreadLocal.get();
        if (sessionMap == null) {
            sessionMap = new HashMap<String, Session>();
        }
        sessionMap.put(beanId, session);
        sessionThreadLocal.set(sessionMap);
    }

    public static Session get(String beanId) {
        Map<String, Session> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null) {
            Session session = sessionMap.get(beanId);
            if (session != null)
                return session;
            else if (!sessionMap.isEmpty())
                return sessionMap.get("");
        }
        return null;
    }

    public static void remove(String beanId) {
        Map<String, Session> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null && !sessionMap.isEmpty()) {
            sessionMap.remove(beanId);
        }
        if (sessionMap != null && sessionMap.isEmpty()) {
            sessionThreadLocal.remove();
        }
    }

    public static void cleanAll() {
        Map<String, Session> sessionMap = sessionThreadLocal.get();
        if (sessionMap != null && !sessionMap.isEmpty()) {
            for (String key : sessionMap.keySet()) {
                sessionMap.remove(key);
            }
        }
        sessionThreadLocal.remove();
    }
}
