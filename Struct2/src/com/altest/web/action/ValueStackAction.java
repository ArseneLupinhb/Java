package com.altest.web.action;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.altest.web.entity.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ValueStackAction implements Action {
	private String username;
	public String getUsername() {
		return username;
	}

	private User user = new User();
	public User getUser() {
		return user;
	}

	public String execute() throws Exception {

		ActionContext actionContext =  ActionContext.getContext();
		username = "张三";
		user.setUsername(username);
		user.setPassword("123");
		return "success";
		
	}

	

}
