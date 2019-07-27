package com.adou.syds.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import com.adou.syds.dao.ImageDao;
import com.adou.syds.dao.impl.ImageDaoImpl;
import com.adou.syds.domain.Image;

public class GetImgsByPage extends HttpServlet {
	ImageDao imageDao = new ImageDaoImpl();

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		int album_id = Integer.parseInt(request.getParameter("albumId"));
		List<Image> images;
		try {
			images = imageDao.queryImagesByAlbum_id(album_id);
			
			JSONArray ja = JSONArray.fromObject(images);
			response.getWriter().print(ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
