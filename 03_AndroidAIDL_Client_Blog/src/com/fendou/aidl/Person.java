package com.fendou.aidl;

import android.os.Parcel;
import android.os.Parcelable;

//<1>实现Parceable接口
public class Person implements Parcelable {

	private int id;
	private String userName;
	private double height;
	
	public Person(){
		
	}
	//创建私有化的构造函数，调用readFromParcel()方法
	private Person(Parcel in)
	{
		readFromParcel(in);
	}
	//(3)必须提供一个名为CREATOR的static final属性 该属性需要实现android.os.Parcelable.Creator<T>接口 
	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Person(source);
		}

		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 注意：writeToParcel()和readFromParcel()对变量的读写顺序需一致
	 */
	//(2)将Person对象的状态写入Parcel中
	public void writeToParcel(Parcel out, int arg1) {
		// TODO Auto-generated method stub
		out.writeInt(id);
		out.writeString(userName);
		out.writeDouble(height);

	}
	//(2)从Parcel中读取Person对象的状态
	public void readFromParcel(Parcel in){
		id = in.readInt();
		userName = in.readString();
		height = in.readDouble();
	}
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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
