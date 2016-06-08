package com.pla.service;

import com.pla.finder.DFinder;
import com.pla.query.Or;

public interface CommonService {
    <M> M save(M m);

    <M> void update(M m);

    <M> void update(M m, String... fields);

    <M> void saveOrUpdate(M m);

    <M> void delete(M m);

    <M> DFinder<M> finder(Class<M> clazz);

    <M> Or or(M m);
}
