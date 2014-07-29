package com.andriodleaf.json.activity;

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

	public void jsonAnalysis(View view){
		intentChange(JsonActivity.class);
	}
	
	public void gsonAnalysis(View view){
		intentChange(GsonActivity.class);
	}
	
	public void jacksonAnalysis(View view){
		intentChange(JacksonActivity.class);
	}
	
	public void fastJsonAnalysis(View view){
		intentChange(FastJsonActivity.class);
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
}
