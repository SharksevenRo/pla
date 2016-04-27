package com.pla.transaction;

import org.hibernate.Session;

public class SessionTransaction {
    private static ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();

    public static void set(Session session) {
        sessionThreadLocal.set(session);
    }

    public static Session get() {
        return sessionThreadLocal.get();
    }

    public static void remove() {
        sessionThreadLocal.remove();
    }
}
