package com.androidleaf.fragmentdemo.activity;

import com.androidleaf.fragmentdemo.fragment.AddFragmentFirst;
import com.androidleaf.fragmentdemo.fragment.AddFragmentSecond;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class AddByDynamicActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addbydynamic);
		
		//获取屏幕宽高
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		//1.根据上下文获取FragmentManager对象
		FragmentManager manager = this.getFragmentManager();
	
		if(mDisplayMetrics.widthPixels < mDisplayMetrics.heightPixels){
			//2.使用获取到的manager对象开启一个事务
			FragmentTransaction mFragmentTransaction01 = manager.beginTransaction();
			//3.替换Fragment
			mFragmentTransaction01.replace(R.id.container, new AddFragmentFirst());
			//4.提交事务
			mFragmentTransaction01.commit();
		}else{
			FragmentTransaction mFragmentTransaction02 = manager.beginTransaction();
			mFragmentTransaction02.replace(R.id.container, new AddFragmentSecond()).commit();
		}
		
	}
}
