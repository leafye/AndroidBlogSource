package com.fendou.service;

import com.fendou.aidl.MyIPerson;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainService extends Service{

	private static final String TAG = "ygh";
	
    /** Called when the activity is first created. */

	MyIPerson.Stub binder = new MyIPerson.Stub() {

		@Override
		public String getPerson(com.fendou.aidl.Person mPerson)
				throws RemoteException {
			// TODO Auto-generated method stub
			String userInfo = "Hello," + mPerson.getUserName() +" !Welcome to study Android.\nYou ID is "+
			mPerson.getId() +",height is "+ mPerson.getHeight() + ".";
			return userInfo;
		}
	};
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStart");
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
		return binder;
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onUnbind");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
}