package com.pla.finder;

import com.pla.dao.Criteria;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class DFinder<M> extends Criteria implements Serializable {
    private static final long serialVersionUID = 3638237321915801747L;

    public DFinder(Class clazz) {
        super(clazz);
    }

    public static DFinder create(Class clazz) {
        return new DFinder(clazz);
    }



    public M load(Serializable id){
        return (M) new Finder<M>().from(getClazz()).load(id);
    }
}
