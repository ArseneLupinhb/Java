package com.adou.syds.domain;

import java.util.Date;

public class Comment {

	private int id;
	private String comment;
	private int image_id;
	private Date comment_time;
	private int isPass;
	private String ipAdress;
	private String note;
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public int getIsPass() {
		return isPass;
	}
	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", image_id="
				+ image_id + ", comment_time=" + comment_time + ", isPass="
				+ isPass + ", ipAdress=" + ipAdress + ", note=" + note
				+ ", userName=" + userName + "]";
	}
	
}
