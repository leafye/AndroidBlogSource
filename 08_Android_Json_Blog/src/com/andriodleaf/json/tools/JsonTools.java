package com.andriodleaf.json.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.andriodleaf.json.entity.Person;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class JsonTools {

	/**-----------------Json数据解析----------------------------*/
	public static Person JsonToPerson(String key,String jsonStr)
	{
		Person mPerson = new Person();
		try {
			JSONObject mJsonObject = new JSONObject(jsonStr);
			JSONObject mJsonObject2 = mJsonObject.getJSONObject(key);

			mPerson.setId(mJsonObject2.getInt("id"));
			mPerson.setUserName(mJsonObject2.getString("userName"));
			mPerson.setPassword(mJsonObject2.getString("password"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mPerson;
	}
	
	public static ArrayList<Person> JsonToListPerson(String key,String jsonStr)
	{
		
		ArrayList<Person> mPersons = new ArrayList<Person>();
		try {
			JSONObject mJsonObject = new JSONObject(jsonStr);
			JSONArray mArray = mJsonObject.getJSONArray(key);
			for(int i = 0 ; i < mArray.length() ; i++)
			{
				Person  mPerson = new Person();
				JSONObject mJsonObject2 = mArray.getJSONObject(i);
				mPerson.setId(mJsonObject2.getInt("id"));
				mPerson.setUserName(mJsonObject2.getString("userName"));
				mPerson.setPassword(mJsonObject2.getString("password"));
				mPersons.add(mPerson);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mPersons;
	}
	
	public static ArrayList<String> jsonToListString(String key,String jsonStr)
	{
		ArrayList<String> mStrings = new ArrayList<String>();
		JSONObject mJsonObject;
		try {
			mJsonObject = new JSONObject(jsonStr);
			JSONArray mArray = mJsonObject.getJSONArray(key);
			for(int i = 0 ;i < mArray.length();i++)
			{
				mStrings.add(mArray.getString(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return mStrings;
	}
	
	public static ArrayList<Map<String, Person>> jsonToListMapPerson(String key,String jsonStr)
	{
		ArrayList<Map<String, Person>> listMaps = new ArrayList<Map<String,Person>>();
		try {
			JSONObject mJsonObject = new JSONObject(jsonStr);
			JSONArray mJsonArray = mJsonObject.getJSONArray(key);
			for(int i = 0 ;i < mJsonArray.length();i++)
			{
				JSONObject mJsonObject2 = mJsonArray.getJSONObject(i);
				Map<String, Person> maps = new HashMap<String, Person>();
				Iterator<String> mIterator = mJsonObject2.keys();
				ArrayList<String> mListKey = new ArrayList<String>();
				ArrayList<String> mListValue = new ArrayList<String>();
				while(mIterator.hasNext())
				{
					String mapKey = mIterator.next();
					Object mObject = mJsonObject2.get(mapKey);
					System.out.println(mObject.toString());
					mListValue.add("{"+ mapKey + ":" + mObject.toString() + "}");
					mListKey.add(mapKey);
				}
				for(int j = 0; j < mListKey.size(); j++)
				{
					JSONObject mJsonObject3 = new JSONObject(mListValue.get(j));
					JSONObject mJsonObject4 = mJsonObject3.getJSONObject(mListKey.get(j));
					Person mPerson = new Person();
					mPerson.setId(mJsonObject4.getInt("id"));
					mPerson.setUserName(mJsonObject4.getString("userName"));
					mPerson.setPassword(mJsonObject4.getString("password"));
					maps.put(mListKey.get(j), mPerson);
				}
				listMaps.add(maps);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMaps;
	}
	
	/**-----------------Gson数据解析----------------------------*/
	
	/**
	 * 将Json字符串解析成对应的Java对象
	 * @param jsonStr 需要解析的Json字符串
	 * @param mClass 需要解析成的Java对象类型
	 * @return 解析后的Java对象实例
	 */
	public static <T> T gsonToObject(String jsonStr,Class<T> mClass)
	{
		Gson mGson = new Gson();
		T mt = mGson.fromJson(jsonStr, mClass);
		return mt;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<T>集合
	 * @param jsonStr 需要解析的Json字符串
	 * @param mType 需要解析成的Java对象类型
	 * @return mlList 解析后的ArrayList<T>集合
	 */
	public static <T> ArrayList<T> gsonToListObjectOrString(String jsonStr,Type mType)
	{
		/*Gson mGsonDate = new GsonBuilder().registerTypeAdapter(java.util.Date.class, new UtilDateDeserializer())
				.setDateFormat(java.text.DateFormat.LONG).create();*/
		Gson mGson = new Gson();
		ArrayList<T> mlList = mGson.fromJson(jsonStr,mType);
		return mlList;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<Map<String,T>>集合
	 * @param jsonStr 需要解析的Json字符串
	 * @param mType 需要解析成的Java对象类型
	 * @return mapsList 解析后的ArrayList<Map<String,T>>集合
	 */
	public static <T> ArrayList<Map<String, T>> gsonGetListMapObject(String jsonStr,Type mType)
	{
		Gson mGson = new Gson();
		ArrayList<Map<String, T>> mapsList = mGson.fromJson(jsonStr, mType);
		return mapsList;
	}
	
	/**
	 * 获取需要转换的List<T>类型
	 * @param mClass 
	 * @return mType
	 */
	public static <T> Type getListType(Class<T> mClass){
		Type mType = null;
		if(mClass == Person.class){
			mType = new TypeToken<List<Person>>(){}.getType();
		}else if(mClass == String.class){
			mType = new TypeToken<List<String>>(){}.getType(); 
		}
		return mType;
	}
	
	/**
	 * 获取需要转换的List<Map<String,T>>类型
	 * @param mClass
	 * @return mType
	 */
	public static <T> Type getListMapType(Class<T> mClass){
		Type mType = null;
		if(mClass == Person.class){
			mType = new TypeToken<List<Map<String, Person>>>(){}.getType();
		}else if(mClass == String.class){
			mType = new TypeToken<List<Map<String, String>>>(){}.getType(); 
		}
		return mType;
	}
	
	/**-----------------Jackson数据解析----------------------------*/
	private static ObjectMapper mObjectMapper;
	private static JsonGenerator mJsonGenerator;
	private static ByteArrayOutputStream mOutputStream;
	public JsonTools()
	{
		mOutputStream = new ByteArrayOutputStream();
		//实例化ObjectMapper对象
		mObjectMapper = new ObjectMapper();
		try {
			//实例化JsonGenerator对象
			mJsonGenerator = mObjectMapper.getFactory().createGenerator(mOutputStream, JsonEncoding.UTF8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将Json字符串解析成对应的Java对象
	 * @param jsonStr 需要解析的Json字符串
	 * @param mClass 需要解析成的Java对象类型
	 * @return mt 解析后的Java对象
	 */
	public <T> T JacksonToObject(String jsonStr,Class<T> mClass)
	{
		T mt = null;
		try {
			mt = mObjectMapper.readValue(jsonStr, mClass);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mt;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<T>对象
	 * @param jsonStr 需要解析的Json字符串
	 * @param mTypeReference 需要解析成的Java对象类型引用
	 * @return 解析后的ArrayList<T>集合
	 */
	public <T> ArrayList<T> JacksonToListObjectOrString(String jsonStr,com.fasterxml.jackson.core.type.TypeReference<T> mTypeReference)
	{
		ArrayList<T> mList = null;
		try {
			mList = mObjectMapper.readValue(jsonStr, mTypeReference);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<Map<String,T>>对象
	 * @param jsonStr 需要解析的Json字符串
	 * @param mTypeReference 需要解析成的Java对象类型引用
	 * @return 解析后的ArrayList<Map<String, T>>集合
	 */
	public <T> ArrayList<Map<String, T>> JacksonToListMapObject(String jsonStr,com.fasterxml.jackson.core.type.TypeReference<T> mTypeReference)
	{
		ArrayList<Map<String, T>> mapsList = null;
		try {
			mapsList = mObjectMapper.readValue(jsonStr, mTypeReference);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapsList;
	}
	
	/**
	 * 获取需要转换的List<T>类型
	 * @param mClass
	 * @return
	 */
	public static <T> com.fasterxml.jackson.core.type.TypeReference<T> getListTypeReference(Class<T> mClass){
		com.fasterxml.jackson.core.type.TypeReference mTypeReference = null;
		if(mClass == Person.class){
			mTypeReference = new com.fasterxml.jackson.core.type.TypeReference<List<Person>>() {
			};
		}else if(mClass == String.class){
			mTypeReference = new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {
			};
		}
		return mTypeReference;
	}
	
	/**
	 * 获取需要转换的List<Map<String,T>>类型
	 * @param mClass
	 * @return
	 */
	public static <T> com.fasterxml.jackson.core.type.TypeReference<T> getListMapTypeReference(Class<T> mClass){
		com.fasterxml.jackson.core.type.TypeReference mTypeReference = null;
		if(mClass == Person.class){
			mTypeReference = new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Person>>>() {
			};
		}else if(mClass == String.class){
			mTypeReference = new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Person>>>() {
			}; 
		}
		return mTypeReference;
	}
	
	/**-----------------FastJson数据解析----------------------------*/
	
	/**
	 * 将Json字符串解析成对应的Java对象
	 * @param jsonStr 需要解析的Json字符串
	 * @param mClass 需要解析成的Java对象类型
	 * @return 解析后的Java对象实例
	 */
	public static <T> T fastJsonToObject(String jsonStr,Class<T> mClass)
	{
		T mt = JSON.parseObject(jsonStr,mClass);
		return mt;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<T>集合
	 * @param jsonStr 需要解析的Json字符串
	 * @param mType 需要解析成的Java对象类型
	 * @return mlList 解析后的ArrayList<T>集合
	 */
	public static <T> ArrayList<T> fastJsonToListObjectOrString(String jsonStr,Class<T> mClass)
	{
		ArrayList<T> mList = (ArrayList<T>) JSON.parseArray(jsonStr, mClass);
		return mList;
	}
	
	/**
	 * 将Json字符串解析成对应的ArrayList<Map<String,T>>集合
	 * @param jsonStr 需要解析的Json字符串
	 * @param mType 需要解析成的Java对象类型
	 * @return mapsList 解析后的ArrayList<Map<String,T>>集合
	 */
	public static <T> ArrayList<Map<String, T>> fastJsonGetListMapObject(String jsonStr,TypeReference<T> mTypeReference)
	{
		ArrayList<Map<String, T>> mapsList = (ArrayList<Map<String, T>>) JSON.parseObject(jsonStr, mTypeReference);
		return mapsList;
	}
	
	/**
	 * 获取需要转换的List<Map<String,T>>类型
	 * @param mClass
	 * @return mType
	 */
	public static <T> TypeReference fastJsonGetetListMapTypeReference(Class<T> mClass){
		TypeReference mTypeReference = null;
		if(mClass == Person.class){
			mTypeReference = new TypeReference<ArrayList<Map<String, Person>>>(){};
		}else if(mClass == String.class){
			mTypeReference = new TypeReference<ArrayList<Map<String, String>>>(){};
		}
		return mTypeReference;
	}
	
}
