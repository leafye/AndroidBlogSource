package com.androidleaf.contentprovider.myprovider;

import com.androidleaf.contentprovider.database.DataBaseManager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * 自定义Content Provider，实现对SQLite数据库数据操作的封装
 * @author AndroidLeaf
 */
public class MyContentProvider extends ContentProvider {
	
	//获取UriMatcher对象
	UriMatcher mUriMatcher = MyContentProviderMetaData.ProviderMetaData.Persons.mUriMatcher;
	DataBaseManager mDataBaseManager;
	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mDataBaseManager = new DataBaseManager(getContext());
		return mDataBaseManager == null?false:true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor mCursor = null;
		switch (mUriMatcher.match(uri)) {
		case MyContentProviderMetaData.SINGLE_INDICATOR:
			//得到最后"/"字符串的数值ID
			String rowId = uri.getLastPathSegment();
			mCursor = mDataBaseManager.query(Integer.parseInt(rowId));
			break;
		case MyContentProviderMetaData.COLLECTION_INDICATOR:
			//查询所有数据
			mCursor = mDataBaseManager.query("select * from person");
			break;
		default:
			throw new IllegalArgumentException("Unkonw Uri "+ uri);
		}
		return mCursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		if(mUriMatcher.match(uri) != MyContentProviderMetaData.COLLECTION_INDICATOR){
			throw new IllegalArgumentException("Unkonw Uri "+ uri);
		}
		long rowId = mDataBaseManager.insert(values);
		 if( rowId!= -1){
			 Uri retUri = ContentUris.withAppendedId(MyContentProviderMetaData.ProviderMetaData.Persons.PERSONS_URI, rowId);
			 notifySetChanged(uri);
			 return retUri;
		 }
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count = 0;
		//UriMatcher.match(uri)方法用于匹配uri，匹配成功后返回注册时设置的code
		switch (mUriMatcher.match(uri)) {
		case MyContentProviderMetaData.SINGLE_INDICATOR:
			//得到最后"/"字符串的数值ID
			String rowId = uri.getLastPathSegment();
			count = mDataBaseManager.delete(Integer.parseInt(rowId));
			break;
		case MyContentProviderMetaData.COLLECTION_INDICATOR:
			count = mDataBaseManager.delete(selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkonw Uri "+ uri);
		}
		
		if(count > 0){
			notifySetChanged(uri);
		}
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count = 0;
		switch (mUriMatcher.match(uri)) {
		case MyContentProviderMetaData.SINGLE_INDICATOR:
			//得到最后"/"字符串的数值ID
			String rowId = uri.getLastPathSegment();
			values.put(MyContentProviderMetaData.ProviderMetaData.Persons._ID, rowId);
			count = mDataBaseManager.update(values);
			break;
		case MyContentProviderMetaData.COLLECTION_INDICATOR:
			count = mDataBaseManager.update(values, selection, selectionArgs);
		default:
			throw new IllegalArgumentException("Unkonw Uri "+ uri);
		}
		
		if(count > 0){
			notifySetChanged(uri);
		}
		return count;
	}
	
	public void notifySetChanged(Uri uri){
		//更新数据时，通知其它的ContentObserver
		this.getContext().getContentResolver().notifyChange(uri, null);
	}
}
