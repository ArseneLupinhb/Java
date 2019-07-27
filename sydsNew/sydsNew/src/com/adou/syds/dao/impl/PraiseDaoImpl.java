package com.adou.syds.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.adou.syds.dao.PraiseDao;
import com.adou.syds.domain.Praise;
import com.adou.syds.utils.JdbcUtil;


public class PraiseDaoImpl implements PraiseDao {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	@Override
	public boolean priase(Praise praise) throws SQLException {
		String sql = "INSERT INTO syds_praise(ipAddress, image_id, praise_time, userName) VALUES(?, ?, ?, ? )";
		Object[] params = {praise.getIpAddress(), praise.getImage_id(), praise.getPraise_time(), praise.getUserName()};
		if (qr.update(sql,params) == 1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean cancelPriase(Praise praise) throws SQLException {
			String sql = "DELETE FROM syds_praise WHERE image_id = ? AND ipAddress = ? AND praise_time =? AND username = ?";
			Object[] params = {praise.getImage_id(), praise.getIpAddress(), praise.getPraise_time(), praise.getUserName()};
			if (qr.update(sql,params) == 1) {
				return true;
			}
			else {
				return false;
			}
	}

}
