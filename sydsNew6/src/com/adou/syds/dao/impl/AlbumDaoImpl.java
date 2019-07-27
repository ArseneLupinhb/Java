package com.adou.syds.dao.impl;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.adou.syds.dao.AlbumDao;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;
import com.adou.syds.utils.DeleteFile;
import com.adou.syds.utils.JdbcUtil;

public class AlbumDaoImpl implements AlbumDao, Serializable {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public boolean addAlbum(int user_id, String userName, Album album)
			throws SQLException {
		String sql = "INSERT INTO syds_album(albumName,description,user_id,createTime,userName) VALUES(?, ?, ?,?, ?)";
		Object[] params = { album.getAlbumName(), album.getDescription(),
				user_id, new Timestamp(new Date().getTime()), userName };
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteAlbum(int album_id,String realPath) throws SQLException {
		String sql = "DELETE FROM syds_album WHERE id = ?";
		Object[] params = { album_id };
		if (qr.update(sql, params) == 1) {
			String sql20 = "SELECT image_url FROM syds_image WHERE album_id =?";
			String sql2 = "DELETE FROM syds_image WHERE album_id = ?";
			Object[] params2 = { album_id };
		    List<Map<String, Object>> maps = qr.query(sql20, new MapListHandler(),params);
		    System.err.println(maps);
		    String filePath = null;
		    if (maps.size() > 0) {
		    	for (Map<String, Object> map : maps) {
		    		filePath = realPath+  map.get("image_url");
					try {
						DeleteFile.deleteFile(filePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		    
			if (qr.update(sql2, params2) == 1) {
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 1.查询相册 2.遍历相册查询图片
	 */
	@Override
	public List<Album> queryAlbum(int user_id, Album album) throws SQLException {
		String sql = "SELECT * FROM syds_album WHERE user_id = ?";
		Object[] params = { user_id };
		List<Album> albums = qr.query(sql, new BeanListHandler<Album>(
				Album.class), params);

		for (Album album_image : albums) {
			String sql_image = "SELECT * FROM syds_image WHERE album_id = ?";
			Object[] params_image = { album_image.getId() };
			List<Image> images = qr.query(sql_image,
					new BeanListHandler<Image>(Image.class), params_image);
			if (images.size() != 0) {
				album_image.setAlbumImage_url(images.get(0).getImage_url());
			}
		}
		return albums;
	}

	@Override
	public boolean alertAlbum(Album album) throws SQLException {
		String sql = "UPDATE syds_album SET albumName = ?, description = ? , note = ?  WHERE id = ?";
		Object[] params = { album.getAlbumName(), album.getDescription(),
				album.getNote(), album.getId() };
		System.err.println(sql);
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int countAlbum(int user_id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM syds_album WHERE user_id = ?";
		Object[] params = { user_id };
		Number count = (Number) qr.query(sql, new ScalarHandler(), params);
		return count.intValue();
	}

	@Override
	public int countImageByAlbum(int user_id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM syds_image WHERE user_id = ?";
		Object[] params = { user_id };
		Number count = (Number) qr.query(sql, new ScalarHandler(), params);
		return count.intValue();
	}

	@Override
	public boolean praiseAlbum(int album_id, boolean isPraise)
			throws SQLException {
		String sql;
		if (isPraise == true) {
			sql = "UPDATE syds_album SET countPraise = countPraise + 1 WHERE id = ?";
		} else {
			sql = "UPDATE syds_album SET countPraise = countPraise - 1 WHERE id = ?";
		}
		Object[] params = { album_id };
		System.err.println(sql);
		if (qr.update(sql, params) == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int countPraiseByAlbum(int id) throws SQLException {
		String sql = "SELECT countPraise FROM syds_album WHERE id = ?";
		Object[] params = { id };
		Map map = qr.query(sql, new MapHandler(), params);
		int count = (Integer) map.get("countPraise");
		return count;
	}

	@Override
	public String setAlbumImage_url(int album_id) throws SQLException {
		String sql = "SELECT * FROM syds_image  WHERE album_id = ?";
		Object[] params = { album_id };
		List<Image> images = qr.query(sql, new BeanListHandler<Image>(
				Image.class), params);
		String albumImage_url = null;
		if (images.size() > 0) {
			albumImage_url = images.get(0).getImage_url();
		} else {
			albumImage_url = "";
		}
		String sql1 = "UPDATE syds_album SET albumImage_url = '"
				+ albumImage_url + "' WHERE id = '" + album_id + "'";
		qr.update(sql1);
		return albumImage_url;

	}

	@Override
	public int countAlbum(String searchStr) throws SQLException {
		StringBuffer conditionWhere = new StringBuffer("");
		if (searchStr != "") {
			conditionWhere.append(" and albumName LIKE '%" + searchStr + "%' ");
			conditionWhere.append("  AND albumImage_url != '' ");
			conditionWhere.append("  OR description LIKE '%" + searchStr
					+ "%' ");
			conditionWhere.append("  AND albumImage_url != '' ");
			conditionWhere.append("  OR user_id IN");
			conditionWhere.append("  (SELECT ");
			conditionWhere.append("    id ");
			conditionWhere.append("  FROM");
			conditionWhere.append("    syds_user ");
			conditionWhere.append("WHERE userName LIKE '%" + searchStr + "%' ");
			conditionWhere
					.append("    OR realName LIKE '%" + searchStr + "%' ");
			conditionWhere.append("    OR major LIKE '%" + searchStr + "%' ");
			conditionWhere.append("    OR unit_id IN");
			conditionWhere.append("   (SELECT ");
			conditionWhere.append("      id ");
			conditionWhere.append("    FROM");
			conditionWhere.append("      syds_unit");
			conditionWhere.append("    WHERE unitName LIKE '%" + searchStr
					+ "%'))");
			conditionWhere.append("    AND albumImage_url != ''");
		}
		String sql = "SELECT COUNT(*) FROM syds_album WHERE albumImage_url !=''"
				+ conditionWhere.toString();
		Number count = null;
		try {
			count = (Number) qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count.intValue();
	}

	public List<Album> listByPage(int currentPage, int pageSize,
			String condition) throws SQLException {
		List<Album> albums = new ArrayList<Album>();
		int nowSet = (currentPage - 1) * pageSize;
		StringBuilder sql = new StringBuilder();
		if (condition != "") {
			// 此处需进行condition拼接
			sql.append("select * from syds_album WHERE albumImage_url !='' and");
			sql.append(" albumName LIKE '%" + condition + "%' ");
			sql.append("  AND albumImage_url != '' ");
			sql.append("  OR description LIKE '%" + condition + "%' ");
			sql.append("  AND albumImage_url != '' ");
			sql.append("  OR user_id IN");
			sql.append("  (SELECT ");
			sql.append("    id ");
			sql.append("  FROM");
			sql.append("    syds_user ");
			sql.append("WHERE userName LIKE '%" + condition + "%' ");
			sql.append("    OR realName LIKE '%" + condition + "%' ");
			sql.append("    OR major LIKE '%" + condition + "%' ");
			sql.append("    OR unit_id IN");
			sql.append("   (SELECT ");
			sql.append("      id ");
			sql.append("    FROM");
			sql.append("      syds_unit");
			sql.append("    WHERE unitName LIKE '%" + condition + "%'))");
			sql.append("    AND albumImage_url != ''");
			sql.append(" limit ?,?");
		}

		else{
			sql.append( "select * from syds_album WHERE albumImage_url !='' limit ?,?");
			
		}
		try {
			albums = (ArrayList<Album>) qr.query(sql.toString(),new BeanListHandler<Album>(Album.class), nowSet, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.err.println(sql.toString());
		return albums;
	}

	@Override
	public List<Album> searchAlbum(String searchStr) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *");
		sql.append("FROM");
		sql.append("  syds_album ");
		sql.append("WHERE albumName LIKE '%" + searchStr + "%' ");
		sql.append("  AND albumImage_url != '' ");
		sql.append("  OR description LIKE '%" + searchStr + "%' ");
		sql.append("  AND albumImage_url != '' ");
		sql.append("  OR user_id IN");
		sql.append("  (SELECT ");
		sql.append("    id ");
		sql.append("  FROM");
		sql.append("    syds_user ");
		sql.append("WHERE userName LIKE '%" + searchStr + "%' ");
		sql.append("    OR realName LIKE '%" + searchStr + "%' ");
		sql.append("    OR major LIKE '%" + searchStr + "%' ");
		sql.append("    OR unit_id IN");
		sql.append("   (SELECT ");
		sql.append("      id ");
		sql.append("    FROM");
		sql.append("      syds_unit");
		sql.append("    WHERE unitName LIKE '%" + searchStr + "%'))");
		sql.append("    AND albumImage_url != ''");
		System.err.println(sql);
		List<Album> albums = qr.query(sql.toString(),
				new BeanListHandler<Album>(Album.class));
		return albums;
	}

}
