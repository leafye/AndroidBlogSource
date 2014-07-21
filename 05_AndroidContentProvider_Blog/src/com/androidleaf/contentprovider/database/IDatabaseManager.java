package com.androidleaf.contentprovider.database;

import android.content.ContentValues;
import android.database.Cursor;

public interface IDatabaseManager {
	public long insert(ContentValues mContentValues);
	public Cursor query(String sql);
	public Cursor query(int id);
	public int update(ContentValues mContentValues);
	public int delete(int id);
	public void execSQL(String sql);
	public void closeDB();
}
