package com.androidleaf.actionbar.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class BaseActivity extends Activity {

	ActionBar mActionBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		/**
		 * 获取ActionBar实例对象，若是Android3.0之前的版本，需
		 * 调用getSupportActionBar()方法获取ActionBar实例对象
		 */
		mActionBar = getActionBar();
		//隐藏ActionBar
		//mActionBar.hide();
		//显示ActionBar
		mActionBar.show();
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
	}
	
	public void showInformation(String information){
		Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
	}
	
}
