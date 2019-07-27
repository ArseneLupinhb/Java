package com.adou.syds.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("user");
		if(user == null) {
			req.getRequestDispatcher("/msg.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);           //放行
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
