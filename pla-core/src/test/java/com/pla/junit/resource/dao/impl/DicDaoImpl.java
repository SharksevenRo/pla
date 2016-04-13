package com.pla.junit.resource.dao.impl;

import com.pla.dao.HibernateDAO;
import com.pla.junit.resource.dao.DicDao;
import com.pla.junit.resource.model.Dic;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DicDaoImpl extends HibernateDAO<Dic> implements DicDao {
    @Resource(name = "sessionFactoryRes")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
	public List<Dic> findList(Dic dic) {
        StringBuilder hql = new StringBuilder("from Dic dic where 1=1 ");
        Map<String, Object> values = new HashMap<String, Object>();
        if (dic.getId() != null) {
            hql.append("and dic.id=:id ");
            values.put("id", dic.getId());
        }
        if (dic.getDicKey() != null && !dic.getDicKey().equals("")) {
            hql.append("and dic.dicKey=:dicKey ");
            values.put("dicKey", dic.getDicKey());
        }

        Query query = getSession().createQuery(hql.toString());
        for (String key : values.keySet()) {
            query.setParameter(key, values.get(key));
        }
        return query.list();
    }
}
