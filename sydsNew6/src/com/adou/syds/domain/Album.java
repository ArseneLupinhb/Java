package com.adou.syds.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable{

	private int id;
	private String albumName;
	private String description;
	private int user_id;
	private String note;
	private List<Image> images;
	private String albumImage_url;
	private String userName;
	private Date createTime;
	private String phone;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAlbumImage_url() {
		return albumImage_url;
	}
	public void setAlbumImage_url(String albumImage_url) {
		this.albumImage_url = albumImage_url;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", albumName=" + albumName
				+ ", description=" + description + ", user_id=" + user_id
				+ ", note=" + note + ", images=" + images + ", albumImage_url="
				+ albumImage_url + ", userName=" + userName + ", createTime="
				+ createTime + ", phone=" + phone + "]"+"\n";
	}
	
}
