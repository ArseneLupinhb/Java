package com.altest.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
	@Test
	public void testUser() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		User user = (User) applicationContext.getBean("user");
		user.add();
	}

}
