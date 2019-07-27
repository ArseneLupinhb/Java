package com.adou.syds.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.dao.AlbumDao;
import com.adou.syds.dao.impl.AlbumDaoImpl;
import com.adou.syds.domain.Album;
import com.adou.syds.service.AlbumService;

public class AlbumServiceImpl implements AlbumService {


	AlbumDao albumdao = new AlbumDaoImpl();
	
	@Override
	public boolean addAlbum(int user_id, Album album) throws SQLException {
		return albumdao.addAlbum(user_id, album);
	}

	@Override
	public boolean deleteAlbum(int user_id, String albumName) throws SQLException {
		return albumdao.deleteAlbum(user_id, albumName);
	}
	@Override
	public List<Album> queryAlbum(int user_id, Album album) throws SQLException {
		return albumdao.queryAlbum(user_id, album);
	}

	@Override
	public boolean alertAlbum(int user_id, String albumNameOld, Album album) throws SQLException {
		
		return albumdao.alertAlbum(user_id,albumNameOld, album);
	}

	@Override
	public int countAlbum(int i) throws SQLException {
		return albumdao.countAlbum(i);
	}

}
