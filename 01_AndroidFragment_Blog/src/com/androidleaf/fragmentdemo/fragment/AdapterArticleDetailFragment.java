package com.androidleaf.fragmentdemo.fragment;

import com.androidleaf.fragmentdemo.activity.R;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AdapterArticleDetailFragment extends Fragment{
	
	private TextView titleContent;
	public static int index;
	
	/**
	 * 实例化 AdapterArticleDetailFragment对象
	 * @param index
	 * @return AdapterArticleDetailFragment
	 */
	public static AdapterArticleDetailFragment newInstance(int index){
		
		AdapterArticleDetailFragment mAdapterArticleDetailFragment = new AdapterArticleDetailFragment();
		Bundle mBundle = new Bundle();
		mBundle.putInt("index", index);
		//保存当前选中的选项ID
		mAdapterArticleDetailFragment.setArguments(mBundle);
		
		return mAdapterArticleDetailFragment;
	}
	
	/**
	 * 获取当前显示的选项ID
	 * @return int index
	 */
	public int showIndex(){
		if(getArguments() == null){
			return 0;
		}
		return getArguments().getInt("index",0);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View inView = inflater.inflate(R.layout.fragment_articledetails, container, false);
		titleContent = (TextView)inView.findViewById(R.id.articledetails_textview);
		//设置详情页的内容
		titleContent.setText(AdapterArticleListFragment.articleTitles[getArguments().getInt("index",0)]);
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
