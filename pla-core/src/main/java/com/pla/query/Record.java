package com.pla.query;


import com.pla.utils.PropertyConvert;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class Record extends HashMap implements Map, Cloneable, Serializable {
    public Record() {
        super();
    }

    public Record(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public Object get(Object key) {
        return super.get(PropertyConvert.convert((String) key));
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(PropertyConvert.convert((String) key));
    }

    @Override
    public Object put(Object key, Object value) {
        return super.put(PropertyConvert.convert((String) key), value);
    }

    @Override
    public void putAll(Map m) {
        for (Map.Entry e : (Set<Map.Entry>) (m.entrySet()))
            super.put(((String) e.getKey()).toUpperCase(), e.getValue());
    }

    @Override
    public Object remove(Object key) {
        return super.remove(PropertyConvert.convert((String) key));
    }

    public <R> R get(String key, Class<R> clazz) {
        Object value = this.get(key);
        if (value == null)
            return null;
        if (value instanceof Number) {
            if (Long.class.isAssignableFrom(clazz) ||
                    long.class.isAssignableFrom(clazz)) {
                return (R) new Long(((Number) value).longValue());
            } else if (Integer.class.isAssignableFrom(clazz) ||
                    int.class.isAssignableFrom(clazz)) {
                return (R) new Integer(((Number) value).intValue());
            } else if (Short.class.isAssignableFrom(clazz) ||
                    short.class.isAssignableFrom(clazz)) {
                return (R) new Short(((Number) value).shortValue());
            } else if (Double.class.isAssignableFrom(clazz) ||
                    double.class.isAssignableFrom(clazz)) {
                return (R) new Double(((Number) value).doubleValue());
            } else if (Float.class.isAssignableFrom(clazz) ||
                    float.class.isAssignableFrom(clazz)) {
                return (R) new Float(((Number) value).floatValue());
            } else if (Byte.class.isAssignableFrom(clazz) ||
                    byte.class.isAssignableFrom(clazz)) {
                return (R) new Byte(((Number) value).byteValue());
            }
        }

        return (R) value;
    }


    public String getStr(String column) {
        return this.get(column, String.class);
    }

    public Integer getInt(String column) {
        return this.get(column, Integer.class);
    }

    public Short getShort(String column) {
        return this.get(column, Short.class);
    }

    public Long getLong(String column) {
        return this.get(column, Long.class);
    }

    public Date getDate(String column) {
        return this.get(column, Date.class);
    }

    public Double getDouble(String column) {
        return this.get(column, Double.class);
    }

    public Float getFloat(String column) {
        return this.get(column, Float.class);
    }

    public Boolean getBoolean(String column) {
        return this.get(column, Boolean.class);
    }

    public byte[] getBytes(String column) {
        return this.get(column, byte[].class);
    }

    public Number getNumber(String column) {
        return this.get(column, Number.class);
    }

    public static Record map2Record(Map map) {
        Record record = new Record();
        record.putAll(map);
        return record;
    }
}
