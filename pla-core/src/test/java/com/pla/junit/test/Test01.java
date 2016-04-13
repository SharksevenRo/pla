package com.pla.junit.test;

import com.pla.finder.NativeFinder;
import com.pla.finder.RecordFinder;
import com.pla.junit.resource.model.Dic;
import com.pla.junit.resource.model.User;
import com.pla.query.*;
import org.junit.Test;

import java.util.List;

public class Test01 extends BaseHibernateConfiguration {
    /**
     * 普通条件查询
     */
    @Test
    public void test01() {
        List<Dic> dicList = Dic.finder(Dic.class).eq("dicKey", "A1").startLike("dicContent", "TTT")
                .desc("id").list() /*.count() .uniqueResult() .first()*/;
        for (Dic dic : dicList) {
            System.out.println(dic.getId() + " " + dic.getDicKey() + " " + dic.getDicValue() + " " + dic.getDicContent());
        }

        Dic dic = new Dic();
        dic.setDicKey("A1");
        dic.setDicContent("TTT");
        dicList = dic.finder().eq("dicKey").like("dicContent").desc("id").list();
        for (Dic dic2 : dicList) {
            System.out.println(dic2.getId() + " " + dic2.getDicKey() + " " + dic2.getDicValue() + " " + dic2.getDicContent());
        }
    }

    /**
     * 或条件查询
     */
    @Test
    public void test02() {
        List<Dic> dicList = Dic.finder(Dic.class).eq("dicKey", "A1").or(Or.create().eq("dicValue", "1")
                .eq("dicValue", "2")).list() /*.count() .uniqueResult() .first()*/;
        for (Dic dic : dicList) {
            System.out.println(dic.getId() + " " + dic.getDicKey() + " " + dic.getDicValue() + " " + dic.getDicContent());
        }

        Dic dic = new Dic();
        dic.setDicKey("A1");
        dic.setDicValue("1");
        dic.setDicContent("TTT");
        dicList = dic.finder().eq("dicKey").or(dic.or().eq("dicValue").like("dicContent")).list();
        for (Dic dic2 : dicList) {
            System.out.println(dic2.getId() + " " + dic2.getDicKey() + " " + dic2.getDicValue() + " " + dic2.getDicContent());
        }
    }

    /**
     * 关联查询
     */
    @Test
    public void test03() {
        List<User> userList = User.finder(User.class).join("role").list(); /*.rightJoin() .innerJoin() */
        for (User user : userList) {
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getRole().getRoleName());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void test04() {
        Pager<Dic> dicPager = Dic.finder(Dic.class).eq("dicKey", "A1").or(Or.create()
                .eq("dicValue", "2").eq("dicValue", "3")).pager(1, 20);
        for (Dic dic : dicPager.getList()) {
            System.out.println(dic.getId() + " " + dic.getDicKey() + " " + dic.getDicValue() + " " + dic.getDicContent());
        }
        System.out.println("count:" + dicPager.getTotalCount() + " page count:" + dicPager.getTotalPage());
    }

    /**
     * 原生SQL查询
     */
    @Test
    public void test05() {
        List<Record> recordList = RecordFinder.create("sessionFactoryRes").query("select * from t_dic t where t.dic_key=?", "A1");
        for (Record record : recordList) {
            System.out.println(record.getLong("id") + " " + record.get("dicKey") + " " + record.get("dicValue") + " "
                    + record.get("dicContent") + " " + record.getStr("test1"));
        }

        //结果集映射为POJO对象
        List<Dic> dic2List = NativeFinder.create(Dic.class).query("select * from t_dic t where t.dic_key=?", "A1");
        for (Dic dic2 : dic2List) {
            System.out.println(dic2.getId() + " " + dic2.getDicKey() + " " + dic2.getDicValue() + " " + dic2.getDicContent()
                    + " " + dic2.getTest1());
        }
    }

    /**
     * 简单分组统计(非原生SQL)
     */
    @Test
    public void test06() {
        List<Record> recordList = Dic.finder(Dic.class).eq("dicKey", "A1").groupBy("dicKey").count("dicValue", "cntVal").sum("sort", "sumSort").recordList();
        for (Record record : recordList) {
            System.out.println(record.get("dicKey") + " " + record.get("cntVal") + " " + record.get("sumSort"));
        }
    }

    /**
     * 普通单一事务增删改
     */
    @Test
    public void test07() {
        Dic dic = new Dic();
        dic.setDicKey("A1");
        dic.setDicValue("2");
        dic.setDicContent("T2");
        dic.setSort(2);
        dic.setKeyDesc("TEST A1");
        //时间参数重写init()方法里初始化
        dic.save();
        System.out.println(dic.getId() + " " + dic.getCreationDate());

        Dic dic2 = Dic.finder(Dic.class).load(dic.getId());
        dic2.setDicValue("3");
        dic2.update("dicValue");

        dic2.delete();
    }

    /**
     * 执行原生SQL
     */
    @Test
    public void test08() {
        SQLExecutor.create("sessionFactoryRes").execute("update t_dic set sort=? where id=? ", 1, 1l);
    }


    /**
     * 指定字段查询
     */
    @Test
    public void test10() {
        User user = User.finder(User.class).join("role").first("id", "userName", "role.roleName");
        System.out.println(user.getId() + " " + user.getUserName() + " " + user.getRole().getRoleName());
    }

    /**
     * on条件查询（由于Hibernate的限制只允许一个on条件）
     */
    @Test
    public void test11() {
        List<User> users = User.finder(User.class).join("role").on(On.eq("roleName", "aaa"))
                .list("id", "userName", "role.roleName");
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getUserName() + " " +
                    (user.getRole() != null ? user.getRole().getRoleName() : ""));
        }
    }

    /**
     * 原生SQL条件
     */
    @Test
    public void test12() {
        User.finder(User.class).join("role").sqlRestriction("{this}.ID=? and length({role}.ROLE_NAME)=?", 1l, 2).list();

        User.finder(User.class).eq("userName", "abc").join("role", "r1").sqlRestriction("{this}.ID=? and length({r1}.ROLE_NAME)=?", 1l, 2).list();
    }
}
