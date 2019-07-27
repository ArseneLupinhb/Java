package com.adou.syds.service;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.domain.Image;

public abstract interface ImageService {

	boolean addImage(Image image) throws SQLException;

	boolean deleteImage(Image image)throws SQLException;

	Image queryImage(Image image)throws SQLException;

	List<Image> queryImages(Image image)throws SQLException;

	List<Image> searchImages(String searchsString)throws SQLException;

}
