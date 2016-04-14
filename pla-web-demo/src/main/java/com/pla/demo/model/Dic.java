package com.pla.demo.model;

import com.pla.model.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by shiro on 2015/7/8.
 */
@Entity
@Table(name = "DIC")
public class Dic extends Model<Dic> {
    private Long id; //数据字典主键
    private String dicKey; //数据键
    private String dicValue; //数据值
    private String dicContent; //数据内容
    private String keyDesc; //数据类型描述
    private Integer sort; //排序
    private Date creationDate; //创建时间

    @Override
    public void init() {
        sort = 0;
        creationDate = new Date();
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DIC_KEY")
    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    @Column(name = "DIC_VALUE")
    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    @Column(name = "DIC_CONTENT")
    public String getDicContent() {
        return dicContent;
    }

    public void setDicContent(String dicContent) {
        this.dicContent = dicContent;
    }

    @Column(name = "KEY_DESC")
    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    @NotNull
    @Column(name = "CREATION_DATE")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
