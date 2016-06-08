package com.pla.service;

import com.pla.dao.Criteria;
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

    <M> M load(Criteria criteria, Class<M> clazz, Serializable id);


    <M> M first(Criteria criteria, Class<M> clazz);

    <M> List<M> list(Criteria criteria, Class<M> clazz);

    <M> List<M> list(Criteria criteria, Class<M> clazz, int offset, int size);

    <M> int count(Criteria criteria, Class<M> clazz);

    <M> M uniqueResult(Criteria criteria, Class<M> clazz);

    <M> Pager<M> pager(Criteria criteria, Class<M> clazz, int pageNo, int pageSize);

    // -------------------------- QueryOp4Parts --------------------------
    <M> M uniqueResult(Criteria criteria, Class<M> clazz, String... propertyNames);

    <M> M first(Criteria criteria, Class<M> clazz, String... propertyNames);

    <M> List<M> list(Criteria criteria, Class<M> clazz, String... propertyNames);

    <M> List<M> list(Criteria criteria, Class<M> clazz, Integer offset, Integer size, String... propertyNames);

    <M> Pager<M> pager(Criteria criteria, Class<M> clazz, int pageNo, int pageSize, String... propertyNames);


    // -------------------------- Group By --------------------------
    <M> Record record(Criteria criteria, Class<M> clazz);

    <M> List<Record> recordList(Criteria criteria, Class<M> clazz);

    <M> List<Record> recordList(Criteria criteria, Class<M> clazz, Integer offset, Integer size);

    <M> Record recordFirst(Criteria criteria, Class<M> clazz);
}
