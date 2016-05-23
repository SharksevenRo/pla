package com.pla.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Criteria implements Serializable {
    private static final long serialVersionUID = -1959436287549702712L;

    public static CriteriaClazz create(Class clazz) {
        return new CriteriaClazz(clazz);
    }

    public static CriteriaModel create(Object model) {
        return new CriteriaModel(model);
    }

    protected DetachedCriteria detachedCriteria;

    private List<String> aliasList;
    private ProjectionList projectionList;
    protected List<String> ascList;
    protected List<String> descList;

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public List<String> getAliasList() {
        if (aliasList == null)
            aliasList = new ArrayList<String>();
        return aliasList;
    }

    public ProjectionList getProjectionList() {
        if (projectionList == null)
            projectionList = Projections.projectionList();
        return projectionList;
    }

    abstract void generateOrderBy();
}


