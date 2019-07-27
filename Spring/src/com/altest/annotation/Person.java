package com.altest.annotation;

import org.springframework.stereotype.Component;

@Component(value = "user")
public class Person {

	private String username;

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void add() {
		System.out.println("add.........");
		
	}
	
}
