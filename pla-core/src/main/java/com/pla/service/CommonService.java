package com.pla.service;

import com.pla.finder.DFinder;
import com.pla.query.Or;
import com.pla.query.Pager;
import com.pla.query.QueryByClass;
import com.pla.query.Record;

import java.io.Serializable;
import java.util.List;

public interface CommonService {
    <M> M save(M m);

    <M> void update(M m);

    <M> void update(M m, String... fields);

    <M> void saveOrUpdate(M m);

    <M> void delete(M m);

    <M> DFinder finder(Class<M> clazz);

    <M> Or or(M m);

    <M> M load(DFinder dFinder, Class<M> clazz, Serializable id);


    <M> M first(DFinder dFinder, Class<M> clazz);

    <M> List<M> list(DFinder dFinder, Class<M> clazz);

    <M> List<M> list(DFinder dFinder, Class<M> clazz, int offset, int size);

    <M> int count(DFinder dFinder, Class<M> clazz);

    <M> M uniqueResult(DFinder dFinder, Class<M> clazz);

    <M> Pager<M> pager(DFinder dFinder, Class<M> clazz, int pageNo, int pageSize);

    // -------------------------- QueryOp4Parts --------------------------
    <M> M uniqueResult(DFinder dFinder, Class<M> clazz, String... propertyNames);

    <M> M first(DFinder dFinder, Class<M> clazz, String... propertyNames);

    <M> List<M> list(DFinder dFinder, Class<M> clazz, String... propertyNames);

    <M> List<M> list(DFinder dFinder, Class<M> clazz, Integer offset, Integer size, String... propertyNames);

    <M> Pager<M> pager(DFinder dFinder, Class<M> clazz, int pageNo, int pageSize, String... propertyNames);


    // -------------------------- Group By --------------------------
    <M> Record record(DFinder dFinder, Class<M> clazz);

    <M> List<Record> recordList(DFinder dFinder, Class<M> clazz);

    <M> List<Record> recordList(DFinder dFinder, Class<M> clazz, Integer offset, Integer size);

    <M> Record recordFirst(DFinder dFinder, Class<M> clazz);
}
