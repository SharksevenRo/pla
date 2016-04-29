package com.pla.demo.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InnerSessionRequest extends HttpServletRequestWrapper {
    public InnerSessionRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession() {
        return InnerSessionHandler.getInstance(super.getSession());
    }
}


class InnerSessionHandler implements InvocationHandler {
    private HttpSession session = null;

    private InnerSessionHandler(HttpSession httpSession) {
        this.session = httpSession;
    }

    ;

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