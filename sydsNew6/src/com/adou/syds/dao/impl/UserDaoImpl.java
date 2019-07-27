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

import com.adou.syds.dao.UserDao;
import com.adou.syds.domain.User;
import com.adou.syds.utils.JdbcUtil;



public class UserDaoImpl implements UserDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	

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
		qr.update(sql,user.getUserName(),user.getRealName(),user.getPassword(),user.getPhone(),user.getUnit_id(),"");
		
		
	}

	@Override
	public String selectUnitName(int unit_id) throws SQLException {
		String unitName;
		String sql = "select unitName from syds_unit where id = ?";
		Object[] params = {unit_id};
		unitName = (String) qr.query(sql,new ScalarHandler(),params);
		return unitName;
	}
	
	public User findUser(String userName) {
		String sql = "select * from syds_user where userName = ?";
		try{
			
			return qr.query(sql, new BeanHandler<User>(User.class),userName);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
	
