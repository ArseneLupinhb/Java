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

public class FormAction implements Action ,ModelDriven<User>{
	
	User user = new User(); 
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public String execute() throws Exception {
		/*ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> map = actionContext.getParameters();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object[] objects =  (Object[]) map.get(key);
			System.err.println(Arrays.toString(objects));
		}*/
		
		/*HttpServletRequest req = ServletActionContext.getRequest();
		//req.setCharacterEncoding("utf-8");
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");*/
		
	    System.err.println(user);
		return null;
		
	}

	

}
