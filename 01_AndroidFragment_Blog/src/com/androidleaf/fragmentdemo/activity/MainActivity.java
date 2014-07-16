package com.androidleaf.fragmentdemo.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void lifeCycle(View view){
		intentChange(LifeCycleActivity.class);
	}
	
	public void exampleFirst(View view){
		intentChange(ExampleFirstActivity.class);
	}
	
	public void addFragmentByDynamic(View view){
		intentChange(AddByDynamicActivity.class);
	}
	
	public void addFragmentByStatic(View view){
		intentChange(AddByStaticActivity.class);
	}
	
	public void createEventCallBackToActivity(View view){
		intentChange(ActivityForCallBackActivity.class);
	}
	
	public void addActionBarToFragment(View view){
		intentChange(AddActionBarForFragmentActivity.class);
	}
	
	public void adaptvieBetweenMobileAndPad(View view){
		intentChange(AdapterMobileAndPadActivity.class);
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
	
}
