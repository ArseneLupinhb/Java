package com.adou.syds.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adou.syds.domain.Image;
import com.adou.syds.domain.Praise;
import com.adou.syds.domain.User;
import com.adou.syds.service.ImageService;
import com.adou.syds.service.impl.ImageServiceImpl;
import com.adou.syds.utils.IpUtils;


public class ImageServlet extends BaseServlet {
	ImageService imageService = new ImageServiceImpl();
	Image image = new Image();
	int album_id = 0 ;

	public String getAlbum_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
		album_id = Integer.parseInt(req.getParameter("album_id"));
		System.err.println("addImage:"+album_id);
		return null;
	}
	
	public String addImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException{
		List<Image> images = UploadServlet.images;
		for (Image image : images) {
			image.setAlbum_id(album_id);
		}
		System.err.println("images.size())::==---"+images.size());
		System.err.println("ImageServlet-image:"+images);
		for (Image image : images) {
			boolean isAdd =  imageService.addImage(image);
			System.err.println("idAdd:"+isAdd);
		}
	
//		String jsonData = "{\"message\": "+album_id+"}";
		resp.getWriter().print(String.valueOf(album_id));
		return "f:/ImageServlet?method=queryImagesByUser&album_id="+album_id+"";
	}
	
    public String deleteImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    	Image image = new Image();     //从点击进来的图片中获取image_url
    	image.setImage_url("/img/upload/8/b/ACA8FFCF8FEC450AA216CBE8CC964232.jpg");
    	boolean isDelete =  imageService.deleteImage(image);
    	System.err.println("idDelete:"+isDelete);
		return null;
	}
    
    public String queryImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
	    Image image = new Image();
	    image.setImage_url("/img/upload/8/b/ACA8FFCF8FEC450AA216CBE8CC964232.jpg");
	    image = imageService.queryImage(image);
	    System.err.println(image);
	    req.setAttribute("image", image);
    	return "f:/image.jsp";
    	
    }
    
    /**
     * 查看用户的个人图片，按照用户和相册来查询
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String queryImagesByUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    	String isNull =  req.getParameter("album_id");
    	int album_id ;
    	if (isNull == null || isNull.length() == 0) {
			album_id = this.album_id;
		}else {
			album_id = Integer.parseInt(isNull);
		}
	    Image image = new Image();
	    image.setUser_id(1);        //从session中获取
	    image.setAlbum_id(album_id);         //
	    List<Image> images =  imageService.queryImages(image);
	    System.err.println(images);
	    req.setAttribute("images", images);
    	return "f:/photoShow.jsp";
    	
    }
    
    public String queryAllImages(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
	    Image image = new Image();
	    
    	return null;
    	
    }
    
    /**
     * 从搜索框搜索图片通过
     *    unitName
     *    userName, realName, major, 
     *    albumName, title, introduction
     *    
     * @param req
     * @param resp
     * @return
     * @throws SQLException
     */
    public String searchImages(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String searchsString = req.getParameter("searchString");
    	List<Image> searchImages = imageService.searchImages(searchsString);
    	System.err.println(searchImages);
    	System.err.println("");
    	return null;
    	
    }
    
    public String praiseImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    	Praise praise = new Praise();
    	praise.setImage_id(55);      //从点击图片的地方获取image_id
    	praise.setIpAddress(IpUtils.getIpAddr(req));
    	praise.setPraise_time(new Timestamp(new Date().getTime()));
    	User user = (User) req.getSession().getAttribute("user");
    	if (user != null) {
			praise.setUserName(user.getUserName());
		}else {
			praise.setUserName("visit");
		}
    	req.setAttribute("praise", praise);
    	return "f:/PraiseServlet?method=praise";
    }
    
    public String cancelPraiseImage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    	return "f:/PraiseServlet?method=cancelPraise";
    }
}
