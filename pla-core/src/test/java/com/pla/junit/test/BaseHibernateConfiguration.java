package com.pla.junit.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
//@TransactionConfiguration(defaultRollback = false)
public class BaseHibernateConfiguration /*extends AbstractTransactionalJUnit4SpringContextTests*/ {

}
