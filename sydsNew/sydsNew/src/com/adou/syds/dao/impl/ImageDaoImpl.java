package com.adou.syds.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.adou.syds.dao.ImageDao;
import com.adou.syds.domain.Image;
import com.adou.syds.utils.JdbcUtil;

public class ImageDaoImpl implements ImageDao {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	@Override
	public boolean addImage(Image image)
			throws SQLException {
		String sql = "INSERT INTO syds_image (title, user_id, place, image_url, image_backName, introduction, publish_time, TYPE, album_id)VALUES(?, ?, ?,?, ?, ?, ?, ?, ?)";
		Object[] params = {image.getTitle(), 
				           image.getUser_id(), 
				           image.getPlace(), 
				           image.getImage_url(), 
				           image.getImage_backName(), 
				           image.getIntroduction(), 
				           image.getPublish_time(), 
				           image.getType(), 
				           image.getAlbum_id()};
		 
		if (qr.update(sql,params) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteImage(Image image)
			throws SQLException {
		String sql = "DELETE  FROM syds_image WHERE image_url = ?";
		Object[] params = {image.getImage_url()};
		
		if (qr.update(sql,params) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Image queryImage(Image image)
			throws SQLException {
		String sql = "SELECT * FROM syds_image WHERE image_url = ?";
		Object[] params = {image.getImage_url()};
		return qr.query(sql, new BeanHandler<Image>(Image.class),params);
	}

	@Override
	public List<Image> queryImages(Image image) throws SQLException {
		String sql = "SELECT * FROM syds_image WHERE user_id = ? AND album_id = ?";
		Object[] params = {image.getUser_id(), image.getAlbum_id()};
		return qr.query(sql, new BeanListHandler<Image>(Image.class),params);
	}

	/**
	 * SELECT * FROM syds_image 
       WHERE ( user_id IN
          (SELECT user_id FROM syds_user WHERE userName = '123' OR realName = '李四' OR major = '信管' OR unit_id IN 
              (SELECT unit_id FROM syds_unit WHERE unitName = '管理学院')
           ) 
       ) OR 
       ( album_id IN
          (SELECT album_id FROM syds_album WHERE albumName = 'dsadsa' OR description = 'sadsad')
       ) OR 
       (title = '123') OR 
       (introduction = '456')
	 */
	@Override
	public List<Image> searchImages(String searchsString) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM syds_image ");
		sql.append(" WHERE ( user_id IN");
		sql.append("           (SELECT id FROM syds_user WHERE userName like "+"'%"+searchsString+"%'" +" OR realName like  "+"'%"+searchsString+"%'" +"  OR major like "+"'%"+searchsString+"%'" +" OR unit_id IN ");
		sql.append("              (SELECT id FROM syds_unit WHERE unitName like  "+"'%"+searchsString+"%'" +"  )");
		sql.append("           ) ");
		sql.append("       ) OR ");
		sql.append("       ( album_id IN");
		sql.append("           ( SELECT id FROM syds_album WHERE albumName like  "+"'%"+searchsString+"%'" +"  OR description like  "+"'%"+searchsString+"%'" +"  )");
		sql.append("       ) OR ");
		sql.append("       (title like  "+"'%"+searchsString+"%'" +"  ) OR ");
		sql.append("       (introduction like  "+"'%"+searchsString+"%'" +"  )");
//		Object[] params = {searchsString, searchsString, searchsString,searchsString,searchsString,searchsString,searchsString,searchsString};
		System.err.println(sql);
		return qr.query(sql.toString(), new BeanListHandler<Image>(Image.class));
	}

}
