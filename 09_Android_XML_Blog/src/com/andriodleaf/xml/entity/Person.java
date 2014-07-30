package com.andriodleaf.xml.entity;

public class Person {
	
	private int id;
	private String userName;
	private float height;
	private String imageUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", height="
				+ height + ", imageUrl=" + imageUrl + "]";
	}
}
