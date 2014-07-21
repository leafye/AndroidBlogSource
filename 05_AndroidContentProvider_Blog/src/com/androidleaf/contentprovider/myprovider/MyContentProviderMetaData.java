package com.androidleaf.contentprovider.myprovider;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public class MyContentProviderMetaData {
	/**
	 * 定义基本授权Authority
	 */
	public static final String AUTHORITY = "com.androidleaf.MyContentProvider";
	public static final String URI_AUTHORITY = "content://"+ AUTHORITY +"/";
	public static final int COLLECTION_INDICATOR = 1;
	public static final int SINGLE_INDICATOR = 2;
	
	public static final class ProviderMetaData{
		
		public static final class Persons implements BaseColumns{
			
			/**
			 * 表的名称
			 */
			public static final String PERSON_TABLE_NAME = "person";
			public static final String PERSON_FIELD_PERSONNAME = "name";
			public static final String PERSON_FIELD_EMAIL = "email";
			public static final String PERSON_FIELD_HEIGHT = "height";
			
			public static UriMatcher mUriMatcher = null;
			
			/**
			 * 创建Person相关的CONTENT_URI,PERSONS_URI用于匹配查询多条Person数据集，
			 * PERSON_URI用于匹配查询单条Person的数据
			 */
			public static final Uri PERSONS_URI = getContentUri("persons");
			public static final Uri PERSON_URI = getContentUri("persons/#");
			
			static{
				/**
				 * 实例化UriMatcher对象，注册需要匹配的URI
				 */
				mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
				mUriMatcher.addURI(AUTHORITY, "persons", COLLECTION_INDICATOR);
				mUriMatcher.addURI(AUTHORITY, "persons/#", SINGLE_INDICATOR);
			}
			
			public static Uri getContentUri(String volumeName){
				return Uri.parse(URI_AUTHORITY + volumeName);
			}
		}
	}
}
