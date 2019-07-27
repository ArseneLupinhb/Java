package com.adou.syds.web.servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adou.syds.domain.Praise;
import com.adou.syds.service.PraiseService;
import com.adou.syds.service.impl.PraiseServiceImpl;


public class PraiseServlet extends BaseServlet {
	
	PraiseService praiseService = new PraiseServiceImpl();
	Praise praise = new Praise();
	
	public String praise(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		 praise = (Praise)req.getAttribute("praise");
		 boolean isPraise =  praiseService.praise(praise);
		 System.err.println("isPrasie:--"+isPraise);
		 return null;
	}
	
	
	public String cancelPraise(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		 boolean isCancelPraise =  praiseService.cancelPraise(praise);
		 System.err.println("isCancelPraise:--"+isCancelPraise);
		 return null;
	}

}
