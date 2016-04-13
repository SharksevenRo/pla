package com.pla.query;


import java.util.List;

public interface QueryExcutor<T> extends QueryGroupby<T> {
    T uniqueResult(String... propertyNames);

    T first(String... propertyNames);

    List<T> list(String... propertyNames);

    List<T> list(Integer offset, Integer size, String... propertyNames);

    Pager<T> pager(int pageNo, int pageSize, String... propertyNames);
}
