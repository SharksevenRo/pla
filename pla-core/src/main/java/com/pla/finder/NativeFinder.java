package com.pla.finder;

import com.pla.bean.SessionBean;
import com.pla.query.Pager;
import com.pla.utils.ModelUtil;
import com.pla.utils.SimplePropertyUtil;
import com.pla.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"unchecked", "rawtypes"})
public class NativeFinder<T> {
    private SessionBean sessionBean;
    private Class<T> clazz;

    public NativeFinder(Class<T> clazz) {
        String beanId = ModelUtil.getFactoryBeanId(clazz);
        sessionBean = new SessionBean(beanId);
        this.clazz = clazz;
    }

    public static <T> NativeFinder<T> create(Class<T> clazz) {
        return new NativeFinder(clazz);
    }

    private Query sqlQueryMapping(String sql, Object... paras) {
        Session session = sessionBean.getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(AliasToBeanResultTransformer.create(clazz));
        if (paras != null) {
            int i = 0;
            for (Object param : paras) {
                query.setParameter(i++, param);
            }
        }
        return query;
    }

    public List<T> query(String sql, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            return query.list();
        } finally {
            sessionBean.close();
        }
    }

    public List<T> query(String sql) {
        return this.query(sql, Util.NULL_PARA_ARRAY);
    }

    public List<T> query(String sql, int offset, int size, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            return query.list();
        } finally {
            sessionBean.close();
        }
    }

    public List<T> query(String sql, int offset, int size) {
        return this.query(sql, offset, size, Util.NULL_PARA_ARRAY);
    }

    public Pager<T> query4Page(String sql, int pageNo, int pageSize, Object... paras) {
        try {
            Pager<T> pager = new Pager<T>(pageNo, pageSize);
            Query query = this.sqlQueryMapping(sql, paras);
            query.setFirstResult(pager.getOffset());
            query.setMaxResults(pager.getPageSize());
            pager.setList(query.list());

            String cntSql = "select count(1) from (" + sql + " )  ";
            Session session = sessionBean.getSession();
            Query cntQuery = session.createSQLQuery(cntSql);
            int count = ((Number) cntQuery.uniqueResult()).intValue();
            pager.setTotalCount(count);

            return pager;
        } finally {
            sessionBean.close();
        }
    }

    public Pager<T> query4Page(String sql, int pageNo, int pageSize) {
        return this.query4Page(sql, pageNo, pageSize, Util.NULL_PARA_ARRAY);
    }

    public T query4First(String sql, Object... paras) {
        try {
            List<T> list = this.query(sql, 0, 1, paras);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            } else {
                return null;
            }
        } finally {
            sessionBean.close();
        }
    }

    public T query4First(String sql) {
        return this.query4First(sql, Util.NULL_PARA_ARRAY);
    }

    public T uniqueResult(String sql, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            return (T) query.uniqueResult();
        } finally {
            sessionBean.close();
        }
    }

    public T uniqueResult(String sql) {
        return this.uniqueResult(sql, Util.NULL_PARA_ARRAY);
    }


    public List<T> query2(String sql, Object obj) {
        try {
            StringBuilder querySql = new StringBuilder();
            String rex = "#\\{(.*?)}";
            Pattern p = Pattern.compile(rex);
            Matcher m = p.matcher(sql);
            List<String> valueStrs = new ArrayList<String>();
            while (m.find()) {
                valueStrs.add(sql.substring(m.start() + 2, m.end() - 1));
                querySql.append(sql.substring(0, m.start()) + "?" + sql.substring(m.end() + 1));
            }

            Query query;
            //single param
            if (m.groupCount() == 1) {
                query = this.sqlQueryMapping(sql, obj);
            } else {
                List<Object> values = new ArrayList<Object>();
                for (String valueStr : valueStrs) {
                    Object value = SimplePropertyUtil.getProperty(obj, valueStr);
                    if (value == null) {
                        throw new HibernateException("");
                    }
                    values.add(value);
                }
                query = this.sqlQueryMapping(sql, values.toArray());
            }
            return query.list();
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            sessionBean.close();
        }
    }

}
