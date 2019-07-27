package com.altest.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.altest.domain.User;
import com.altest.utils.HibernateUtils;

import java.util.*;;

public class Hibernate_test {
	@Test
	public void addTest (){
		/*
		 * 1.加载配置文件
		 * 2.创建SessionFactory对象
		 * 3.创建session
		 * 4.开启事务
		 * 5.做cudr操作
		 * 6.提交事务
		 * 7.关闭资源
		 */
	   /* Configuration configuration = new Configuration();
	    configuration.configure();
	     
	    SessionFactory sessionFactory = configuration.buildSessionFactory();*/
	    Session session = null;
	    Transaction transaction = null;
		
		try {
		    session = HibernateUtils.getSession();
		    transaction =  session.beginTransaction();
		    
		    /*User user = new User();
		    user.setUsername("张三");
		    user.setAddress("wuhan");
		    user.setPassword("123");
		    session.save(user);*/
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			/*session.close();*/
		} 
	}
	
	@Test
	public  void queryTest() {

		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("from User");
		    List<User> list =  query.list();
		    for (User user:list ) {
				System.err.println(user);
			}
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		} 
	}
	@Test
	public  void criteriaTest() {
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Criteria criteria =  session.createCriteria(User.class);
		    List<User> list = criteria.list();
		    for (User user:list ) {
				System.err.println(user);
			}
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		} 
	}
	@Test
	public  void SQLQueryTest() {
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
            sqlQuery.addEntity(User.class);
            List<User> list =  sqlQuery.list();
            for (User user : list) {
				System.err.println(user);
			}
		   /* List<Object[]> objects = sqlQuery.list();
		    for (Object[] object:objects ) {
				System.err.println(Arrays.toString(object));
			}*/
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		} 
	}
	@Test

	public void updateTest(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    
	    Transaction transaction =  session.beginTransaction();
	    
	    User user = session.get(User.class, 1);
	    //user.setUsername("hello");
	    
	    session.update(user);
	    System.err.println(user);    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void deleteTest(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    
	    Transaction transaction =  session.beginTransaction();
	    
	    User user = session.get(User.class, 3);
	    
	    session.delete(user);
	    System.err.println(user);    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
