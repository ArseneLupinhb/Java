package com.adou.syds.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adou.syds.dao.ImageDao;
import com.adou.syds.dao.impl.ImageDaoImpl;
import com.adou.syds.domain.Image;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ManageSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		String status = request.getParameter("status");
		request.getParameter("status");
		if ((!status.equals("helpManager")) && (!status.equals("backLogin"))
				&& (session.getAttribute("admin") == null)) {
			request.setAttribute("message", "由于未知原因无法检测到您的账户，请重新登录！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("Back/login.jsp");
			dispatcher.forward(request, response);
		} else {
			if (status.equals("helpManager")) {
				if (session.getAttribute("admin") != null) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("Back/manageIndex.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("Back/login.jsp");
					dispatcher.forward(request, response);
				}
			} else if (status.equals("backExit")) {
				session.removeAttribute("admin");
				response.sendRedirect("Back/login.jsp");
			}

			if (status.equals("showImage")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("Back/showImage.jsp");
				dispatcher.forward(request, response);
			}
			if (status.equals("imageList")) {
				int currentPage = 1;
				int size = 10;
				String ip = "127.0.0.1";
				String condition = "";
				ImageDao imageDao = new ImageDaoImpl();
				List<Image> images = imageDao.list(currentPage, size, ip,
						condition);
				// request.setAttribute("image", images);

				// 使用json封装对象的时候, 对于日期格式会报错，使用以下方法解决
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(java.util.Date.class,
						new JsonValueProcessor() {
							private SimpleDateFormat sd = new SimpleDateFormat(
									"yyyy-MM-dd");

							public Object processObjectValue(String key,
									Object value, JsonConfig jsonConfig) {
								return value == null ? "" : sd.format(value);
							}

							public Object processArrayValue(Object value,
									JsonConfig jsonConfig) {
								return null;
							}
						});
				// 将从数据库中获取到的imgs封装在JSONArray中以回传给ajax
				JSONArray ja = JSONArray.fromObject(images, jsonConfig);
				response.getWriter().write(ja.toString());
			}

			if (status.equals("AllList")) {
				// ImageDao imageDao = new ImageDaoImpl();
				// List<Image> images = imageDao.list(1, 10, "", "");
				// request.setAttribute("image", images);
				// response.getWriter().write(imageDao.getJson(images));

				int currentPage = 1;
				int size = 10;
				String ip = "127.0.0.1";
				String condition = "";
				ImageDao imageDao = new ImageDaoImpl();
				List<Image> images = imageDao.list(currentPage, size, ip,
						condition);
				// request.setAttribute("image", images);

				// 使用json封装对象的时候, 对于日期格式会报错，使用以下方法解决
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(java.util.Date.class,
						new JsonValueProcessor() {
							private SimpleDateFormat sd = new SimpleDateFormat(
									"yyyy-MM-dd");

							public Object processObjectValue(String key,
									Object value, JsonConfig jsonConfig) {
								return value == null ? "" : sd.format(value);
							}

							public Object processArrayValue(Object value,
									JsonConfig jsonConfig) {
								return null;
							}
						});
				// 将从数据库中获取到的imgs封装在JSONArray中以回传给ajax
				JSONArray ja = JSONArray.fromObject(images, jsonConfig);
				response.getWriter().write(ja.toString());
			}

			// 审核通过
			if (status.equals("Pass")) {
				ImageDao imageDao = new ImageDaoImpl();
				int id = Integer.parseInt(request.getParameter("id").toString());
				String str = "";
				if (imageDao.Pass(id)!=0) {
					str = "[{\"success\":\"true\"}]";
				} else {
					str = "[{\"errorMsg\":\"审核失败！\"}]";
				}
				response.getWriter().write(str);
			}

			//查看详细
			if (status.equals("queryImage")) {
				ImageDao imageDao = new ImageDaoImpl();
				int id = Integer.parseInt(request.getParameter("id").toString());
				Image im = imageDao.queryImageByImageId(id);
				request.setAttribute("image", im);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("BackXiangxi.jsp");
				dispatcher.forward(request, response);
			}

			if (status.equals("deleteImage")) {
				ImageDao imageDao = new ImageDaoImpl();
				int id = Integer.parseInt(request.getParameter("id").toString());

				String str = "";
				try {
					if (imageDao.deleteImageById(id)) {
						str = "[{\"success\":\"true\"}]";
					} else {
						str = "[{\"errorMsg\":\"删除失败！\"}]";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().write(str);
			}
		}
	}
}