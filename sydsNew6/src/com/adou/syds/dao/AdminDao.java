package com.adou.syds.dao;

import java.util.ArrayList;
import java.util.List;

import com.adou.syds.domain.Admin;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;

public abstract interface AdminDao {

	boolean login(Admin admin);

	List<Album> showAlbums();
	
	List<Image> showImages();
	
	List<Album> showAllAlbums();
	
	List<Image> showAllImages();

	boolean passAlbum(int id);

	boolean passImage(int id);

}
