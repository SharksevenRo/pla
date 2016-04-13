package com.pla;

import org.hibernate.cfg.NamingStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class PLAConfiguration implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private static NamingStrategy recordNamingStrategy;

    public void setRecordNamingStrategy(NamingStrategy namingStrategy) {
        recordNamingStrategy = namingStrategy;
    }

    public static NamingStrategy getNamingStrategy() {
        return recordNamingStrategy;
    }


}
