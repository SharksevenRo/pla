package com.pla.utils;

import com.pla.PLAConfiguration;

@SuppressWarnings("unchecked")
public class SpringUtil {
	public static <T> T getBean(String beanName) {
        return (T) PLAConfiguration.getBean(beanName);
    }

    public static  <T> T getBean(String beanName, Class<T> clazz) {
        return PLAConfiguration.getBean(beanName, clazz);
    }
}
