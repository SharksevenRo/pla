package com.pla.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SimplePropertyUtil {
    public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Field[] fields = bean.getClass().getDeclaredFields();
        if (name.contains(".")) {
            String[] items = name.split("\\.");
            String item = items[0];
            StringBuilder otherItems = new StringBuilder();
            for (int i = 1; i < items.length; i++) {
                if (i != 1) {
                    otherItems.append(".");
                }
                otherItems.append(items[i]);
            }
            for (Field field : fields) {
                if (field.getName().equals(item)) {
                    Object obj = getProperty(bean, field.getName());
                    if (obj == null) {
                        obj = field.getType().newInstance();
                    }
                    setPropertySimple(bean, item, obj);
                    setProperty(obj, otherItems.toString(), value);
                    break;
                }
            }
        } else {
            setPropertySimple(bean, name, value);
        }
    }

	public static void setPropertySimple(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        } else if (name == null) {
            throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'");
        } else {
            Class clazz = bean.getClass();
            //Field[] fields = clazz.getDeclaredFields();
            PropertyDescriptor pd;
            try {
                pd = new PropertyDescriptor(name, clazz);
            } catch (IntrospectionException e) {
                throw new NoSuchMethodException("Unknown set method of property '" + name + "'  on class '" + bean.getClass() + "'");
            }

            Method wM = pd.getWriteMethod();
            wM.invoke(bean, value);
        }
    }

	public static <T> T getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        } else if (name == null) {
            throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'");
        } else {
            Class clazz = bean.getClass();
            if (name.contains(".")) {
                String[] items = name.split("\\.");
                String item = items[0];
                StringBuilder otherItems = new StringBuilder();
                for (int i = 1; i < items.length; i++) {
                    if (i != 1) {
                        otherItems.append(".");
                    }
                    otherItems.append(items[i]);
                }

                PropertyDescriptor pd;
                try {
                    pd = new PropertyDescriptor(item, clazz);
                } catch (IntrospectionException e) {
                    throw new NoSuchMethodException("Unknown get method of property '" + name + "'  on class '" + bean.getClass() + "'");
                }
                Method rM = pd.getReadMethod();
                Object obj = rM.invoke(bean);
                if (obj == null)
                    return null;
                else
                    return getProperty(obj, otherItems.toString());
            } else {
                PropertyDescriptor pd;
                try {
                    pd = new PropertyDescriptor(name, clazz);
                } catch (IntrospectionException e) {
                    throw new NoSuchMethodException("Unknown get method of property '" + name + "'  on class '" + bean.getClass() + "'");
                }
                Method rM = pd.getReadMethod();
                return (T) rM.invoke(bean);
            }
        }
    }

    public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        } else if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        } else {
            Class clazz = orig.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                String propertyName = f.getName();
                try {
                    Object value = getProperty(orig, propertyName);
                    setPropertySimple(dest, propertyName, value);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
}
