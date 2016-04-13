package com.pla.dao.support;

/**
 * Created by machey on 2016/3/29.
 */
public class OrderBy {
    String propertyName;
    Order order;

    public OrderBy(String propertyName, Order order) {
        this.propertyName = propertyName;
        this.order = order;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
