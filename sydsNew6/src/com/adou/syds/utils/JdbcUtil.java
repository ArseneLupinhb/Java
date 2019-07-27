package com.adou.syds.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

	//必须c3p0的默认的配置文件
	private static DataSource dataSource = new ComboPooledDataSource();
	private static Connection con = null;
	
	public static Connection getConnection() throws SQLException {
		if(con == null) {
			return dataSource.getConnection();
		}
		return con;
	}

	public static DataSource getDataSource(){
		return dataSource;
	}
}
