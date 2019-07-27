package com.adou.syds.domain;

import java.util.Date;

public class Image {

	private int id;
	private String title;
	private int user_id;
	private String place;
	private String image_url;
	private String image_backName;
	private String introduction;
	private Date publish_time;
	private int type;
	private int rank;
	private int isRecommend;
	private int isPass;
	private int isDelete;
	private String note;
	private int album_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_backName() {
		return image_backName;
	}
	public void setImage_backName(String image_backName) {
		this.image_backName = image_backName;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public int getIsPass() {
		return isPass;
	}
	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	@Override
	public String toString() {
		return " Image [id=" + id + ", title=" + title + ", user_id=" + user_id
				+ ", place=" + place + ", iamge_url=" + image_url
				+ ", iamge_backName=" + image_backName + ", introduction="
				+ introduction + ", publish_time=" + publish_time + ", type="
				+ type + ", rank=" + rank + ", isRecommend=" + isRecommend
				+ ", isPass=" + isPass + ", isDelete=" + isDelete + ", note="
				+ note + ", album_id=" + album_id + "]"+"\n";
	}
	
}
