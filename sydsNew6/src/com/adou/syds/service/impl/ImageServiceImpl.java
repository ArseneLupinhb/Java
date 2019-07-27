package com.adou.syds.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public boolean alertImage(Image image) throws SQLException {
		return imageDao.alertImage(image);
	}
	@Override
	public int countImageByUser(int i) throws SQLException {
		return imageDao.countImageByUser(i);
	}
	@Override
	public int countImageByAlbum(int album_id) throws SQLException {
		return imageDao.countImageByAlbum(album_id);
	}
	@Override
	public List<Image> queryImagesByAlbum_id(int album_id)
			throws SQLException {
		return imageDao.queryImagesByAlbum_id(album_id);
	}

}
