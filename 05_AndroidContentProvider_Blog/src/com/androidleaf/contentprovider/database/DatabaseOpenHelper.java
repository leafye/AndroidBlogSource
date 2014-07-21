package com.androidleaf.contentprovider.database;

import com.androidleaf.contentprovider.myprovider.MyContentProviderMetaData;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * 该类是SQLiteDatabase的帮助类，主要管理数据库的创建和版本的更新
 * @author AndroidLeaf
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private static DatabaseOpenHelper mDatabaseOpenHelper;
	/**
	 * 数据库版本号
	 */
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABSE_NAME = "manager.db";
	
	/**
	 * 初始化数据库信息
	 * @param context 应用程序上下文
	 * @param name 数据库名称
	 * @param factory cursor工厂对象
	 * @param version 数据库版本号
	 * @param errorHandler 数据库错误处理对象
	 */
	public DatabaseOpenHelper(Context context) {
		super(context, DATABSE_NAME, null,
				DATABASE_VERSION, new DatabaseErrorHandler() {
			
			@Override
			public void onCorruption(SQLiteDatabase dbObj) {
				// TODO Auto-generated method stub
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 使用单例模式，获取数据库唯一实例
	 * @param mContext 应用程序上下文
	 * @return mDatabaseOpenHelper 该对象用于获取SQLiteDatabase实例
	 */
	public synchronized static DatabaseOpenHelper getDatabaseOpenHelper(Context mContext){
		if(mDatabaseOpenHelper == null){
			mDatabaseOpenHelper = new DatabaseOpenHelper(mContext);
		}
		return mDatabaseOpenHelper;
	} 

	/**
	 * 创建数据库时调用，一般执行创建表的操作
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建数据库表
		db.execSQL("CREATE TABLE "
				+ MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME + " (" + "" 
				+  MyContentProviderMetaData.ProviderMetaData.Persons._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME + " VARCHAR(20) NOT NULL," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL + " VARCHAR(20) NOT NULL," + ""
				+  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT + " FLOAT(10) NOT NULL" + ")");
		
		//插入20条测试数据
		for(int i = 0;i < 20;i++){
			ContentValues mContentValues = new ContentValues();
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME, "Steve.P.Jobs "+ i);
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL, "45936455"+ i +"@qq.com");
			mContentValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT, String.valueOf(170 + i));
			db.insertOrThrow(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME,
					MyContentProviderMetaData.ProviderMetaData.Persons._ID, mContentValues);
		}
	}

	/**
	 * 当数据库需要修改的时系统会自动调用此方法。一般我们在这个方法里边删除数据库表，
	 * 并建立新的数据库表，当然是否还需要其它操作，完全取决于应用的需求
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " +  MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME);
		onCreate(db);
	}
}
