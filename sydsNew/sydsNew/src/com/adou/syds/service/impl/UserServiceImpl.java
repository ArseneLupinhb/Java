package com.adou.syds.service.impl;

import java.sql.SQLException;

import com.adou.syds.dao.UserDao;
import com.adou.syds.dao.impl.UserDaoImpl;
import com.adou.syds.domain.User;
import com.adou.syds.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public boolean login(String userName, String password) throws Exception {
		
		return userDao.login(userName,password);
	}
	@Override
	public User showUser(User user) throws SQLException {
		return userDao.showUser(user);
	}
	@Override
	public boolean alertUser(User user) throws SQLException {
		
		return userDao.alertUser(user);
	}
	@Override
	public boolean findUserByName(String name) throws SQLException {
		
		return userDao.findUserByName(name);
	}
	@Override
	public void addUser(User user) throws SQLException{
		userDao.addUser(user);
		
	}

	
}
