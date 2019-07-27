package com.adou.syds.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adou.syds.domain.User;
import com.adou.syds.service.UserService;
import com.adou.syds.service.impl.UserServiceImpl;
import com.adou.syds.utils.CommonUtils;
import com.adou.syds.utils.MD5Utils;

public class UserServlet extends BaseServlet {

	private UserService  userService = new UserServiceImpl();
	public User user = new User();
	
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp){
		
		String userName = req.getParameter("userName").trim();
		String password = MD5Utils.md5(req.getParameter("password").trim());
		if (req.getSession().getAttribute("user") == null) {
			boolean isLogin = false;
			try {
				isLogin = userService.login(userName,password);
				System.out.println("");
				if (!isLogin) {
					
					System.err.println("登录失败");
					resp.setContentType("text/html");
					try {
						resp.getWriter().write("false");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}else {
					System.err.println("登录成功");
					user.setUserName(userName);
					user.setPassword(password);
					req.getSession().setAttribute("user", user);
					resp.setContentType("text/html");
					try {
						resp.getWriter().write("true");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
			} catch (Exception e) {
			}
		}else {
			System.err.println("请先退出你的账号");
			resp.setContentType("text/html");
			try {
				resp.getWriter().write("logining");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		//return null;
	}
	
	/**
	 * 检查用户名是否已经注册
	 * @param req
	 * @param resp
	 */
	public void checkUserName(HttpServletRequest req,HttpServletResponse resp) {
		boolean isRegister = false;
		user.setUserName(req.getParameter("userName").trim());
		try {
			isRegister = userService.findUserByName(user.getUserName());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!isRegister){
			resp.setContentType("text/html");
			try {
				resp.getWriter().write("true");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 */
	public void register(HttpServletRequest req,HttpServletResponse resp){
		
		
		try {
				user.setUserName(req.getParameter("userName").trim());
				String password = MD5Utils.md5(req.getParameter("password"));
				user.setPassword(password);
				user.setPhone(req.getParameter("cellnumber").trim());
				String realName = req.getParameter("realName").trim();
				if(realName == null || realName.length() == 0){
					realName = "";
				}
				user.setRealName(realName);
				String shu = req.getParameter("department");
				int unit_id = Integer.parseInt(req.getParameter("academy").trim());
				if (shu.equals("学院")){
					user.setUnit_id(unit_id+1);
					//user.setMajor(req.getParameter("major").trim());
				}
				else {
					user.setUnit_id(unit_id+20);
					//user.setMajor("");
				}
				userService.addUser(user);
				resp.setContentType("text/html");
				try {
					resp.getWriter().write("true");
				} catch (IOException e) {
					
					e.printStackTrace();
				}

		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
	}
	
    public String showUser(HttpServletRequest req, HttpServletResponse resp){
    	try {
    		System.err.println(user);
			user =  userService.showUser(user);
			System.err.println(user.getId());
			req.getSession().setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
 
    
    public String alertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		User user = CommonUtils.toBean(req.getParameterMap(), User.class);
		user.setId(this.user.getId());
		System.err.println(user.getId());
		boolean isAlert =  userService.alertUser(user);
		System.err.println(isAlert);
		showUser(req, resp);
		return "f:/user.jsp";
	}
    
    public String quit(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().removeAttribute("user");
		System.err.println("退出成功");
		return "f:/login.jsp";
	}
}
