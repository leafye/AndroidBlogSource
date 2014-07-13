package com.fendou.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyServiceDemo extends Service {

	private static final String TAG = "androidLeaf";
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind()");
		return new MyBinder();
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onCreate()");
		super.onCreate();
	}	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStart()");
		super.onStart(intent, startId);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onRebind()");
		super.onRebind(intent);
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onUnbind()");
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onDestroy()");
		super.onDestroy();
	}
	//自定义MyBinder类，继承Binder类
	public class MyBinder extends Binder
	{
		public void getString(String str)
		{
			Toast.makeText(getApplicationContext(), "Hello, "+ str, Toast.LENGTH_LONG).show();	
		}
	}
}