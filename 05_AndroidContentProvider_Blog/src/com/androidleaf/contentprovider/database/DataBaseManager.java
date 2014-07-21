package com.androidleaf.contentprovider.database;

import com.androidleaf.contentprovider.myprovider.MyContentProviderMetaData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DataBaseManager implements IDatabaseManager{

	private static final String TAG = "sqlite_log";
	private static final String PERSON_TABLE = MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_TABLE_NAME;
	private static final String PERSON_ID = MyContentProviderMetaData.ProviderMetaData.Persons._ID;
	
	private SQLiteDatabase mSqLiteDatabase;
	DatabaseOpenHelper mDatabaseOpenHelper;
	
	public DataBaseManager(Context mContext){
		mDatabaseOpenHelper = DatabaseOpenHelper.getDatabaseOpenHelper(mContext);
		//获取SQLiteDatabase对象，创建或打开数据库
		mSqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
	}
	
	/**
	 * 插入单条数据操作
	 * @param mContentValues 插入的数据集合
	 * @return long 插入数据的行号
	 */
	@Override
	public long insert(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			long rowId = mSqLiteDatabase.insertOrThrow(PERSON_TABLE,PERSON_ID, mContentValues);
			mSqLiteDatabase.setTransactionSuccessful();
			return rowId;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The insert operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return -1;
	}

	/**
	 * 更新单条数据操作
	 * @param mContentValues 需要更新的数据集合
	 * @return int 更新数据的数量
	 */
	@Override
	public int update(ContentValues mContentValues) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.update(PERSON_TABLE, mContentValues, PERSON_ID +"= ?",
					new String[]{String.valueOf(mContentValues.get(PERSON_ID))});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The update operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	
	/**
	 * 更新多条数据操作
	 * @param mContentValues 需要更新的数据集合
	 * @param whereClause
	 * @param whereArgs
	 * @return int 更新的数据数量
	 */
	public int update(ContentValues mContentValues,String whereClause,String[] whereArgs) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.update(PERSON_TABLE, mContentValues, whereClause, whereArgs);
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The update operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}

	/**
	 * 删除单条数据操作
	 * @param mContentValues 需要更删除的数据选项ID
	 * @return int 删除的数据数量
	 */
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.delete(PERSON_TABLE, PERSON_ID +"= ?", new String[]{String.valueOf(id)});
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The delete operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	/**
	 * 删除多条数据操作
	 * @param whereClause
	 * @param whereArgs
	 * @return int 删除的数据数量
	 */
	public int delete(String whereClause,String[] whereArgs) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.beginTransaction();
		try {
			int rows = mSqLiteDatabase.delete(PERSON_TABLE, whereClause, whereArgs);
			mSqLiteDatabase.setTransactionSuccessful();
			return rows;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, "The delete operation failed");
		}finally{
			mSqLiteDatabase.endTransaction();
		}
		return 0;
	}
	
	/**
	 * 使用标准SQL语句查询数据列表
	 * @param <T>
	 * @param sql 标准SQL语句
	 * @return mList 查询后的数据列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Cursor query(String sql) {
		return mSqLiteDatabase.rawQuery(sql, null);
	}
	
	/**
	 * 根据ID查询数据
	 * @param <T>
	 * @param id 需要查询的数据ID
	 * @return T 查询后获取到的对象
	 */
	@Override
	public Cursor query(int id) {
		// TODO Auto-generated method stub
		return mSqLiteDatabase.query(PERSON_TABLE, null, PERSON_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
	}
	
	/**
	 * 执行一些较为复杂的CRUD操作
	 */
	@Override
	public void execSQL(String sql) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 执行对数据库中数据的操作后关闭数据库
	 */
	@Override
	public void closeDB() {
		// TODO Auto-generated method stub
		mSqLiteDatabase.close();
	}
}
