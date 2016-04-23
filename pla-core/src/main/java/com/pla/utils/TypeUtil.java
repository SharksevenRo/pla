package com.pla.utils;

import org.hibernate.type.*;

import java.util.Date;

@SuppressWarnings("rawtypes")
public class TypeUtil {
	public static Type getType(Object value) {
        Class clazz = value.getClass();
        Type type;
        if (value instanceof String) {
            type = StringType.INSTANCE;
        } else if (int.class.isAssignableFrom(clazz) || Integer.class.isAssignableFrom(clazz)) {
            type = IntegerType.INSTANCE;
        } else if (long.class.isAssignableFrom(clazz) || Long.class.isAssignableFrom(clazz)) {
            type = LongType.INSTANCE;
        } else if (short.class.isAssignableFrom(clazz) || Short.class.isAssignableFrom(clazz)) {
            type = ShortType.INSTANCE;
        } else if (double.class.isAssignableFrom(clazz) || Double.class.isAssignableFrom(clazz)) {
            type = DoubleType.INSTANCE;
        } else if (float.class.isAssignableFrom(clazz) || Float.class.isAssignableFrom(clazz)) {
            type = FloatType.INSTANCE;
        } else if (Date.class.isAssignableFrom(clazz)) {
            type = TimestampType.INSTANCE;
        } else {
            throw new RuntimeException("Unknown type of value '" + value.getClass().getName() + "'");
        }
        return type;
    }
}
