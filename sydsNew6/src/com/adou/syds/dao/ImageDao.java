package com.adou.syds.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adou.syds.domain.Image;


public abstract interface ImageDao {

	boolean addImage( Image image) throws SQLException;
	
	boolean deleteImage(Image image) throws SQLException;
	
	boolean alertImage(Image image) throws SQLException;

	ArrayList<Image> list(int currentPage, int pageSize, String ip,
			String condition);

	int countImageByUser(int i)throws SQLException;

	int countImageByAlbum(int album_id)throws SQLException;

	int count();

	List<Image> queryImagesByAlbum_id(int album_id)throws SQLException;
	
	public boolean deleteImageById(int imageId) throws SQLException;
	
	public Image queryImageByImageId(int imageId) ;
	
	public int Pass(int id);

}
