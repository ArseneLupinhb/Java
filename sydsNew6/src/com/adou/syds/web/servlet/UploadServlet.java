package com.adou.syds.web.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.adou.syds.domain.Image;
import com.adou.syds.domain.User;
import com.adou.syds.utils.CommonUtils;

public class UploadServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getFiles(req,resp);
	}
	
	private void getFiles(HttpServletRequest req,HttpServletResponse resp) throws IOException  {
		int singleFile = 8;      //单个文件大小
		int totalSize = 50;      //总大小
		boolean isAdd = true;
		
		List<Image> images=new ArrayList<>();
		try {
		
			DiskFileItemFactory dfif = new DiskFileItemFactory(1024*20, new File(this.getServletContext().getRealPath("/img/temp")));
			ServletFileUpload fileUpload = new ServletFileUpload(dfif);
			      
	        fileUpload.setSizeMax(1024 * 1024 * totalSize);       //上传文件总大小限制为50M
	        fileUpload.setFileSizeMax(1024 * 1024 * singleFile);    //上传单个文件大小限制为5M
	        User user = new User();
	        user = (User) req.getSession().getAttribute("user");
			@SuppressWarnings("unchecked")
			List<FileItem> fileItems = fileUpload.parseRequest(req);
			System.err.println(fileItems.size());
				for (int i = 0; i < fileItems.size(); i++) {
					   Image image=new Image();
						FileItem fileItem = fileItems.get(i);
						if (!fileItem.isFormField()) {
							
							String name = fileItem.getName();
							String fileType = fileItem.getContentType();
							if (!fileType.contains("image/")) {
								break;
							}
							String savePath = this.getServletContext().getRealPath("/img/upload");
							fileItem.write(path(savePath, name,image,user.getRealName(),user.getPhone()));
							toImageBean(images,image);
							
						}
				}
		}catch (Exception e) {
			e.printStackTrace();
			isAdd = false;
			System.out.println("{\"message\":\" 请上传单个文件不超过"+singleFile+"M总大小不超过"+totalSize+"M的作品\"}");
			String jsonData = null;
			jsonData =  "{\"message\":\"请上传单个文件不超过8M总大小不超过50M的作品\"}";
			
		    resp.getWriter().print(jsonData);
		}
		
		if (isAdd) {
			
			if (images.size() ==  0 ) {
				String jsonData = null;
				jsonData =  "{\"message\":\"请刷新浏览器，重新上传\"}";
				
			    resp.getWriter().print(jsonData);
				
			}else {
				try {
					req.setAttribute("imagesAdd", images);
					req.getRequestDispatcher("/ImageServlet?method=addImage").forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		images.clear();
	}
	
	
	private void toImageBean(List<Image> images,Image image) {
		Image imageTemp = new Image();
		imageTemp.setImage_url(image.getImage_url());
		//imageTemp.setImage_backName(image.getImage_backName());
		//imageTemp.setTitle(image.getTitle());      //title设置为文件原名
		//imageTemp.setUser_id(image.getId());     //从session中获取user_id
		//imageTemp.setIntroduction("照片描述");   //可能需添加,页面中没有描述框
		//imageTemp.setPublish_time(new Timestamp(new Date().getTime()));
		//imageTemp.setPlace("地点");    //
		imageTemp.setType(0);          //没有要求图片类型
		imageTemp.setIsPass(0);
		images.add(imageTemp);
	}

	private File path(String savepath, String filename,Image image, String realName, String phone) {
		int lastIndex = filename.lastIndexOf("\\");
		if(lastIndex != -1) {
			filename = filename.substring(lastIndex + 1);
		}
		
		int hCode = filename.hashCode();
		String dir1 = Integer.toHexString(hCode & 0xF);
		String dir2 = Integer.toHexString(hCode >>> 4 & 0xF);
		savepath = savepath + "/" + dir1 + "/" + dir2;
		new File(savepath).mkdirs();
		
		String uuid = CommonUtils.uuid();
		
		
		String image_backName = filename.substring(filename.lastIndexOf("."));
		
		lastIndex = filename.lastIndexOf(".");
		if(lastIndex != -1) {
			filename = filename.substring(0,lastIndex);
		}
		//image.setTitle(filename);
		//image.setImage_backName(image_backName);
		image.setImage_url("/img/upload"+ "/" + dir1 + "/" + dir2+ "/"+realName+"_"+phone+"_" +uuid+image_backName);
		filename =realName+"_"+phone+"_"+ uuid + image_backName;
		return new File(savepath, filename);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
