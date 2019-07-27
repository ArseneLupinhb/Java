package com.adou.syds.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adou.syds.domain.Image;

public abstract interface ImageService {

	boolean addImage(Image image) throws SQLException;

	boolean deleteImage(Image image)throws SQLException;

	boolean alertImage(Image image)throws SQLException;

	int countImageByUser(int i)throws SQLException;

	int countImageByAlbum(int album_id)throws SQLException;

	List<Image> queryImagesByAlbum_id(int album_id)throws SQLException;
}
