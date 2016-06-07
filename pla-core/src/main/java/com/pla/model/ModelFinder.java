package com.pla.model;

import com.pla.finder.Finder;
import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;

public abstract class ModelFinder<T> {
    @SuppressWarnings("unchecked")
    public final QueryByModel<T> finder() {
        return new Finder<T>().from((T) this);
    }

    public final static <T> QueryByClass<T> finder(Class<T> clazz) {
        return new Finder<T>().from(clazz);
    }

    public Or or() {
        return Or.create(this);
    }
}
