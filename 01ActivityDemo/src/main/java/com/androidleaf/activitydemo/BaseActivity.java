package com.androidleaf.activitydemo;

import android.app.Activity;
import android.content.Intent;

public class BaseActivity extends Activity {

	//��־tag
	public static final String TAG = "androidLeaf";
	
	public <T> void intentChange(Class<T> mClass){
	    	Intent mIntent = new Intent();
	    	mIntent.setClass(getApplicationContext(), mClass);
	    	this.startActivity(mIntent);
		}
}
