package com.pla.finder;


import com.pla.query.QueryByClass;

public class DFinderUtil {
    public static <M> QueryByClass<M> convert(DFinder<M> dFinder) {
        QueryByClass<M> query =  new Finder<M>().from(dFinder.getClazz());

        return query;
    }
}
