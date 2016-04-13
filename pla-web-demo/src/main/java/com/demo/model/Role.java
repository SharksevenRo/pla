package com.demo.model;


import com.pla.model.Model;

import javax.persistence.*;

/**
 * Created by chey on 2015/9/15.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ROLE")
public class Role extends Model<Role> {
    private Long id;
    private String name;
    private String alias;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ALIAS")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
