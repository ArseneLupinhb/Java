package com.adou.syds.service;

import java.sql.SQLException;

import com.adou.syds.domain.User;

public abstract interface UserService {

	public boolean login(String userName, String password) throws Exception;

	public User showUser(User user) throws SQLException;

	public boolean alertUser(User user) throws SQLException;

	public boolean findUserByName(String name) throws SQLException;

	public void addUser(User user) throws SQLException;

}
