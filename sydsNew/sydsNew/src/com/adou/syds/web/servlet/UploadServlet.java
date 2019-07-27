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
import com.adou.syds.utils.CommonUtils;

public class UploadServlet extends HttpServlet{

	public static List<Image> images = new ArrayList<Image>();
	private  static Image  image = new Image();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getFiles(req,resp);
	}
	
	private void getFiles(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory dfif = new DiskFileItemFactory(1024*20, new File("/temp"));
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
        fileUpload.setSizeMax(1024 * 1024 * 50);       //上传文件总大小限制为50M
        fileUpload.setFileSizeMax(1024 * 1024 * 5);    //上传单个文件大小限制为5M
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> fileItems = fileUpload.parseRequest(req);
			System.err.println("hello files");
			
				for (int i = 0; i < fileItems.size(); i++) {
					if (i == fileItems.size() - 1) {
						FileItem fileItem = fileItems.get(i);
						if (!fileItem.isFormField()) {
							
							String name = fileItem.getName();
							String fileType = fileItem.getContentType();
							System.err.println(fileType);
							if (!fileType.contains("image/")) {
								System.err.println("请全部上传图片文件");
								break;
							}
							String savePath = this.getServletContext().getRealPath("/img/upload");
							fileItem.write(path(savePath, name));
							toImageBean();
						}
					}
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("1111111111");
		req.getRequestDispatcher("/ImageServlet?method=addImage").forward(req, resp);
		images.clear();
	}
	
	
	private void toImageBean() {
		Image imageTemp = new Image();
		imageTemp.setImage_url(image.getImage_url());
		imageTemp.setImage_backName(image.getImage_backName());
		imageTemp.setTitle(image.getTitle());      //title设置为文件原名
		imageTemp.setUser_id(1);     //从session中获取user_id
		imageTemp.setIntroduction("照片描述");   //可能需添加,页面中没有描述框
		imageTemp.setPublish_time(new Timestamp(new Date().getTime()));
		imageTemp.setPlace("地点");    //
		imageTemp.setType(0);          //没有要求图片类型
		imageTemp.setAlbum_id(imageTemp.getAlbum_id());      //从点击进来的地方获取album_id
		                           //其他的暂时不做处理
		System.err.println("imageTemp："+imageTemp);
		images.add(imageTemp);
	}

	private File path(String savepath, String filename) {
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
		
		image.setTitle(filename);
		
		String image_backName = filename.substring(filename.lastIndexOf("."));
		System.err.println(image_backName);
		image.setImage_backName(image_backName);
		image.setImage_url("/img/upload"+ "/" + dir1 + "/" + dir2+ "/" +uuid+image_backName);
		System.err.println(image.getImage_url());
		filename = uuid + image_backName;
		return new File(savepath, filename);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
