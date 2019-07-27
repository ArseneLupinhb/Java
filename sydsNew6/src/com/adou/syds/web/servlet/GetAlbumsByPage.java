package com.adou.syds.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.adou.syds.dao.AlbumDao;
import com.adou.syds.dao.ImageDao;
import com.adou.syds.dao.impl.AlbumDaoImpl;
import com.adou.syds.dao.impl.ImageDaoImpl;
import com.adou.syds.domain.Album;
import com.adou.syds.domain.Image;

public class GetAlbumsByPage extends HttpServlet {

	int pageSize = 12;// 每页显示数量

	AlbumDao albumDao = new AlbumDaoImpl();

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;//默认当前页为第1页
		String pageStr = request.getParameter("currentPage");
		if (pageStr != null) {
			currentPage = Integer.valueOf(pageStr);
		}

		String condition = request.getParameter("condition");// 搜索的关键词
		System.err.println("condition--"+condition);
		
		// 取数据
		if (condition == null||condition=="") {// 无条件搜索
			condition = "";
		}
		List<Album> albums = new ArrayList<Album>();
		try {
			albums = albumDao.listByPage(currentPage, pageSize, condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int count = 0;
		try {
			count = albumDao.countAlbum(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int totalPage = count % pageSize == 0 ? count / pageSize : count
				/ pageSize + 1; //根据相册数量计算分页的总页数

		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("condition", condition);
		request.setAttribute("albums", albums);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		// int
		// currentPage=Integer.parseInt(request.getParameter("page"));//获取ajax传来的用户点击的页码
		// int
		// pageSize=Integer.parseInt(request.getParameter("size"));//获取ajax传来的每页显示图片数
		// String ip="127.0.0.1";
		// String condition="";
		// System.out.println("=====currentPage="+currentPage+"\n----pageSize="+pageSize);
		//
		// ArrayList<Image> imgs=new ArrayList<Image>();
		// ImageDao imgDao=new ImageDaoImpl();
		// imgs=imgDao.list(currentPage, pageSize, ip, condition);
		//
		// HttpSession session=request.getSession();
		// session.setAttribute("imgs", imgs);
		// //request.getRequestDispatcher("index.html").forward(request,
		// response);
		// System.out.println("------到此一游end doPost()------");
		//
		// PrintWriter out=null;
		// try {
		// out=response.getWriter();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// System.out.println("-----out对象创建异常---------");
		// }
		//
		// //使用json封装对象的时候, 对于日期格式会报错，使用以下方法解决
		// JsonConfig jsonConfig = new JsonConfig();
		// jsonConfig.registerJsonValueProcessor(java.util.Date.class,new
		// JsonValueProcessor() {
		// private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		// public Object processObjectValue(String key, Object value, JsonConfig
		// jsonConfig) {
		// return value == null ?"" : sd.format(value);
		// }
		// public Object processArrayValue(Object value, JsonConfig jsonConfig)
		// {
		// return null;
		// }
		// });
		// //将从数据库中获取到的imgs封装在JSONArray中以回传给ajax
		// JSONArray ja=JSONArray.fromObject(imgs,jsonConfig);
		// try {
		// out.print(ja.toString());//回传数据给ajax
		// System.out.println("-----imgs--"+imgs.toString()+"---------");
		// System.out.println("-----ja----"+ja.toString()+"-----------");
		// out.flush();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// System.out.println("-----回传数据异常---------");
		// }
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
