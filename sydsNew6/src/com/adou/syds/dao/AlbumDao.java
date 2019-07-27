package com.adou.syds.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;

public abstract interface AlbumDao {
	
	/**
	 * 根据currentPage及pageSize查询并返回一页数据
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 * @throws SQLException 
	 */
	List<Album> listByPage(int currentPage, int pageSize, String condition) throws SQLException;

	boolean addAlbum(int id, String userName, Album album) throws SQLException;

	boolean deleteAlbum(int id, String realPath) throws SQLException;

	List<Album> queryAlbum(int id, Album album) throws SQLException;

	boolean alertAlbum(Album album) throws SQLException;

	/**
	 * 
	 * @param i
	 * @return 根据用户ID返回用户的相册数量
	 * @throws SQLException
	 */
	int countAlbum(int i) throws SQLException;

	int countImageByAlbum(int i) throws SQLException;

	boolean praiseAlbum(int album_id, boolean isPraise)throws SQLException;

	int countPraiseByAlbum(int id)throws SQLException;

	String setAlbumImage_url(int album_id)throws SQLException;

	List<Album> searchAlbum(String searchStr)throws SQLException;

	/**
	 * 根据contionString给的条件返回符合条件的相册的数量
	 * @param contionString 筛选条件（不含where的条件语句）
	 * @return
	 * @throws SQLException
	 */
	int countAlbum(String contionString) throws SQLException;
}
