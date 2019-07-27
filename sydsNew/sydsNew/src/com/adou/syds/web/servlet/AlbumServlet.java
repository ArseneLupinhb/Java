package com.adou.syds.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adou.syds.domain.Album;
import com.adou.syds.domain.User;
import com.adou.syds.service.AlbumService;
import com.adou.syds.service.impl.AlbumServiceImpl;
import com.adou.syds.utils.CommonUtils;

public class AlbumServlet extends BaseServlet {
	private Album album = new Album();
	private AlbumService albumService = new AlbumServiceImpl();

	  public String countAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
			album = CommonUtils.toBean(req.getParameterMap(), Album.class);
			User user = (User) req.getSession().getAttribute("user");
			/*boolean isAdd =  albumService.addAlbum(user.getId(),album);*/     //user_id应从session域中获取
			int countAlbum =  albumService.countAlbum(1); 
			System.err.println("countAlbum："+countAlbum);
			req.getSession().setAttribute("countAlbum", countAlbum);
			
			return null;
		}
    public String addAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		User user = (User) req.getSession().getAttribute("user");
		/*boolean isAdd =  albumService.addAlbum(user.getId(),album);*/     //user_id应从session域中获取
		boolean isAdd =  albumService.addAlbum(1,album); 
		String jsonData = null;
		if (isAdd) {
			jsonData = "{\"message\":\"添加成功\"}";
		}
		else {
			jsonData = "{\"message\":\"添加失败\"}";
		}
		resp.getWriter().print(jsonData);
		System.err.println("isAdd："+isAdd);
		System.out.println("addAlbum: "+album);
		req.setAttribute("album", album);
		return "f:/AlbumServlet?method=queryAlbum";
	}
    
    public String alertAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		String albumNameOld = req.getParameter("albumNameOld");
		System.err.println("albumNameOld "+albumNameOld);
		User user = (User) req.getSession().getAttribute("user");
		//albumName 应该从点击进来修改的地方获取。
//		boolean isAlert =  albumService.alertAlbum(user.getId(), albumNameOld,album);   //从session中获取user_id
		boolean isAlert =  albumService.alertAlbum(1, albumNameOld, album);
		String jsonData = null;
		if (isAlert) {
			jsonData = "{\"message\":\"修改成功\"}";
		}
		else {
			jsonData = "{\"message\":\"修改失败\"}";
		}
		resp.getWriter().print(jsonData);
		System.err.println("isAlert："+isAlert);
		System.out.println("alertAlbum: "+album);
		return null;
	}
	
    public String deleteAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
    	album = CommonUtils.toBean(req.getParameterMap(), Album.class);
    	System.err.println(album);
		User user = (User) req.getSession().getAttribute("user");
		//albumName 应该从点击进来修改的地方获取。
//		boolean idDelete = albumService.deleteAlbum(user.getId(),album.getAlbumName());
		boolean idDelete = albumService.deleteAlbum(1,album.getAlbumName());
		String jsonData = null;
		if (idDelete) {
			jsonData = "{\"message\":\"删除成功\"}";
		}
		else {
			jsonData = "{\"message\":\"删除失败\"}";
		}
		resp.getWriter().print(jsonData);
		System.err.println("isDelete:"+idDelete);
		return null;
	}
    
    public String queryAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
    	album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		User user = (User) req.getSession().getAttribute("user");
//		List<Album> albumList=  albumService.queryAlbum(user.getId(),album);     //user_id应从session中获取
		List<Album> albumList=  albumService.queryAlbum(1,album);
		req.getSession().setAttribute("albumList", albumList);
		System.out.println("queryAlbum"+albumList);
		countAlbum(req,resp);
		return "f:/personalHomepage.jsp";
	}
}
