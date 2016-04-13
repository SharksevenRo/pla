package com.pla.service;

import com.pla.dao.support.Criteria;
import com.pla.query.Pager;
import com.pla.query.Record;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {
    void save(T t);

    void update(T t);

    void update(T t, String... fields);

    void saveOrUpdate(T t);

    void delete(Serializable id);

    T load(Serializable id);

    List<T> list(Criteria criteria);

    List<T> list(Criteria criteria, int offset, int size);

    T first(Criteria criteria);

    int count(Criteria criteria);

    T uniqueResult(Criteria criteria);

    Pager<T> pager(Criteria criteria, int pageNo, int pageSize);

    //-------------------------- Query for parts of properties--------------------------
    T uniqueResult(Criteria criteria, String... propertyNames);

    T first(Criteria criteria, String... propertyNames);

    List<T> list(Criteria criteria, String... propertyNames);

    List<T> list(Criteria criteria, Integer offset, Integer size, String... propertyNames);

    Pager<T> pager(Criteria criteria, int pageNo, int pageSize, String... propertyNames);

    //-------------------------- Query for record --------------------------
    Record record(Criteria criteria);

    List<Record> recordList(Criteria criteria);

    List<Record> recordList(Criteria criteria, Integer offset, Integer size);

    Record recordFirst(Criteria criteria);
}
