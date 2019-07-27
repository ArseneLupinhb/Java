package com.adou.syds.service;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.domain.Album;

public abstract interface AlbumService {

	boolean addAlbum(int id, String string, Album album) throws SQLException;

	boolean deleteAlbum(int id,String realPath) throws SQLException;

	List<Album> queryAlbum(int id, Album album) throws SQLException;

	boolean alertAlbum(Album album) throws SQLException;

	int countAlbum(int i)throws SQLException;

	int countImageByAlbum(int i)throws SQLException;

	boolean praiseAlbum(int album_id, boolean isPraise)throws SQLException;

	int countPraiseByAlbum(int id)throws SQLException;

	String setAlbumImage_url(int album_id)throws SQLException;

	List<Album> searchAlbum(String searchStr)throws SQLException;

}
