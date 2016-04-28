package com.pla.junit.test;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.pla.dao.Criteria;
import com.pla.finder.Finder;
import com.pla.junit.resource.model.Dic;
import com.pla.junit.resource.service.DicService;
import com.pla.query.HibernateQuery;
import com.pla.query.QueryByClass;
import com.pla.query.QueryByModel;

public class SampleDicTest extends BaseHibernateConfiguration {
    @Resource
    private DicService dicService;

    @Test
    public void test00() {
        Dic dic = new Dic();
        dic.setDicKey("A1");
        List<Dic> list = dicService.findList(dic);
        for(Dic d:list){
            System.out.println(d);
        }
    }

    @Test
    public void test01() {
        Criteria criteria = Criteria.create(Dic.class).eq("dicKey", "1").like("dicContent", "test").desc("id");
        List<Dic> list = dicService.list(criteria);
        System.out.println(list.size());

        Dic dic = new Dic();
        dic.setDicKey("1");
        dic.setDicContent("test");
        Criteria criteria2 = Criteria.create(dic).eq("dicKey").like("dicContent").desc("id");
        List<Dic> list2 = dicService.list(criteria2);
        System.out.println(list2.size());
    }

    @Resource(name = "sessionFactoryRes")
    private SessionFactory sessionFactory;

    @Test
    public void test02() {
        Session session = sessionFactory.openSession();

        QueryByClass<Dic> query = new HibernateQuery<Dic>(session).from(Dic.class);
        List<Dic> list = query.eq("dicKey", "1").like("dicContent", "test").desc("id").list();
        System.out.println(list.size());

        Dic dic = new Dic();
        dic.setDicKey("A1");
        dic.setDicContent("1");
        QueryByModel<Dic> query2 = new HibernateQuery<Dic>(session).from(dic);
        List<Dic> list2 = query2.eq("dicKey").like("dicContent").desc("id").list();
        System.out.println(list2.size());

        session.close();
    }

    @Test
    public void test03() {
        QueryByClass<Dic> query = new Finder<Dic>().from(Dic.class);
        List<Dic> list = query.eq("dicKey", "1").like("dicContent", "test").desc("id").list();
        System.out.println(list.size());

        Dic dic = new Dic();
        dic.setDicKey("1");
        dic.setDicContent("test");
        QueryByModel<Dic> query2 = new Finder<Dic>().from(dic);
        List<Dic> list2 = query2.eq("dicKey").like("dicContent").desc("id").list();
        System.out.println(list2.size());
    }


    @Test
    public void test04() {
        List<Dic> list = Dic.finder(Dic.class).eq("dicKey", "1").like("dicContent", "test").desc("id").list();
        System.out.println(list.size());

        Dic dic = new Dic();
        dic.setDicKey("1");
        dic.setDicContent("test");
        List<Dic> list2 = dic.finder().eq("dicKey").like("dicContent").desc("id").list();
        System.out.println(list2.size());
    }

}
