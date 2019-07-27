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

	/**
	 * 退出
	 * @param req
	 * @param resp
	 */
	public void quit(HttpServletRequest req, HttpServletResponse resp){
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			try {
				resp.getWriter().write("noLogin");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		req.getSession().removeAttribute("user");
		user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			try {
				resp.getWriter().write("true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 */
public void login(HttpServletRequest req, HttpServletResponse resp){
		User user = new User();
		String userName = req.getParameter("userName1").trim();
		String password = MD5Utils.md5(req.getParameter("password1").trim());
		if (req.getSession().getAttribute("user") == null) {
			boolean isLogin = false;
			try {
				isLogin = userService.login(userName,password);
				if (!isLogin) {
					
					resp.setContentType("text/html");
					try {
						resp.getWriter().write("false");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}else {
					user = userService.findUser(userName);
					String unitName = userService.selectUnitName(user.getUnit_id());
					
					req.getSession().setAttribute("user", user);
					req.getSession().setAttribute("unitName", unitName);
					System.err.println(unitName);
					
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
		User user=new User();
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
		User user=new User();
		
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
				//int unit_id = Integer.parseInt(req.getParameter("academy").trim());
				String unit_id0 =  req.getParameter("academy");
				System.err.println(unit_id0);
				int unit_id = Integer.parseInt(unit_id0);
				if (shu.equals("学院")){
					user.setUnit_id(unit_id);
					//user.setMajor(req.getParameter("major").trim());
				}
				else {
					user.setUnit_id(unit_id+19);
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
	
}
