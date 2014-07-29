package com.androidleaf.jsonserver.tools;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

public class JsonTools {

	public static String createJsonString(String key, Object value) {
		/**
		 * 使用Json原生库解析数据时，使用该方法返回Json字符串
		 */
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
		
		/**
		 * 使用Gson,Jackson和FastJson解析数据时，使用该方法返回Json字符串
		 */
		
		
		/*Gson mGson = new GsonBuilder().registerTypeAdapter(java.util.Date.class, new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();*/
		
		/*Gson mGson = new Gson();
		return mGson.toJson(value);*/
		
	}
}
