package com.adou.syds.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.adou.syds.dao.AlbumDao;
import com.adou.syds.domain.Album;
import com.adou.syds.utils.JdbcUtil;

public class AlbumDaoImpl implements AlbumDao {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
  	@Override
	public boolean addAlbum(int user_id, Album album) throws SQLException {
		String sql = "INSERT INTO syds_album(albumName,description,user_id) VALUES(?, ?, ?)";
		Object[] params = {album.getAlbumName(), album.getDescription(), user_id};
		if (qr.update(sql,params) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteAlbum(int user_id, String albumName) throws SQLException {
		String sql = "DELETE FROM syds_album WHERE user_id = ? AND albumName = ?";
		Object[] params = {user_id, albumName};
		if (qr.update(sql,params) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Album> queryAlbum(int user_id, Album album) throws SQLException {
		String sql = "SELECT * FROM syds_album WHERE user_id = ?";
		Object[] params = {user_id};
		List<Album> albums = qr.query(sql, new BeanListHandler<Album>(Album.class), params);
 		return albums;
	}

	@Override
	public boolean alertAlbum(int user_id, String albumNameOld, Album album) throws SQLException {
		String sql_id = "SELECT id FROM syds_album WHERE user_id = ? AND albumName = ?";
		Object[] params_id = {user_id, albumNameOld};
		Album album_id =  qr.query(sql_id,new BeanHandler<Album>(Album.class),params_id);
		
		String sql = "UPDATE syds_album SET albumName = ?, description = ? , note = ?  WHERE id = ?";
		Object[] params = {album.getAlbumName(), album.getDescription(), album.getNote(),album_id.getId()};
		System.err.println(sql);
		if (qr.update(sql,params) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int countAlbum(int user_id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM syds_album WHERE user_id = ?";
		Object[] params = {user_id};
		Number count = (Number) qr.query(sql, new ScalarHandler(),params);
 		return  count.intValue();
	}

}
