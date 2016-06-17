package com.pla.finder;


import com.pla.utils.ConvertUtil;
import com.pla.utils.PropertyConvert;
import com.pla.utils.SimplePropertyUtil;
import org.hibernate.HibernateException;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Clob;

@SuppressWarnings({"serial", "rawtypes"})
public class AliasToBeanResultTransformer extends AliasedTupleSubsetResultTransformer {
    private final Class resultClass;

    public static AliasToBeanResultTransformer create(Class clazz) {
        return new AliasToBeanResultTransformer(clazz);
    }

    public AliasToBeanResultTransformer(Class resultClass) {
        if (resultClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        } else {
            this.resultClass = resultClass;
        }
    }

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    private void setter(Object model, Object value, String alias) {
        if (value == null)
            return;
        try {
            Field[] fields = model.getClass().getDeclaredFields();
            for (Field field : fields) {
                Transient tra = field.getAnnotation(Transient.class);
                if (tra != null) {
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                boolean isMatch = false;
                if (column != null && column.name().equalsIgnoreCase(alias)) {
                    isMatch = true;
                } else if (PropertyConvert.convert(field.getName()).equalsIgnoreCase(alias)) {
                    isMatch = true;
                } else {
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), model.getClass());
                        Method rM = pd.getReadMethod();
                        column = rM.getAnnotation(Column.class);
                        if (column != null && column.name().equalsIgnoreCase(alias)) {
                            isMatch = true;
                        }
                    } catch (IntrospectionException e) {
                    }
                }

                if (isMatch) {
                    if (value instanceof String) {
                        if (String.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), value);
                        }
                    } else if (value instanceof Number) {
                        if (Long.class.isAssignableFrom(field.getType()) ||
                                long.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).longValue());
                        } else if (Integer.class.isAssignableFrom(field.getType()) ||
                                int.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).intValue());
                        } else if (Short.class.isAssignableFrom(field.getType()) ||
                                short.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).shortValue());
                        } else if (Double.class.isAssignableFrom(field.getType()) ||
                                double.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).doubleValue());
                        } else if (Float.class.isAssignableFrom(field.getType()) ||
                                float.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).floatValue());
                        } else if (Byte.class.isAssignableFrom(field.getType()) ||
                                byte.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ((Number) value).byteValue());
                        } else if (String.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), value.toString());
                        }
                    } else if (value instanceof Blob) {
                        if (byte[].class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ConvertUtil.blobToBytes((Blob) value));
                        }
                    } else if (value instanceof Clob) {
                        if (String.class.isAssignableFrom(field.getType())) {
                            SimplePropertyUtil.setProperty(model, field.getName(), ConvertUtil.clobToString((Clob) value));
                        }
                    } else {
                        SimplePropertyUtil.setProperty(model, field.getName(), value);
                    }
                    break;
                }
            }

        } catch (Exception e) {
            throw new HibernateException("Could not find setter for " + alias + " on class "
                    + model.getClass().getName());
        }
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        try {
            Object result = this.resultClass.newInstance();

            for (int e = 0; e < aliases.length; ++e) {
                this.setter(result, tuple[e], aliases[e]);
            }

            return result;
        } catch (InstantiationException var5) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
        } catch (IllegalAccessException var6) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
        }
    }
}
