package com.pla.service;

import com.pla.dao.IBaseDAO;
import com.pla.dao.Criteria;
import com.pla.query.Pager;
import com.pla.query.Record;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseService<T> implements IBaseService<T> {
    private IBaseDAO<T> dao;

    public void setDao(IBaseDAO<T> dao) {
        this.dao = dao;
    }

    @Transactional
    public void save(T t) {
        dao.save(t);
    }

    @Transactional
    public void update(T t) {
        dao.update(t);
    }

    @Transactional
    public void update(T t, String... fields) {
        dao.update(t, fields);
    }

    @Transactional
    public void saveOrUpdate(T t) {
        dao.saveOrUpdate(t);
    }

    @Transactional
    public void delete(Serializable id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    public T load(Serializable id) {
        return dao.load(id);
    }

    @Transactional(readOnly = true)
    public List<T> list(Criteria criteria) {
        return dao.list(criteria);
    }

    @Transactional(readOnly = true)
    public List<T> list(Criteria criteria, int offset, int size) {
        return dao.list(criteria, offset, size);
    }

    @Transactional(readOnly = true)
    public T first(Criteria criteria) {
        return dao.first(criteria);
    }

    @Transactional(readOnly = true)
    public int count(Criteria criteria) {
        return dao.count(criteria);
    }

    @Transactional(readOnly = true)
    public T uniqueResult(Criteria criteria) {
        return dao.uniqueResult(criteria);
    }

    @Transactional(readOnly = true)
    public Pager<T> pager(Criteria criteria, int pageNo, int pageSize) {
        return dao.pager(criteria, pageNo, pageSize);
    }

    //-------------------------- Query for parts of properties--------------------------
    @Transactional(readOnly = true)
    public T uniqueResult(Criteria criteria, String... propertyNames) {
        return dao.uniqueResult(criteria, propertyNames);
    }

    @Transactional(readOnly = true)
    public T first(Criteria criteria, String... propertyNames) {
        return dao.first(criteria, propertyNames);
    }

    @Transactional(readOnly = true)
    public List<T> list(Criteria criteria, String... propertyNames) {
        return dao.list(criteria, propertyNames);
    }

    @Transactional(readOnly = true)
    public List<T> list(Criteria criteria, Integer offset, Integer size, String... propertyNames) {
        return dao.list(criteria, offset, size, propertyNames);
    }

    @Transactional(readOnly = true)
    public Pager<T> pager(Criteria criteria, int pageNo, int pageSize, String... propertyNames) {
        return dao.pager(criteria, pageNo, pageSize, propertyNames);
    }

    //-------------------------- Query for record --------------------------
    @Transactional(readOnly = true)
    public Record record(Criteria criteria) {
        return dao.record(criteria);
    }

    @Transactional(readOnly = true)
    public List<Record> recordList(Criteria criteria) {
        return dao.recordList(criteria);
    }

    @Transactional(readOnly = true)
    public List<Record> recordList(Criteria criteria, Integer offset, Integer size) {
        return dao.recordList(criteria,offset,size);
    }

    @Transactional(readOnly = true)
    public Record recordFirst(Criteria criteria) {
        return dao.recordFirst(criteria);
    }
}
