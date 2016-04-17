package com.pla.demo.model;

import com.pla.model.FactoryBeanId;
import com.pla.model.Model;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_DIC")
@FactoryBeanId("sessionFactoryRes")
public class Dic extends Model<Dic> implements Serializable {
    private static final long serialVersionUID = -7843097306817462952L;

    @Override
    protected void init() {
        creationDate = new Date();
    }

    private Long id; //数据字典主键
    private String dicKey; //数据键
    private String dicValue; //数据值
    private String dicContent; //数据内容
    private String keyDesc; //数据类型描述
    private int sort; //排序
    private Date creationDate; //创建时间

    private String test1;
    private byte[] test2;
    private Double test3;
    private Boolean test4;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    @NotEmpty
    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getDicContent() {
        return dicContent;
    }

    public void setDicContent(String dicContent) {
        this.dicContent = dicContent;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }


    @NotNull
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public byte[] getTest2() {
        return test2;
    }

    public void setTest2(byte[] test2) {
        this.test2 = test2;
    }

    public Double getTest3() {
        return test3;
    }

    public void setTest3(Double test3) {
        this.test3 = test3;
    }

    public Boolean getTest4() {
        return test4;
    }

    public void setTest4(Boolean test4) {
        this.test4 = test4;
    }


    @Override
    public String toString() {
        return "id:" + id + " dicKey:" + dicKey + " dicValue:" + dicValue + " dicContent:" + dicContent +
                " keyDesc:" + keyDesc + " sort:" + sort + " creationDate:" + creationDate;
    }
}
