package com.pla.query;

import org.hibernate.Session;

@SuppressWarnings({"unchecked", "rawtypes"})
public class  HibernateQuery<T> extends QueryByModelImpl<T> implements Query<T>{
    public HibernateQuery(Session session) {
        this.session = session;
    }

	public  QueryByClass<T> from(Class<T> clazz) {
        if (clazz == null) {
            throw new RuntimeException();
        }
        this.clazz = clazz;
        return (QueryByClass) this;
    }

    public QueryByModel<T> from(T t) {
        if (t == null) {
            throw new RuntimeException();
        }
        this.t = t;
        this.clazz =(Class<T>) t.getClass();
        return (QueryByModel) this;
    }

    @Override
    protected void close() {}
}
