package com.adou.syds.service;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.domain.Album;

public abstract interface AlbumService {

	boolean addAlbum(int id, Album album) throws SQLException;

	boolean deleteAlbum(int id, String albumName) throws SQLException;

	List<Album> queryAlbum(int id, Album album) throws SQLException;

	boolean alertAlbum(int id, String albumNameOld, Album album) throws SQLException;

	int countAlbum(int i)throws SQLException;

}
