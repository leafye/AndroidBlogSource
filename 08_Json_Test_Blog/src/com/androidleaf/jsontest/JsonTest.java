package com.androidleaf.jsontest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

/**
 * Json中JSONObject、JSONArray、JSONStringer和JSONTokener的使用实例
 * @author AndroidLeaf
 *
 */
public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonTest mJsonTest = new JsonTest();
		//mJsonTest.jsonObjectTest();
		//mJsonTest.jsonArrayTest();
		//mJsonTest.jsonStringTest();
		mJsonTest.jsonTokenerTest();
	}
	
	/**
	 * JsonObject对象的使用
	 */
	public void jsonObjectTest(){
		Person mPerson = testDataPerson();
		
		/**
		 * 将Person对象转换成Json字符串
		 */
		JSONObject mJsonObject = new JSONObject();
		try {
			//添加值到mJsonObject对象中
			mJsonObject.put("id", mPerson.getId());
			mJsonObject.put("userName", mPerson.getUserName());
			mJsonObject.put("password", mPerson.getPassword());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mJsonObject.toString());
		/**打印的内容为：{"id":1,"userName":"AndroidLeaf","password":"123"} */
		
		/**
		 * 将Json字符串转换成对应的Person对象
		 */
		Person mPerson2 = new Person();
		JSONObject mJsonObject2;
		try {
			 mJsonObject2 = new JSONObject(mJsonObject.toString());
			 mPerson2.setId(mJsonObject2.getInt("id"));
			 mPerson2.setUserName(mJsonObject2.getString("userName"));
			 mPerson2.setPassword(mJsonObject2.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mPerson2.toString());
		/**打印的内容为：Person [id=1, userName=AndroidLeaf, password=123]*/
	}
	
	/**
	 * JsonArray对象的使用
	 */
	public void jsonArrayTest(){
		
		ArrayList<Person> mList = testDataPersons();
		/**
		 * 将Person集合对象转换成Json字符串
		 */
		JSONArray mJsonArray = new JSONArray();
		for(int i = 0; i < mList.size();i++){
			Person mPerson = mList.get(i);
			try{
				JSONObject mJsonObject = new JSONObject();
				mJsonObject.put("id", mPerson.getId());
				mJsonObject.put("userName", mPerson.getUserName());
				mJsonObject.put("password", mPerson.getPassword());
				mJsonArray.put(mJsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(mJsonArray.toString());
		/**打印的内容为：[{"id":0,"userName":"AndroidLeaf 0","password":"1230"},
		 * {"id":1,"userName":"AndroidLeaf 1","password":"1231"},
		 * {"id":2,"userName":"AndroidLeaf 2","password":"1232"},
		 * {"id":3,"userName":"AndroidLeaf 3","password":"1233"},
		 * {"id":4,"userName":"AndroidLeaf 4","password":"1234"}]*/
		
		/**
		 * 将Json字符串转换成Person集合对象
		 */
		ArrayList<Person> mPersons = new ArrayList<Person>();
		JSONArray mJsonArray2;
		try {
			mJsonArray2 = new JSONArray(mJsonArray.toString());
			for(int j = 0;j < mJsonArray2.length();j++){
				Person mPerson = new Person();
				JSONObject mJsonObject = mJsonArray2.getJSONObject(j);
				mPerson.setId(mJsonObject.getInt("id"));
				mPerson.setUserName(mJsonObject.getString("userName"));
				mPerson.setPassword(mJsonObject.getString("password"));
				mPersons.add(mPerson);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mPersons.toString());
		/**打印的内容为：
		 * [Person [id=0, userName=AndroidLeaf 0, password=1230], 
		 *  Person [id=1, userName=AndroidLeaf 1, password=1231],
		 *  Person [id=2, userName=AndroidLeaf 2, password=1232], 
		 *  Person [id=3, userName=AndroidLeaf 3, password=1233], 
		 *  Person [id=4, userName=AndroidLeaf 4, password=1234]]*/
	}
	
	/**
	 * JsonString对象的使用
	 */
	public String jsonStringTest(){
		
		/**
		 * 将Person对象构建成Json字符串文本
		 */
		JSONStringer mJsonStringer = new JSONStringer();
		Person mPerson = testDataPerson();
		try {
			//object()和endObject()配对出现，用于描述对象开始解析
			mJsonStringer.object();
			mJsonStringer.key("id");
			mJsonStringer.value(mPerson.getId());
			mJsonStringer.key("userName");
			mJsonStringer.value(mPerson.getUserName());
			mJsonStringer.key("password");
			mJsonStringer.value(mPerson.getPassword());
			mJsonStringer.endObject();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(mJsonStringer.toString());
		/**打印的内容为： {"id":1,"userName":"AndroidLeaf","password":"123"}*/
		
		
		/**
		 * 将Person集合对象构建成Json字符串文本
		 * 
		 */
		
		JSONStringer mJsonStringer2 = new JSONStringer();
		ArrayList<Person> mList = testDataPersons();
		
		try {
			//array()和endArray()方法配对出现，用于描述数组对象开始解析
			mJsonStringer2.array();
			for(int i = 0 ;i < mList.size();i++){
				//将Person对象解析成Json字符串并包装成JSONStringer添加到数组集合中
				JSONStringer mJsonStringer3 = new JSONStringer();
				mJsonStringer3.object();
				mJsonStringer3.key("id");
				mJsonStringer3.value(mList.get(i).getId());
				mJsonStringer3.key("userName");
				mJsonStringer3.value(mList.get(i).getUserName());
				mJsonStringer3.key("password");
				mJsonStringer3.value(mList.get(i).getPassword());
				mJsonStringer3.endObject();
				mJsonStringer2.value(mJsonStringer3);
			}
			mJsonStringer2.endArray();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(mJsonStringer2.toString());
		return mJsonStringer2.toString();
		/**打印的内容为：["{\"id\":0,\"userName\":\"AndroidLeaf 0\",\"password\":\"1230\"}",
		 * "{\"id\":1,\"userName\":\"AndroidLeaf 1\",\"password\":\"1231\"}",
		 * "{\"id\":2,\"userName\":\"AndroidLeaf 2\",\"password\":\"1232\"}",
		 * "{\"id\":3,\"userName\":\"AndroidLeaf 3\",\"password\":\"1233\"}",
		 * "{\"id\":4,\"userName\":\"AndroidLeaf 4\",\"password\":\"1234\"}"]*/
		
	}
	
	/**
	 * JsonTokener对象的使用
	 */
	public void jsonTokenerTest(){
		
		/**jsonStr的内容为：["{\"id\":0,\"userName\":\"AndroidLeaf 0\",\"password\":\"1230\"}",
		 * "{\"id\":1,\"userName\":\"AndroidLeaf 1\",\"password\":\"1231\"}",
		 * "{\"id\":2,\"userName\":\"AndroidLeaf 2\",\"password\":\"1232\"}",
		 * "{\"id\":3,\"userName\":\"AndroidLeaf 3\",\"password\":\"1233\"}",
		 * "{\"id\":4,\"userName\":\"AndroidLeaf 4\",\"password\":\"1234\"}"]*/
		String jsonStr = initTokenerData();
		JSONTokener mJsonTokener = new JSONTokener(jsonStr);
		try {
			//跳转并读取到下一个字符
			System.out.println(mJsonTokener.next());
			//跳转并读取一个值
			System.out.println(mJsonTokener.nextValue());
			System.out.println(mJsonTokener.next());
			System.out.println(mJsonTokener.nextValue());
			//返回到上一个字符
			mJsonTokener.back();
			System.out.println(mJsonTokener.next());
			//跳转到下一个包含"f"字符的位置，并获取起初位置到跳转后位置之间的字符串
			System.out.println(mJsonTokener.nextTo('f'));
			//mJsonTokener.next(c);
			//mJsonTokener.next(arg0);
			//mJsonTokener.skipTo(arg0);
			//mJsonTokener.nextClean();
			
			/**打印的内容为：[
				*		{"id":0,"userName":"AndroidLeaf 0","password":"1230"}
				*		,
				*		{"id":1,"userName":"AndroidLeaf 1","password":"1231"}
				*		"
				*		,"{\"id\":2,\"userName\":\"AndroidLea*/
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Person testDataPerson(){
		Person mPerson = new Person();
		mPerson.setId(1);
		mPerson.setUserName("AndroidLeaf");
		mPerson.setPassword("123");
		return mPerson;
	}
	
	public ArrayList<Person> testDataPersons(){
		ArrayList<Person> mList = new ArrayList<Person>();
		for(int i = 0; i < 5; i++){
			Person mPerson = new Person();
			mPerson.setId(i);
			mPerson.setUserName("AndroidLeaf " + i);
			mPerson.setPassword("123" + i);
			mList.add(mPerson);
		}
		return mList;
	}
	
	public String initTokenerData(){
		return jsonStringTest();
	}
}
