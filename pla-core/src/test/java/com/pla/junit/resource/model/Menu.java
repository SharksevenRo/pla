package com.pla.junit.resource.model;

import com.pla.model.FactoryBeanId;
import com.pla.model.Model;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "T_MENU")
@FactoryBeanId("sessionFactory")
public class Menu extends Model<Menu> implements Serializable {
	private static final long serialVersionUID = -6847282786996519415L;
	private Long id;
    private String menuName;
    private String menuUri;
    private Integer level;
    private Date creationDate;

    private Set<Role> roleList;

    @Override
    protected void init(){
        creationDate = new Date();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @NotEmpty
    public String getMenuUri() {
        return menuUri;
    }

    public void setMenuUri(String menuUri) {
        this.menuUri = menuUri;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "menuList")
    @BatchSize(size = 10)
    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }
}
