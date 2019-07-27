package com.adou.syds.domain;

public class Album {

	private int id;
	private String albumName;
	private String description;
	private int user_id;
	private String note;
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
				+ ", note=" + note + "]"+"\n";
	}
	
}
