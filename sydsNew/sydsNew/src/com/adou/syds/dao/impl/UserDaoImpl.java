package com.adou.syds.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.adou.syds.dao.UserDao;
import com.adou.syds.domain.User;
import com.adou.syds.utils.JdbcUtil;



public class UserDaoImpl implements UserDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	
	@Test
	public void queryUserObject() throws SQLException{
		String sql = "SELECT * FROM syds_user WHERE username = 'test'";
		User user = qr.query(sql, new BeanHandler<User>(User.class));
		System.err.println(user);
	}
	
	@Test
	public void queryUserList() throws SQLException{
		String sql = "SELECT * FROM syds_user";
		List<User> userList = qr.query(sql, new BeanListHandler<User>(User.class) );
		System.err.println(userList);
	}
	
	@Test
	public void queryUserMap() throws SQLException{
		String sql = "SELECT count(*) FROM syds_user WHERE userName = 'hello' AND PASSWORD = '235'";
	
		Number num = (Number)qr.query(sql, new ScalarHandler());
		if (num.intValue() == 1) {
			System.out.println("true;"); 
		}else {
			System.out.println("false;"); 
		}	
	}
	
	@Test
	public void queryUserMaps() throws SQLException{
		String sql = "SELECT * FROM syds_user";
		List<Map<String, Object>> userMapList = qr.query(sql, new MapListHandler() );
		System.err.println(userMapList);
	}
	
	@Test
	public void queryUserCount() throws SQLException{
		String sql = "select count(*) from syds_user";
		Number num = (Number)qr.query(sql, new ScalarHandler());
		System.err.println(num);
	}

	

	@Override
	public User showUser(User user) throws SQLException {
		String sql = "SELECT * FROM syds_user WHERE username = ? AND PASSWORD = ?";
		Object[] params = {user.getUserName(),user.getPassword()};
		return qr.query(sql, new BeanHandler<User>(User.class),params);
	}

	@Override
	public boolean alertUser(User user) throws SQLException {
		String sql = "UPDATE syds_user SET userName = ? ,PASSWORD = ?, unit_id = ? ,major = ? ,realName = ?, phone = ? WHERE id = ? ";
		Object[] params = {user.getUserName(), user.getPassword(), user.getUnit_id(), user.getMajor(), user.getRealName(), user.getPhone(), user.getId()};
		if (qr.update(sql,params) != 1) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 登录的方法
	 */
	@Override
	public boolean login(String userName, String password) throws Exception {
		String sql = "SELECT count(*) FROM syds_user WHERE userName = ? AND PASSWORD = ?";
		Object[] params = {userName,password};
		Number num = (Number)qr.query(sql, new ScalarHandler(),params);
		if (num.intValue() == 1) {
			return true;
		}else {
			return false;
		}

	}
	/**
	 * 注册时检查用户名是否存在
	 */
	@Override
	public boolean findUserByName(String name) throws SQLException {
		
		String sql = "SELECT count(*) FROM syds_user WHERE userName = ? ";
		Object[] params = {name};
		Number num = (Number)qr.query(sql, new ScalarHandler(),params);
		if (num.intValue() == 0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 添加用户
	 */
	@Override
	public void addUser(User user) throws SQLException {
		String sql = "insert into syds_user (userName,realName, password, phone, unit_id, major) values(?,?,?,?,?,?)";
		qr.update(sql,user.getUserName(),user.getRealName(),user.getPassword(),user.getPhone(),user.getUnit_id(), "");
		
		
	}
}
	
