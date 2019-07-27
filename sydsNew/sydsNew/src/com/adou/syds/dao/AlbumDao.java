package com.adou.syds.dao;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.domain.Album;

public abstract interface AlbumDao {

	boolean addAlbum(int id, Album album) throws SQLException;

	boolean deleteAlbum(int id, String albumName) throws SQLException;

	List<Album> queryAlbum(int id, Album album) throws SQLException;

	boolean alertAlbum(int user_id, String albumNameOld, Album album) throws SQLException;

	int countAlbum(int i) throws SQLException;

}
