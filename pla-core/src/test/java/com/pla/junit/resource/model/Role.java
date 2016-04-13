package com.pla.junit.resource.model;

import com.pla.model.FactoryBeanId;
import com.pla.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "T_ROLE")
@FactoryBeanId("sessionFactory")
public class Role extends Model<Role> implements Serializable {
	private static final long serialVersionUID = 3834031244408575341L;
	private Long id;
    private String roleName;
    private Date creationDate;

    private Set<Menu> menuList;

    @Override
    protected void init() {
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_ROLE_MENU", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    public Set<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(Set<Menu> menuList) {
        this.menuList = menuList;
    }
}
