package com.androidleaf.fragmentdemo.fragment;

import com.androidleaf.fragmentdemo.activity.R;
import com.androidleaf.fragmentdemo.fragment.ActivityForCallBackFragmentA.OnArticleSelectedListener;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ActivityForCallBackFragmentB extends Fragment{

	private TextView mTextView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View inView = inflater.inflate(R.layout.fragment_activityforcallback_b, container, false);
		mTextView = (TextView)inView.findViewById(R.id.callback_textview);
		return inView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public void setContent(String titleContent){
		if(!TextUtils.isEmpty(titleContent)){
			mTextView.setText(titleContent);
		}
	}
}
