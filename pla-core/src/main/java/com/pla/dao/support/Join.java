package com.pla.dao.support;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

public class Join {
    String associationPath;
    String alias;
    JoinType joinType;
    org.hibernate.criterion.Criterion criterion;

    public Join(String associationPath, String alias, JoinType joinType) {
        this.associationPath = associationPath;
        this.alias = alias;
        this.joinType = joinType;
    }

    protected void on(org.hibernate.criterion.Criterion criterion) {
        this.criterion = criterion;
    }

    public String getAssociationPath() {
        return associationPath;
    }

    public void setAssociationPath(String associationPath) {
        this.associationPath = associationPath;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public org.hibernate.criterion.Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }
}
