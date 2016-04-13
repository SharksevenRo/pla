package com.pla.utils;

import com.pla.model.FactoryBeanId;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModelUtil {
    public static String getFactoryBeanId(Class clazz) {
        FactoryBeanId factoryBeanId = (FactoryBeanId) clazz.getAnnotation(FactoryBeanId.class);
        if (factoryBeanId != null && factoryBeanId.value() != null
                && !factoryBeanId.value().equals("")) {
            return factoryBeanId.value();
        } else {
            return null;
        }
    }

    public static Serializable getIdValue(Object model) {
        try {
            Class clazz = model.getClass();
            Serializable id = null;
            Method method = getIdMethod(clazz);
            if (method != null) {
                id = (Serializable) method.invoke(model);
            } else {
                Field field = getIdField(clazz);
                if (field != null) {
                    id = SimplePropertyUtil.getProperty(model, field.getName());
                }
            }
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    public static Method getIdMethod(Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Id md = method.getAnnotation(Id.class);
            if (md != null) {
                return method;
            }
        }
        return null;
    }

    public static Field getIdField(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Id column = field.getAnnotation(Id.class);
            if (column != null) {
                return field;
            }
        }
        return null;
    }

    public static Field[] getEntityProperties(Class clazz) {
        List<Field> fields = new ArrayList<Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Transient tranField = field.getAnnotation(Transient.class);
            if (tranField != null) {
                continue;
            }
            String filedName = field.getName();
            try {
                PropertyDescriptor pd = new PropertyDescriptor(filedName, clazz);
                Method methodTmp = pd.getReadMethod();
                tranField = methodTmp.getAnnotation(Transient.class);

                if (tranField != null) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
            fields.add(field);
        }
        return fields.toArray(new Field[0]);
    }
}