package com.adou.syds.web.filter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest req;
	
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.req = request;
	}

	public String getParameter(String name) {
		String value = req.getParameter(name);
		/*
		if(value != null){
			try {
				value = new String(value.trim().getBytes("iso-8859-1"), "utf-8");
				System.err.println(value);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}*/
		
		
		return value;
	}
}
