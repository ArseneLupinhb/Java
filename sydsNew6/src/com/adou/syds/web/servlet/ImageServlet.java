package com.adou.syds.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;
import com.adou.syds.domain.Praise;
import com.adou.syds.domain.User;
import com.adou.syds.service.AlbumService;
import com.adou.syds.service.ImageService;
import com.adou.syds.service.impl.AlbumServiceImpl;
import com.adou.syds.service.impl.ImageServiceImpl;
import com.adou.syds.utils.CommonUtils;
import com.adou.syds.utils.DeleteFile;
import com.adou.syds.utils.IpUtils;
import com.adou.syds.utils.LayerPhotosGetJSON;


public class ImageServlet extends BaseServlet {
	ImageService imageService = new ImageServiceImpl();
    
    /**
     * 当用户在上传时，重新选择相册时，获取当前相册的id.
     * 并更新session中相册的id
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
	public void  getAlbum_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		//int album_id = Integer.parseInt(req.getParameter("album_id"));
		String album_id0 = req.getParameter("album_id");
		System.err.println(album_id0);
		if (album_id0 !=  null) {
			int album_id = Integer.parseInt(album_id0);
			Album album = (Album) req.getSession().getAttribute("album");
			album.setId(album_id);
			req.getSession().setAttribute("album", album);
		}
		
	}
	
	/**
	 * 首页中通过超链接中相册id查询相册中的图片
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String queryImagesByAlbum_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		int album_id = Integer.parseInt(req.getParameter("album_id"));
		List<Image> images = imageService.queryImagesByAlbum_id(album_id);
		
		String jsonData = LayerPhotosGetJSON.getJSON((ArrayList<Image>) images, req.getContextPath());
		resp.getWriter().print(jsonData);
		
		return null;
	}
	
	/**
	 * 通过session中用户的id来查询用户的图片数量。用与对图片操作后的刷新。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public String countImageByUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		User user = (User) req.getSession().getAttribute("user");
		int countImageByUser = imageService.countImageByUser(user.getId());    
		req.getSession().setAttribute("countImageByUser", countImageByUser);
		return null;
	}
	
	/**
	 * 查询相册中的图片数量，根据存在session中相册id来查询。用于图片操作后的更新和首次进入时的查询.
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 */
	public String countImageByAlbum(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		Album album = (Album) req.getSession().getAttribute("album");
		int album_id = album.getId();
		int countImageByAlbum = imageService.countImageByAlbum(album_id);
		req.getSession().setAttribute("countImageByAlbum", countImageByAlbum);
		return null;
	}
	
	/**
	 * 修改图片，通过获取链接中的值，得到图片的id进行修改。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String alertImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		Image image = new Image();
		image.setId(Integer.parseInt(req.getParameter("image_id")));
		image.setTitle(req.getParameter("title"));
		image.setIntroduction(req.getParameter("introduction"));
        boolean isAlert =  imageService.alertImage(image);
        String jsonData = null;
		if (isAlert) {
			jsonData = "{\"message\":\"修改成功\"}";
		}
		else {
			jsonData = "{\"message\":\"修改失败\"}";
		}
		resp.getWriter().print(jsonData);
		return null;
	}
	
	/**
	 * 添加图片，通过UploadServlet存在request中的imagesAdd得到要上传的文件，然后
	 * 从session中获取并设置用户id,获取并设置相册id
	 * 添加之后，通过第一章照片的URL设置为相册的URL，然后统计用户的图片的数量。
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String addImage(HttpServletRequest req, HttpServletResponse resp) {
		@SuppressWarnings("unchecked")
		List<Image> images = (List<Image>) req.getAttribute("imagesAdd");
		Album album = (Album) req.getSession().getAttribute("album");
		User user = (User) req.getSession().getAttribute("user");
		for (Image image : images) {
			image.setAlbum_id(album.getId());
			image.setUser_id(user.getId());
		}
		for (Image image : images) {
			try {
				boolean isAdd =  imageService.addImage(image);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		AlbumService albumService = new AlbumServiceImpl();
		try {
			albumService.setAlbumImage_url(images.get(0).getAlbum_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    try {
			countImageByUser(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    String jsonData = null;
		
		jsonData = "{\"message\":\"success\"}";
		
		try {
			resp.getWriter().print(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除照片，根据传过来的图片的id删除图片。
	 * 删除照片之后，重新设置相册url.通过session中相册的id
	 * 统计用户照片的数量
	 * @param req
	 * @param resp
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
    public String deleteImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    	Image image = new Image();     //从点击进来的图片中获取image_id
    	image.setId(Integer.parseInt(req.getParameter("image_id")));
    	String image_url = req.getParameter("image_url");
    	
    	if (image_url != null && image_url.length() != 0) {
    		String realPath = req.getServletContext().getRealPath(image_url);
        	DeleteFile.deleteFile(realPath);
		}
    	
    	
    	boolean isDelete =  imageService.deleteImage(image);
    	AlbumServlet albumServlet = new AlbumServlet();
    	
    	Album album = (Album) req.getSession().getAttribute("album");
		albumServlet.setAlbumImage_url(req, resp, album.getId());
		
    	countImageByUser(req, resp);
    	
    	String jsonData = null;
		if (isDelete) {
			jsonData = "{\"message\":\"删除成功\"}";
		}
		else {
			jsonData = "{\"message\":\"删除失败\"}";
		}
		resp.getWriter().print(jsonData);
		
		return null;
	}
    
    /**
     * 查看用户的个人图片，按照用户和相册来查询
     * 从相册点击过来查询图片，并查询相关信息
     * 并把相册信息存在session域中
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public String queryImagesBA(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    	Album album = CommonUtils.toBean(req.getParameterMap(), Album.class);
    	String albumName = req.getParameter("albumName");
    	if (albumName != "") {
			album.setAlbumName(albumName);
		}
    	System.err.println(album);
    	List<Image> images =  imageService.queryImagesByAlbum_id(album.getId());
	    req.setAttribute("images", images);
	    req.getSession().setAttribute("album", album);
	    
	    countImageByUser(req, resp);
	    countImageByAlbum(req, resp);
	    AlbumServlet albumServlet = new AlbumServlet();
	    albumServlet.countPraiseByAlbum(req, resp);
	    
	    return "f:/photoShow.jsp";
    }
    
    /**
     * 基于图片的删除、修改、添加之后的查询图片的操作。
     * 通过从相册点击过来所存储在session中的相册id查询。
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String queryImagesBySession(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    	Album album = (Album) req.getSession().getAttribute("album");
    	
	    List<Image> images =  imageService.queryImagesByAlbum_id(album.getId());
	    
	    req.setAttribute("images", images);
	    countImageByUser(req, resp);
	    countImageByAlbum(req, resp);
	    AlbumServlet albumServlet = new AlbumServlet();
	    albumServlet.countPraiseByAlbum(req, resp);
	    return "f:/photoShow.jsp";
    }
    
}
