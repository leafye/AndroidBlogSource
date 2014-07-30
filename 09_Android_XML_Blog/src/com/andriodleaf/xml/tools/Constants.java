package com.andriodleaf.xml.tools;

/**
 * 网络请求的Url地址及一些常量
 * @author AndroidLeaf
 */
public class Constants {

	//基路径
	public static final String BASE_PATH = "http://10.0.2.2:8080/09_Android_XMLServer_Blog/";
	//person.xml网络请求路径
	public static final String XML_PATH = BASE_PATH + "xmlfile/person.xml";
	
	//使用SAX解析的标签类型
	public static final int REQUEST_SAX_TYPE = 0;
	//使用DOM解析的标签类型
	public static final int REQUEST_DOM_TYPE = 1;
	//使用PULL解析的标签类型
	public static final int REQUEST_PULL_TYPE = 2;
	
	//请求person.xml文件标签
	public static final int TYPE_STR = 1;
	//请求图片文件标签
	public static final int TYPE_STREAM = 2;
	
}
