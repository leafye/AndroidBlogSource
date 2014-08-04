package com.androidleaf.animation.activity;

import com.androidleaf.animation.adapter.CustomPageAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;


public class PropertyAnimationActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propertyanimation);
		
		//是否启用ActionBar图标的导航功能
		ActionBar mActionBar = getSupportActionBar();
	    mActionBar.setDisplayHomeAsUpEnabled(true);
	    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
	    mViewPager = (ViewPager)findViewById(R.id.property_animation_viewpager);
	    mViewPager.setAdapter(new CustomPageAdapter(this, mViewPager));
	    mViewPager.setOffscreenPageLimit(0);
	    
	}
}
