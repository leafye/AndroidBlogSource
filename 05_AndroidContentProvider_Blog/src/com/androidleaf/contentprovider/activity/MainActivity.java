package com.androidleaf.contentprovider.activity;

import com.androidleaf.contentprovider.R;

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

	public void imagesProvider(View view){
		intentChange(ImagesProviderActivity.class);
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
}
