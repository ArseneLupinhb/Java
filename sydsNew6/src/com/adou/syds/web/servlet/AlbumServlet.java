package com.adou.syds.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.adou.syds.dao.AlbumDao;
import com.adou.syds.dao.impl.AlbumDaoImpl;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.User;
import com.adou.syds.service.AlbumService;
import com.adou.syds.service.impl.AlbumServiceImpl;
import com.adou.syds.utils.CommonUtils;

public class AlbumServlet extends BaseServlet {
	//private Album album = new Album();
	private AlbumService albumService = new AlbumServiceImpl();

	/*
	 * 根据id查询，拿出第一章存在相册封面,并修改这个相册的封面以及此时这个相册存在session中的封面，
	 * 
	 */
	public String setAlbumImage_url(HttpServletRequest req, HttpServletResponse resp, int album_id)
			throws SQLException, IOException {
		String albumImage_url = albumService.setAlbumImage_url(album_id);
		Album album = (Album) req.getSession().getAttribute("album");
		album.setAlbumImage_url(albumImage_url);
		return null;
	}

	/**
	 * 通过session里的album来获取id，并根据id查询相册的点赞数。
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */

	public String countPraiseByAlbum(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		Album album = (Album) req.getSession().getAttribute("album");
		int countPraiseByAlbum = albumService.countPraiseByAlbum(album.getId());
		req.getSession().setAttribute("countPraise", countPraiseByAlbum);
		return null;
	}

	/**
	 * 通过存在session中的用户查询相册数量。
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String countAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		int countAlbum = albumService.countAlbum(user.getId());
		req.getSession().setAttribute("countAlbum", countAlbum);
		return null;
	}

	/**
	 * 根据存在session中的用户来查询用户总的图片数。
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String countImageByUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		int countImageByUser = albumService.countImageByAlbum(user.getId());
		req.getSession().setAttribute("countImageByUser", countImageByUser);
		return null;
	}

	/**
	 * 添加相册，通过存在session中的用户的id添加相册。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String addAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Album album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		User user = (User) req.getSession().getAttribute("user");
		album.setUser_id(user.getId());
		boolean isAdd = albumService.addAlbum(user.getId(), user.getUserName(), album);
		String jsonData = null;
		if (isAdd) {
			jsonData = "{\"message\":\"添加成功\"}";
		} else {
			jsonData = "{\"message\":\"添加失败\"}";
		}
		resp.getWriter().print(jsonData);
		req.setAttribute("album", album);
		return null;
	}

	/**
	 * 修改相册,通过超链接中传过来的数据来得到相册id并修改相册
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String alertAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Album album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		String albumNameOld = req.getParameter("albumNameOld");
		User user = (User) req.getSession().getAttribute("user");
		boolean isAlert = albumService.alertAlbum(album);
		String jsonData = null;
		if (isAlert) {
			jsonData = "{\"message\":\"修改成功\"}";
		} else {
			jsonData = "{\"message\":\"修改失败\"}";
		}
		resp.getWriter().print(jsonData);
		return null;
	}

	/**
	 * 删除相册，通过传过来的相册id,来删除相册。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String deleteAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Album album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		User user = (User) req.getSession().getAttribute("user");
		String realPath =  req.getServletContext().getRealPath("/");
		boolean idDelete = albumService.deleteAlbum(album.getId(),realPath);
		String jsonData = null;
		if (idDelete) {
			jsonData = "{\"message\":\"删除成功\"}";
		} else {
			jsonData = "{\"message\":\"删除失败\"}";
		}
		resp.getWriter().print(jsonData);
		return null;
	}

	/**
	 * 查询相册，进入个人中心时，查询用户相册。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String queryAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		Album album = CommonUtils.toBean(req.getParameterMap(), Album.class);
		User user = (User) req.getSession().getAttribute("user");
		List<Album> albumList = albumService.queryAlbum(user.getId(), album);
		req.getSession().setAttribute("albumList", albumList);
		countAlbum(req, resp);
		countImageByUser(req, resp);
		return "f:/personalHomepage.jsp";
	}
}
