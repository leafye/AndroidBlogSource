package com.androidleaf.actionbar.activity;

import com.androidleaf.actionbar.widget.MyActionProvider;
import com.androidleaf.actionbar.widget.MyActionProvider.SubMenuItemClickListener;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.MediaRouteActionProvider;
import android.app.SearchManager;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ShareActionProvider;

public class ScreenFirstActivity extends BaseActivity implements SubMenuItemClickListener,OnClickListener{

	private Button mButtonSkip;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_first);
		
		mButtonSkip = (Button)findViewById(R.id.skip_screensecond_button);
		mButtonSkip.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_first, menu);

		MenuItem searchMenuItem = menu.findItem(R.id.action_search); 
		//2、调用getActionView()获取SearchView对象
		SearchView mSearchView = (SearchView) searchMenuItem.getActionView();
		//获取搜索的管理对象
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		//当前的Activity为可搜索的Activity
		mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		//以默认的方式展开
		mSearchView.setIconifiedByDefault(true);
		//3、执行相关搜索操作
		//......

		/**
		 * 获取MyActionProvider对象，并设置事件监听
		 */
		MenuItem writeMenuItem = menu.findItem(R.id.action_write);
		MyActionProvider myActionProvider = (MyActionProvider)writeMenuItem.getActionProvider();
		myActionProvider.setOnSubMenuItemClickListener(this);
		
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSubMenuItem(int itemId) {
		// TODO Auto-generated method stub
		switch (itemId) {
		case R.id.user_like:
			showInformation("点赞评论");
			break;
		case R.id.user_fuck:
			showInformation("Fuck评论");
			break;
		default:
			break;
		}
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		intentChange(ScreenSecondActivity.class);
	}
}
