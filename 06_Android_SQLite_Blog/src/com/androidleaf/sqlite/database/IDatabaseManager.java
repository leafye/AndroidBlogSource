package com.androidleaf.sqlite.database;

import java.util.ArrayList;

import android.content.ContentValues;


public interface IDatabaseManager {
	public boolean insert(ContentValues mContentValues);
	public <T> ArrayList<T> query(String sql);
	public Object query(int id);
	public boolean update(ContentValues mContentValues);
	public boolean delete(int id);
	public void execSQL(String sql);
	public void closeDB();
}
