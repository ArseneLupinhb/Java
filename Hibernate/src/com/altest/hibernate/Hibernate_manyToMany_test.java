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

import java.util.*;;

public class Hibernate_manyToMany_test {
	@Test
	public void saveTest (){
	    Session session = null;
	    Transaction transaction = null;
		
		try {
		    session = HibernateUtils.getSession();
		    transaction =  session.beginTransaction();
		    
		    User user1 = new User();
		    user1.setUser_name("石教祥");
		    user1.setUser_password("123456");
		    User user2 = new User();
		    user2.setUser_name("欧阳东");
		    user2.setUser_password("0123456");
		    
		    
		    Role role1 = new Role();
		    role1.setRole_name("班长");
		    role1.setRole_memo("代理1");
		    Role role2 = new Role();
		    role2.setRole_name("寝室室长");
		    role2.setRole_memo("代理2");
		    Role role3 = new Role();
		    role3.setRole_name("寝室室长");
		    role3.setRole_memo("代理2");
		    
		    user1.getSetRole().add(role1);
		    user1.getSetRole().add(role2);		    
		    
		    user2.getSetRole().add(role2);
		    user2.getSetRole().add(role3);

		    session.save(user1);
		    session.save(user2);
		    
		    transaction.commit();
		    
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			/*session.close();*/
		} 
	}
	
	@Test
	public void deleteTest(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    
	    Transaction transaction =  session.beginTransaction();
	    
	    User user = session.get(User.class, 1);
	    
	    session.delete(user);
	    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void updateTest(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    
	    Transaction transaction =  session.beginTransaction();
	    
	    User user = session.get(User.class, 4);
	    Role role = session.get(Role.class, 6);
	    
	    user.getSetRole().remove(role); 
	    //user.getSetRole().add(role);
	    //role.getSetUser().add(user);
	    
	    
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public  void selectTest() {
		SessionFactory sessionFactory = null;
	    Session session = null;
	    Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    transaction =  session.beginTransaction();
		    
		    Customer customer = session.get(Customer.class, 2);
		    Set<LinkMan> linkMans = customer.getSetLinkMan();
		    
		    System.err.println(linkMans.size());
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
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
	
	
}
