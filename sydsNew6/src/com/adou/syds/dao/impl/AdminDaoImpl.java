package com.adou.syds.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.adou.syds.dao.AdminDao;
import com.adou.syds.domain.Admin;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;
import com.adou.syds.utils.JdbcUtil;

public class AdminDaoImpl implements AdminDao {

	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	@Override
	public boolean login(Admin admin) {
		String sql = "SELECT count(*) FROM syds_admin WHERE adminName = ? AND adminPwd = ?";
		Object[] params = {admin.getAdminName(),admin.getAdminPwd()};
		Number num = null;
		try {
			num = (Number)qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (num.intValue() == 1) {
			return true;
		}else {
			return false;
		}

	}
	@Override
	public List<Album> showAlbums() {
		String sql = "SELECT * FROM syds_album WHERE isPass = 0";
		List<Album> checkAlbums = null;
		try {
			checkAlbums = qr.query(sql, new BeanListHandler<Album>(Album.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (checkAlbums.size() > 0 ) {
			for (Album album : checkAlbums) {
				String sql1 = "SELECT phone FROM syds_user WHERE id = ?";
			    Object[] params  = {album.getUser_id()};
			    String phone = null;
			    try {
					phone =  (String) qr.query(sql1, new ScalarHandler(), params);
					album.setPhone(phone);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return checkAlbums;
	}
	@Override
	public boolean passAlbum(int id) {
		String sql = "UPDATE syds_album SET isPass = 1 WHERE id = ?";
		Object[] params = {id};
		int isPass = 0;
		try {
			isPass =  qr.update(sql,params);
		} catch (SQLException e) {
		}
		if (isPass == 1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Album> showAllAlbums() {
		String sql = "SELECT  albumName, phone, syds_album.`userName`, syds_album.`description`, syds_album.`id`,syds_album.`createTime` FROM syds_album LEFT JOIN syds_user ON syds_album.`user_id`= syds_user.`id`";
		List<Album> showAllAlbums = null;
		try {
			showAllAlbums = qr.query(sql, new BeanListHandler<Album>(Album.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showAllAlbums;
	}
	@Override
	public List<Image> showAllImages() {
		String sql = " SELECT  syds_image.`id`,syds_image.`image_url`, syds_user.`userName`,syds_user.`realName`,syds_user.`phone`,syds_album.`albumName` FROM syds_image ,syds_user, syds_album WHERE syds_image.`user_id`= syds_user.`id` AND syds_image.`album_id` = syds_album.`id`";
		System.err.println(sql);
		List<Image> showAllImages = null;
		try {
			showAllImages = qr.query(sql, new BeanListHandler<Image>(Image.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showAllImages;
	}
	@Override
	public List<Image> showImages() {
		String sql = " SELECT  syds_image.`id`,syds_image.`image_url`,syds_image.`album_id`, syds_user.`userName`,syds_user.`phone`,syds_album.`albumName` FROM syds_image ,syds_user, syds_album WHERE syds_image.`user_id`= syds_user.`id` AND syds_image.`album_id` = syds_album.`id` AND syds_image.`isPass` = 0";
		List<Image> showImages = null;
		try {
			showImages = qr.query(sql, new BeanListHandler<Image>(Image.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return showImages;
	}
	@Override
	public boolean passImage(int id) {
		String sql = "UPDATE syds_image SET isPass = 1 WHERE id = ?";
		Object[] params = {id};
		int isPass = 0;
		try {
			isPass =  qr.update(sql,params);
		} catch (SQLException e) {
		}
		if (isPass == 1) {
			return true;
		}else {
			return false;
		}
	}
}
