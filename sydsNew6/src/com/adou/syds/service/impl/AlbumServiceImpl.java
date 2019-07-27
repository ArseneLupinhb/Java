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
	public boolean addAlbum(int user_id,String userName, Album album) throws SQLException {
		return albumdao.addAlbum(user_id, userName, album);
	}

	@Override
	public boolean deleteAlbum(int album_id,String realPath) throws SQLException {
		return albumdao.deleteAlbum(album_id,realPath);
	}
	@Override
	public List<Album> queryAlbum(int user_id, Album album) throws SQLException {
		return albumdao.queryAlbum(user_id, album);
	}

	@Override
	public boolean alertAlbum(Album album) throws SQLException {
		
		return albumdao.alertAlbum(album);
	}

	@Override
	public int countAlbum(int i) throws SQLException {
		return albumdao.countAlbum(i);
	}

	@Override
	public int countImageByAlbum(int i) throws SQLException {
		return albumdao.countImageByAlbum(i);
	}

	@Override
	public boolean praiseAlbum(int album_id, boolean isPraise)
			throws SQLException {
		return albumdao.praiseAlbum(album_id, isPraise);
	}


	@Override
	public int countPraiseByAlbum(int id) throws SQLException {
		return albumdao.countPraiseByAlbum(id);
	}

	@Override
	public String setAlbumImage_url(int album_id) throws SQLException {
		return albumdao.setAlbumImage_url(album_id);
	}

	@Override
	public List<Album> searchAlbum(String searchStr) throws SQLException {
		return albumdao.searchAlbum(searchStr);
	}


}
