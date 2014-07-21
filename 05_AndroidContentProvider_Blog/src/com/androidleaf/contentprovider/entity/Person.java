package com.androidleaf.contentprovider.entity;

public class Person {
	private int id;
	private String name;
	private String email;
	private float height;
	
	public Person(){}
	
	public Person(int id,String name,String email,float height){
		this.id = id;
		this.name = name;
		this.email = email;
		this.height = height;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
}
