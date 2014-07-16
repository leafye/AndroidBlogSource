package com.androidleaf.fragmentdemo.fragment;


import com.androidleaf.fragmentdemo.activity.AdapterArticleDetailActivity;
import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class AdapterArticleListFragment extends ListFragment{

	/**
	 * 判断当前加载的是否为大屏幕布局
	 */
	private boolean isScreenPad;
	/**
	 * 用来记录上次选中的项
	 */
	private int mCurrentPosition;
	
	/**
	 * 列表测试数据
	 */
	public static String[] articleTitles = {
		"a",
		"b",
		"c",
		"d",
		"e",
		"f",
		"g",
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//绑定数据列表
		setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, articleTitles));
		
		View details = getActivity().findViewById(R.id.details_container);
		//检测是否使用大屏幕尺寸的布局
		isScreenPad = details != null && details.getVisibility() == View.VISIBLE;
		
		if(savedInstanceState != null){
			//获取上次离开界面时列表选项值
			mCurrentPosition = savedInstanceState.getInt("currentChoice", 0);
		}
		if(isScreenPad){
			//设置列表选中的选项高亮
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			showDetails(mCurrentPosition);
		}
	}

	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showDetails(position);
	}

	
	/**
	 * 离开界面时保存当前选中的选项的状态值
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("currentChoice", mCurrentPosition);
	}

	/**
	 * 
	 * @param index
	 */
	public void showDetails(int index){
		mCurrentPosition = index;
		if(isScreenPad){
			getListView().setItemChecked(index, true);
			
			AdapterArticleDetailFragment mDetailFragment = (AdapterArticleDetailFragment) getActivity().getFragmentManager().findFragmentById(R.id.details_container);
			//若mDetailFragment为空或选中非当前显示的Fragment
			if(mDetailFragment == null ||  mDetailFragment.showIndex() != index){
				mDetailFragment = AdapterArticleDetailFragment.newInstance(index);
				//替换Fragment实例对象
				getActivity().getFragmentManager().beginTransaction().replace(R.id.details_container, mDetailFragment)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
			}
		}else{
			Intent mIntent = new Intent();
	    	mIntent.setClass(getActivity(), AdapterArticleDetailActivity.class);
	    	Bundle mBundle = new Bundle();
	    	mBundle.putInt("index", index);
	    	mIntent.putExtras(mBundle);
	    	getActivity().startActivity(mIntent);
		}
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
