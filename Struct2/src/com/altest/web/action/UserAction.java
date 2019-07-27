package com.altest.web.action;

import com.opensymphony.xwork2.Action;

public class UserAction implements Action{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String add() {
		System.err.println("add()............");
		return NONE;
	}
	
	public String update() {
		System.err.println("update().........");
		return NONE;
	}

}
