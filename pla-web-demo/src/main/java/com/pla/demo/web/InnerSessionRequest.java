package com.pla.demo.web;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Enumeration;

public class InnerSessionRequest extends HttpServletRequestWrapper {
    public InnerSessionRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession() {
        return new InnerSession(super.getSession());//InnerSessionHandler.getInstance(super.getSession());
    }

    private static class InnerSession implements HttpSession {
        private HttpSession session;

        public InnerSession(HttpSession session) {
            this.session = session;
        }

        public long getCreationTime() {
            return session.getCreationTime();
        }

        public String getId() {
            return session.getId();
        }

        public long getLastAccessedTime() {
            return session.getLastAccessedTime();
        }


        public ServletContext getServletContext() {
            return session.getServletContext();
        }

        public void setMaxInactiveInterval(int interval) {
            session.setMaxInactiveInterval(interval);
        }

        public int getMaxInactiveInterval() {
            return session.getMaxInactiveInterval();
        }


        public HttpSessionContext getSessionContext() {
            return session.getSessionContext();
        }


        public Object getAttribute(String name) {
            //TODO to do something
            return null;
        }


        public Object getValue(String name) {
            return session.getValue(name);
        }

        public Enumeration getAttributeNames() {
            return session.getAttributeNames();
        }


        public String[] getValueNames() {
            return session.getValueNames();
        }

        public void setAttribute(String name, Object value) {
            //TODO to do something
        }


        public void putValue(String name, Object value) {
            session.putValue(name, value);
        }


        public void removeAttribute(String name){
            //TODO to do something
        }


        public void removeValue(String name){
            session.removeValue(name);
        }


        public void invalidate(){
            //TODO to do something
        }


        public boolean isNew(){
            return session.isNew();
        }
    }

    private static class InnerSessionHandler implements InvocationHandler {
        private HttpSession session = null;

        private InnerSessionHandler(HttpSession httpSession) {
            this.session = httpSession;
        }

        public static HttpSession getInstance(HttpSession httpSession) {
            InvocationHandler handler = new InnerSessionHandler(httpSession);
            return (HttpSession) Proxy.newProxyInstance(httpSession.getClass().getClassLoader(), httpSession.getClass().getInterfaces(), handler);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("setAttribute".equals(method.getName())) {
                //TODO to do something
                return null;
            } else if ("getAttribute".equals(method.getName())) {
                //TODO to do something
                return null;
            } else if ("removeAttribute".equals(method.getName())) {
                //TODO to do something
                return null;
            } else if ("invalidate".equals(method.getName())) {
                //TODO to do something
                return null;
            }
            return method.invoke(session, args);
        }

    }
}


