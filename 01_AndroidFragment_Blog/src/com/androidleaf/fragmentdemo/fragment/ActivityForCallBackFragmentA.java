package com.androidleaf.fragmentdemo.fragment;

import java.util.ArrayList;

import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ActivityForCallBackFragmentA extends Fragment implements OnItemClickListener{

	private ListView mListView;
	ArrayAdapter<String> adapter = null;
	ArrayList<String> list = null;
	
	/**
	 * 创建一个事件回调函数，用来监听用户点击列表选项的操作
	 */
	public OnArticleSelectedListener mArticleSelectedListener;
	
	public interface OnArticleSelectedListener{
		public void onArticleSelected(int itemID,String title);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			//将宿主Activity对象强制转换成OnArticleSelectedListener实例，主要是为了确保宿主Activity实现监听接口
			mArticleSelectedListener = (OnArticleSelectedListener) activity;
		} catch (ClassCastException e) {
			// TODO: handle exception
			 throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
		}
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/**
		 * 初始化新闻列表的值
		 */
		list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View inView = inflater.inflate(R.layout.fragment_activityforcallback_a, container, false);
		mListView = (ListView)inView.findViewById(R.id.callback_listview);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//将选中的列表选项ID和Title传递给实现监听接口的宿主Activity
		mArticleSelectedListener.onArticleSelected(position,list.get(position));
	}
}
