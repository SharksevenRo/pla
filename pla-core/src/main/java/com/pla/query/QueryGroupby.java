package com.pla.query;

import java.util.List;

 public interface QueryGroupby<T> {
     QueryGroupby<T> groupBy(String propertyName) ;

     QueryGroupby<T> countAll();

     QueryGroupby<T> countAll(String alias);

     QueryGroupby<T> count(String propertyName);

     QueryGroupby<T> count(String propertyName, String alias);

     QueryGroupby<T> max(String propertyName);

     QueryGroupby<T> max(String propertyName, String alias);

     QueryGroupby<T> min(String propertyName);

     QueryGroupby<T> min(String propertyName, String alias);

     QueryGroupby<T> sum(String propertyName) ;

     QueryGroupby<T> sum(String propertyName, String alias) ;

     QueryGroupby<T> avg(String propertyName) ;

     QueryGroupby<T> avg(String propertyName, String alias) ;

     QueryGroupby<T> distinct(String... properties);

     QueryGroupby<T> distinct() ;

     Record record() ;

     List<Record> recordList() ;

     List<Record> recordList(Integer offset, Integer size);

     Record recordFirst() ;
}
