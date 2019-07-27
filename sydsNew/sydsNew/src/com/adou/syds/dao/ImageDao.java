package com.adou.syds.dao;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.domain.Image;


public abstract interface ImageDao {

	boolean addImage( Image image) throws SQLException;
	
	boolean deleteImage(Image image) throws SQLException;
	
	Image queryImage(Image image) throws SQLException;

	List<Image> queryImages(Image image) throws SQLException;

	List<Image> searchImages(String searchsString) throws SQLException;

}
