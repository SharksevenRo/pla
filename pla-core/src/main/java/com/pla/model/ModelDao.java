package com.pla.model;

public interface ModelDao<T> {
    void save(T t);

    void update(T t);

    void update(T t, String... fields);

    void saveOrUpdate(T t);

    void delete(T t);
}