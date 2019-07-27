package com.adou.syds.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adou.syds.service.AlbumService;
import com.adou.syds.service.impl.AlbumServiceImpl;

public class PraiseServlet extends BaseServlet {
	AlbumService albumService = new AlbumServiceImpl();
	
	 public String praiseAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		    boolean isPraise = Boolean.parseBoolean(req.getParameter("isPraise"));
		    int album_id = Integer.parseInt(req.getParameter("album_id"));
		    boolean isSuccess =  albumService.praiseAlbum(album_id,isPraise);
			return null;
	  }

}
