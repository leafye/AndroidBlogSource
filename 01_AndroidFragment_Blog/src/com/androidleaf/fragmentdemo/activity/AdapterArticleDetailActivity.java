package com.androidleaf.fragmentdemo.activity;

import com.androidleaf.fragmentdemo.fragment.AdapterArticleDetailFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;


public class AdapterArticleDetailActivity extends Activity{

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articledetails_activity);
		
		if(savedInstanceState == null){
			AdapterArticleDetailFragment mArticleDetailFragment = new AdapterArticleDetailFragment();
			mArticleDetailFragment.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(R.id.article_details_act,mArticleDetailFragment).commit();
		}
	}
}
