package com.adou.syds.domain;

public class Unit {

	private int id;
	private String unitName;
	private int isInstitue;
	private String note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int isInstitue() {
		return isInstitue;
	}
	public void setInstitue(int isInstitue) {
		this.isInstitue = isInstitue;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Unit [id=" + id + ", unitName=" + unitName + ", isInstitue="
				+ isInstitue + ", note=" + note + "]";
	}
	
}
