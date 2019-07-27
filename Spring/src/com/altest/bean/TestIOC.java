package com.altest.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
	@Test
	public void testUser() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
		bean2 user = (bean2) applicationContext.getBean("bean2");
		user.add();
	}

}
