package com.adou.syds.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.adou.syds.dao.ImageDao;
import com.adou.syds.dao.impl.ImageDaoImpl;
import com.adou.syds.domain.Image;
import com.adou.syds.service.ImageService;


public class ImageServiceImpl implements  ImageService {

	ImageDao imageDao = new ImageDaoImpl();
	@Override
	public boolean addImage(Image image) throws SQLException {
		
		return imageDao.addImage(image);
	}
	@Override
	public boolean deleteImage(Image image) throws SQLException {
		return imageDao.deleteImage(image);
	}
	@Override
	public Image queryImage(Image image) throws SQLException {
		return imageDao.queryImage(image);
	}
	@Override
	public List<Image> queryImages(Image image) throws SQLException {
		return imageDao.queryImages(image);
	}
	@Override
	public List<Image> searchImages(String searchsString) throws SQLException {
		return imageDao.searchImages(searchsString);
	}

}
