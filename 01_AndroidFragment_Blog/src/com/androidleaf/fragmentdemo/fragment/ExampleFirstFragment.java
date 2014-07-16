package com.androidleaf.fragmentdemo.fragment;

import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class ExampleFirstFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/**
		 * 这里主要初始化一些在Fragment需使用和持久化的必要组件
		 */
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/**
		 * 使用inflate()方法加载一个自定义的Layout布局，该布局是Fragment的根布局view.
		 * inflate()方法的3个参数:
		 * 1.想要加载的Layout Resource Id；
		 * 2.加载 Layout的父ViewGroup,目的是将Layout挂靠到container上；
		 * 3.布尔值，指示是否将Layout附着到ViewGroup中，这里一般指定false，
		 * 因为Layout已经附着到container上，若为true，系统将为Layout新建一个ViewGroup作为附着对象，多余；
		 */
		View inView = inflater.inflate(R.layout.fragment_main, container, false);
		
		/**
		 * 这里主要是初始化Layout中的控件对象
		 */
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		/**
		 * 当用户离开此Fragment界面时，系统调用此方法，这里主要保存一些需要持久化的对象状态
		 */
	}
}
