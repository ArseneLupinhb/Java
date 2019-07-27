package com.adou.syds.domain;

import java.util.Date;

public class Praise {

	private int id;
	private String ipAddress;
	private int image_id;
	private Date praise_time;
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public Date getPraise_time() {
		return praise_time;
	}
	public void setPraise_time(Date praise_time) {
		this.praise_time = praise_time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Praise [id=" + id + ", ipAddress=" + ipAddress + ", image_id="
				+ image_id + ", praise_time=" + praise_time + ", userName="
				+ userName + "]";
	}
	
}
