package com.androidleaf.actionbar.listener;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

@SuppressLint("NewApi")
public class ActionBarTabListener<T extends Fragment> implements TabListener {

	private Fragment mFragment;
	private Class<T> mFragmentClass;
	private String fragmentTag;
	private Activity mActivity;
	
	public ActionBarTabListener(Activity mActivity,String fragmentTag,Class<T> mFragmentClass){
		this.mActivity = mActivity;
		this.mFragmentClass = mFragmentClass;
		this.fragmentTag = fragmentTag;
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		//如果Fragment的实例对null，则重新创建
		if(mFragment == null){
			//根据类名初始化Fragment对象
			mFragment = Fragment.instantiate(mActivity, mFragmentClass.getName());
			ft.add(android.R.id.content, mFragment,fragmentTag);
		}else{
			//当选中的Tab所对应的Fragment不为null时，则建立与Tab的依赖
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//当Tab别切换到另一个Tab，依附在当前Tab的Fragment如果不为null，则解除依赖
		if(mFragment != null){
			ft.detach(mFragment);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
