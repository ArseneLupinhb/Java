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
import com.altest.domain.Role;
import com.altest.domain.User;
import com.altest.utils.HibernateUtils;

import sun.print.resources.serviceui;

import java.util.*;;

public class HibernateHQlSelect {
	@Test
	public  void queryTest() {

		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
		    List<Customer> list =  query.list();
		    
		    for (Customer customer:list ) {
				System.err.println(customer);
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
	public void selectAll(){
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("from Customer");
		    query.setFirstResult(0);
		    query.setMaxResults(1);
		    
		    List<Customer> list =  query.list();
		    for (Customer customer:list ) {
				System.err.println(customer);
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
	public void selectSome(){
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("select count(*) from Customer");
		    
		    Object object = query.uniqueResult();
		    long count1 = (long) object;
		    int countInt = (int) count1;
		    
		    System.err.println(countInt);
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		} 
	}
	
	@Test
	public void selectCondition(){
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("from Customer where custPhone =?");
		    query.setParameter(0, "15671558130");
		    
		    List<Customer> list =  query.list();
		    for (Customer customer:list ) {
				System.err.println(customer);
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
	public void selectLike(){
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Query query = session.createQuery("from Customer where custPhone like ?");
		    query.setParameter(0, "%71%");
		    
		    List<Customer> list =  query.list();
		    for (Customer customer:list ) {
				System.err.println(customer);
			}
		    
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		} 
	}
}