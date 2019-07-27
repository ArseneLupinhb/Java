package com.adou.syds.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.adou.syds.dao.AdminDao;
import com.adou.syds.dao.impl.AdminDaoImpl;
import com.adou.syds.domain.Admin;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;
import com.adou.syds.service.AlbumService;
import com.adou.syds.service.ImageService;
import com.adou.syds.service.impl.AlbumServiceImpl;
import com.adou.syds.service.impl.ImageServiceImpl;
import com.adou.syds.utils.DeleteFile;

public class AdminServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminDao adminDao = new AdminDaoImpl();
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @param album_id
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		Admin admin = new Admin();
		admin.setAdminName(req.getParameter("adminName"));
		admin.setAdminPwd(req.getParameter("adminPwd"));
		System.err.println(admin);
		String message = "";
		if (adminDao.login(admin)) {
			req.getSession().setAttribute("admin", admin);
			resp.sendRedirect("Back/manageIndex.jsp");
		}else {
			 message = "用户名或密码错误！";
			 req.setAttribute("message", message);
			 RequestDispatcher dispatcher = req.getRequestDispatcher("Back/login.jsp");
			 try {
				dispatcher.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 退出
	 * @param req
	 * @param resp
	 * @param album_id
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		req.getSession().removeAttribute("admin");
        resp.sendRedirect("Back/login.jsp");
		return null;
	}
	/**
	 * 审核不通过，删除相册
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String deleteAlbum(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		String album_id =  req.getParameter("id");
		int id = 0 ;
		if (album_id != null && album_id.length() != 0) {
			id = Integer.parseInt(album_id);
		}
	    AlbumService albumService = new AlbumServiceImpl();
	    String realPath =  req.getServletContext().getRealPath("/");
	    boolean isDelete =  albumService.deleteAlbum(id,realPath);
	    String str = null;
		if (isDelete) {
			str = "[{\"success\":\"true\"}]";
		}
		else {
			str = "[{\"errorMsg\":\"删除失败！\"}]";
		}
		System.err.println(isDelete);
		resp.getWriter().print(str);
		return null;
	}
	
	/**
	 * 删除图片
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String deleteImage(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		String image_id =  req.getParameter("id");
		String image_url = req.getParameter("image_url");
		if (image_url != null && image_url.length() != 0) {
    		String realPath = req.getServletContext().getRealPath(image_url);
        	DeleteFile.deleteFile(realPath);
		}
		int id = 0 ;
		if (image_id != null && image_id.length() != 0) {
			id = Integer.parseInt(image_id);
		}
		Image image = new Image();
		image.setId(id);
		ImageService imageService = new ImageServiceImpl();
	    boolean isDelete =  imageService.deleteImage(image);
	    
	    
	    String str = null;
		if (isDelete) {
			str = "[{\"success\":\"true\"}]";
		}
		else {
			str = "[{\"errorMsg\":\"删除失败！\"}]";
		}
		System.err.println(isDelete);
		String album_id0 = req.getParameter("album_id");
		int album_id = 0;
		System.err.println(album_id0);
		if (album_id0 != null ) {
			album_id = Integer.parseInt(album_id0);
		}
		AlbumService albumService = new AlbumServiceImpl();
		albumService.setAlbumImage_url(album_id);
		resp.getWriter().print(str);
		return null;
	}
	
	/**
	 * 审核通过相册
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String passAlbum(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		String album_id =  req.getParameter("id");
		int id = 0 ;
		if (album_id != null && album_id.length() != 0) {
			id = Integer.parseInt(album_id);
		}
		boolean isPass =  adminDao.passAlbum(id);
		String str = null;
		if (isPass) {
			str = "[{\"success\":\"true\"}]";
		}
		else {
			str = "[{\"errorMsg\":\"审核失败！\"}]";
		}
		System.err.println(isPass);
		resp.getWriter().print(str);
		return null;
	}
	/**
	 * 图片通过审核
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String passImage(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		String image_id =  req.getParameter("id");
		int id = 0 ;
		if (image_id != null && image_id.length() != 0) {
			id = Integer.parseInt(image_id);
		}
		boolean isPass =  adminDao.passImage(id);
		String str = null;
		if (isPass) {
			str = "[{\"success\":\"true\"}]";
		}
		else {
			str = "[{\"errorMsg\":\"审核失败！\"}]";
		}
		System.err.println(isPass);
		resp.getWriter().print(str);
		return null;
	}
	/**
	 * 展示未通过审核的相册
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String showAlbums(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		 ArrayList<Album> showAlbums = (ArrayList<Album>) adminDao.showAlbums();
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
		 private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 public Object processObjectValue(String key, Object value, JsonConfig
		 jsonConfig) {
		 return value == null ?"" : sd.format(value);
		 }
		 public Object processArrayValue(Object value, JsonConfig jsonConfig)
		 {
		 return null;
		 }
		 });
		 JSONArray ja = JSONArray.fromObject(showAlbums,jsonConfig );
		resp.getWriter().print(ja);
		return null;
	}
	public String showImages(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		 ArrayList<Image> showImages = (ArrayList<Image>) adminDao.showImages();
		 System.err.println(showImages);
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
		 private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 public Object processObjectValue(String key, Object value, JsonConfig
		 jsonConfig) {
		 return value == null ?"" : sd.format(value);
		 }
		 public Object processArrayValue(Object value, JsonConfig jsonConfig)
		 {
		 return null;
		 }
		 });
		 JSONArray ja = JSONArray.fromObject(showImages,jsonConfig );
		resp.getWriter().print(ja);
		return null;
	}
	
    /**
     * 展示有违背删除的相册
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
	public String showAllAlbums(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		 ArrayList<Album> showAllAlbums = (ArrayList<Album>) adminDao.showAllAlbums();
		 System.err.println(showAllAlbums);
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
		 private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 public Object processObjectValue(String key, Object value, JsonConfig
		 jsonConfig) {
		 return value == null ?"" : sd.format(value);
		 }
		 public Object processArrayValue(Object value, JsonConfig jsonConfig)
		 {
		 return null;
		 }
		 });
		 JSONArray ja = JSONArray.fromObject(showAllAlbums,jsonConfig );
		resp.getWriter().print(ja);
		return null;
	}
	/**
	 * 展示所有未被删除的图片
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String showAllImages(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		 ArrayList<Image> showAllImages = (ArrayList<Image>) adminDao.showAllImages();
		 System.err.println(showAllImages);
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
		 private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 public Object processObjectValue(String key, Object value, JsonConfig
		 jsonConfig) {
		 return value == null ?"" : sd.format(value);
		 }
		 public Object processArrayValue(Object value, JsonConfig jsonConfig)
		 {
		 return null;
		 }
		 });
		 JSONArray ja = JSONArray.fromObject(showAllImages,jsonConfig );
		resp.getWriter().print(ja);
		return null;
	}
	
	public void downloadImage(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String realName = req.getParameter("realName");
		String phone = req.getParameter("phone");
		String image_url = req.getParameter("image_url");
		System.err.println(realName+"\n"+phone+"\n"+image_url);

    	String image_backName = image_url.substring(image_url.lastIndexOf("."));
		
		
		String filename = realName+"_"+phone+image_backName;
		String filePath = this.getServletContext().getRealPath(image_url);
		
		String framename = null;
		try {
			framename = new String(filename.getBytes("GBK"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		
		}
		
		System.err.println(filename+"\n"+filePath+"\n"+framename);
		
		String contentType = this.getServletContext().getMimeType(filePath); 
		String contentDisposition = "attachment;filename="+framename;
		System.err.println(contentType+"\n"+contentDisposition);
		FileInputStream fileInputStream = new FileInputStream(filePath);
		
		resp.setHeader("Content-Type", contentType);
		resp.setHeader("Content-Disposition", contentDisposition);
		
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		
		IOUtils.copy(fileInputStream, servletOutputStream);
		fileInputStream.close();
	}
}
