package com.pla.finder;

import com.pla.bean.SessionBean;
import com.pla.query.Pager;
import com.pla.query.Record;
import com.pla.utils.Util;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public class RecordFinder {
    private SessionBean sessionBean;

    public RecordFinder(String factoryBeanId) {
        sessionBean = new SessionBean(factoryBeanId);
    }

    public static RecordFinder create() {
        return new RecordFinder(null);
    }

    public static RecordFinder create(String factoryBeanId) {
        return new RecordFinder(factoryBeanId);
    }

    private Query sqlQueryMapping(String sql, Object... paras) {
        Session session = sessionBean.getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(AliasToEntityMapResultTransformer.create());
        if (paras != null) {
            int i = 0;
            for (Object param : paras) {
                query.setParameter(i++, param);
            }
        }
        return query;
    }

    public List<Record> query(String sql, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            return query.list();
        } finally {
            sessionBean.close();
        }
    }

    public List<Record> query(String sql) {
        return this.query(sql, Util.NULL_PARA_ARRAY);
    }

    public List<Record> query(String sql, int offset, int size, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            return query.list();
        } finally {
            sessionBean.close();
        }
    }

    public List<Record> query(String sql, int offset, int size) {
        try {
            Query query = this.sqlQueryMapping(sql);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            return query.list();
        } finally {
            sessionBean.close();
        }
    }

    public Pager<Record> query4Page(String sql, int pageNo, int pageSize, Object... paras) {
        try {
            Pager<Record> pager = new Pager<Record>(pageNo, pageSize);
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

    public Pager<Record> query4Page(String sql, int pageNo, int pageSize) {
        return this.query4Page(sql, pageNo, pageSize, Util.NULL_PARA_ARRAY);
    }

    public Record query4First(String sql, Object... paras) {
        try {
            List<Record> list = this.query(sql, 0, 1, paras);
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            } else
                return null;
        } finally {
            sessionBean.close();
        }
    }

    public Record query4First(String sql) {
        return this.query4First(sql, Util.NULL_PARA_ARRAY);
    }

    public Record uniqueResult(String sql, Object... paras) {
        try {
            Query query = this.sqlQueryMapping(sql, paras);
            return (Record) query.uniqueResult();
        } finally {
            sessionBean.close();
        }
    }

    public Record uniqueResult(String sql) {
        return this.uniqueResult(sql, Util.NULL_PARA_ARRAY);
    }


    private String getKey(Record record) {
        String resKey = null;
        if (record != null && record.keySet().size() == 1) {

            for (Object key : record.keySet()) {
                resKey = (String) key;
            }
        }
        return resKey;
    }

    public String queryStr(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getStr(this.getKey(record));
    }

    public String queryStr(String sql) {
        return queryStr(sql, Util.NULL_PARA_ARRAY);
    }

    public Integer queryInt(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getInt(this.getKey(record));
    }

    public Integer queryInt(String sql) {
        return queryInt(sql, Util.NULL_PARA_ARRAY);
    }

    public Long queryLong(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getLong(this.getKey(record));
    }

    public Long queryLong(String sql) {
        return queryLong(sql, Util.NULL_PARA_ARRAY);
    }

    public Double queryDouble(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getDouble(this.getKey(record));
    }

    public Double queryDouble(String sql) {
        return queryDouble(sql, Util.NULL_PARA_ARRAY);
    }

    public Float queryFloat(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getFloat(this.getKey(record));
    }

    public Float queryFloat(String sql) {
        return queryFloat(sql, Util.NULL_PARA_ARRAY);
    }

    public Date queryDate(String sql, Object... paras) {
        Record record = query4First(sql, paras);
        return record.getDate(this.getKey(record));
    }

    public Date queryDate(String sql) {
        return queryDate(sql, Util.NULL_PARA_ARRAY);
    }

}
