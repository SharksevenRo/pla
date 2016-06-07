package com.pla.service;

import com.pla.query.Or;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;

public interface CommonService {
    <M> void save(M m);

    <M> void update(M m);

    <M> void update(M m, String... fields);

    <M> void saveOrUpdate(M m);

    <M> void delete(M m);

    <M> QueryByModel<M> finder(M m);

    <M> QueryByClass<M> finder(Class<M> clazz);

    <M> Or or(M m);
}
