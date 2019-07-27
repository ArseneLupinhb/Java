package com.altest.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.altest.domain.Customer;
import com.altest.domain.LinkMan;
import com.altest.domain.User;
import com.altest.utils.HibernateUtils;

import java.util.*;;

public class Hibernate_oneToMany_test {
	@Test
	public void addTest (){
	    Session session = null;
	    Transaction transaction = null;
		
		try {
		    session = HibernateUtils.getSession();
		    transaction =  session.beginTransaction();
		    
		    Customer customer = new Customer();
		    customer.setCustName("腾讯");
		    customer.setCustLevel("VIP");
		    customer.setCustSource("网络");
		    customer.setCustPhone("15671558130");
		    customer.setCustMobile("15671558130");
		    
		    LinkMan linkMan = new LinkMan();
		    linkMan.setLkm_gender("男");
		    linkMan.setLkm_name("张三");
		    linkMan.setLkm_phone("15678155");

		    customer.getSetLinkMan().add(linkMan);
		    
		    session.save(customer);		    
		    
		    System.err.println(customer);
		    
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
	    
	    Customer customer = session.get(Customer.class, 1);
	    LinkMan linkMan = session.get(LinkMan.class, 2);
	    
	    customer.getSetLinkMan().add(linkMan);
	    linkMan.setCustomer(customer);
	    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void deleteTest(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    
	    Transaction transaction =  session.beginTransaction();
	    
	    Customer customer = session.get(Customer.class, 1);
	    
	    session.delete(customer);
	    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
