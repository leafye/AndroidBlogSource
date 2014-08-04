package com.androidleaf.animation.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void propertyAnimation(View view){
		intentChange(PropertyAnimationActivity.class);
	}
	
	public void viewAnimation(View view){
		intentChange(ViewAnimationActivity.class);
		overridePendingTransition(R.anim.page_right_in,R.anim.page_right_out);
	}

	public void drawableAnimation(View view){
		intentChange(DrawableAnimationActivity.class);
		
	}
	
	public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
}
