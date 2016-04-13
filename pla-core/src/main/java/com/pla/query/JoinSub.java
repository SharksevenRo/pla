package com.pla.query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

public class JoinSub {
    private String associationPath;
    private String alias;
    private JoinType joinType;
    private Criterion criterion;

    public JoinSub(String associationPath, String alias, JoinType joinType) {
        this.associationPath = associationPath;
        this.alias = alias;
        this.joinType = joinType;
    }

    protected void on(Criterion criterion) {
        this.criterion = criterion;
    }

    protected void generateJoin(Criteria criteria) {
        if (this.criterion == null)
            criteria.createAlias(associationPath, alias, joinType);
        else
            criteria.createAlias(associationPath, alias, joinType, this.criterion);
    }
}
