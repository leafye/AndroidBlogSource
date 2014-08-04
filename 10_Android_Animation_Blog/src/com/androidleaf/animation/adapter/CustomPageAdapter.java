package com.androidleaf.animation.adapter;

import java.util.ArrayList;

import com.androidleaf.animation.fragment.LayoutAnimatorFragment;
import com.androidleaf.animation.fragment.ObjectAnimatorFragment;
import com.androidleaf.animation.fragment.ValueAnimatorFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;

public class CustomPageAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener, TabListener {

	private final ActionBar mActionBar;
	private final ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	private ArrayList<Fragment> mFragments;
	
	static final class TabInfo {
		private final Class<?> clss;
		TabInfo(Class<?> _class) {
			clss = _class;
		}
	}

	public CustomPageAdapter(ActionBarActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		mActionBar = activity.getSupportActionBar();
		mViewPager = pager;
		mViewPager.setOnPageChangeListener(this);
		newFragmentsInstance();
		addTabs();
	}

	public void addTabs() {
         TabInfo valueInfo = new TabInfo(ValueAnimatorFragment.class);
         Tab mTabValue = mActionBar.newTab().setText("ValueAnimator").setTabListener(this);
         mTabValue.setTag(valueInfo);
        
         TabInfo objectInfo = new TabInfo(ObjectAnimatorFragment.class);
 		 Tab mTabObject = mActionBar.newTab().setText("ObjectAnimator").setTabListener(this);
 		 mTabObject.setTag(objectInfo);
 		 
 		 TabInfo layoutInfo = new TabInfo(LayoutAnimatorFragment.class);
 		 Tab mTabLayout = mActionBar.newTab().setText("LayoutAnimator").setTabListener(this);
 		mTabLayout.setTag(layoutInfo);
 		
 		 mTabs.add(valueInfo);
 		 mTabs.add(objectInfo);
 		 mTabs.add(layoutInfo);
         mActionBar.addTab(mTabValue);
         mActionBar.addTab(mTabObject);
         mActionBar.addTab(mTabLayout);
         
         notifyDataSetChanged();
     }

	public void newFragmentsInstance(){
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new ValueAnimatorFragment());
		mFragments.add(new ObjectAnimatorFragment());
		mFragments.add(new LayoutAnimatorFragment());
	}
	
	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		mActionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Object tag = tab.getTag();
		for (int i = 0; i < mTabs.size(); i++) {
			if (mTabs.get(i) == tag) {
				mViewPager.setCurrentItem(i);
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}
}
