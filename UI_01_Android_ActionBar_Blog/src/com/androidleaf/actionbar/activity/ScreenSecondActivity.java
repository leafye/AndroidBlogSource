package com.androidleaf.actionbar.activity;

import java.lang.reflect.Method;

import com.androidleaf.actionbar.fragment.ApplicationFragment;
import com.androidleaf.actionbar.fragment.GameFragment;
import com.androidleaf.actionbar.listener.ActionBarTabListener;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class ScreenSecondActivity extends BaseActivity {

	private SpinnerAdapter mSpinnerAdapter;
	private TextView mTextView;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_second);
		
		//是否启用ActionBar图标的导航功能
		mActionBar.setDisplayHomeAsUpEnabled(true);
		
		/*mActionBar.setTitle("");
		 * //1、设置Action Bar的导航模式
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		//2、初始化适配器并绑定下拉导航列表数据
		mSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.navigation_list_array,
				android.R.layout.simple_spinner_dropdown_item);
		//4、为ActionBar设置Adapter并为其设置事件监听
		mActionBar.setListNavigationCallbacks(mSpinnerAdapter, new MyOnNavigationListener());
		//mTextView = (TextView)findViewById(R.id.screen_second_textview);
		 */	
		
		
	    //2、设置导航模式
	    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    //3、创建ActionBar.Tab对象，为其设置ActionBar.TabListener监听器，并设置Tab的标题名称或图标
		Tab mTabApp = mActionBar
				.newTab()
				.setText("应用")
				.setTabListener(new ActionBarTabListener<ApplicationFragment>(this,
								"application", ApplicationFragment.class));
		Tab mTabGame = mActionBar
				.newTab()
				.setText("游戏")
				.setTabListener(new ActionBarTabListener<GameFragment>(this, "game",
								GameFragment.class));
		//4、将ActionBar.TAB对象添加到ActionBar中
		mActionBar.addTab(mTabApp);
		mActionBar.addTab(mTabGame);
		//mActionBar.addTab(mTabApp);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen_second, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
	        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
	            try {  
	                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);  
	                m.setAccessible(true);  
	                m.invoke(menu, true);  
	            } catch (Exception e) {  
	            }  
	        }  
	    }  
		return super.onMenuOpened(featureId, menu);
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		//ActionBar Home键ID
		case android.R.id.home:
			//获取跳转至父类Activity的Intent
			Intent mIntent = NavUtils.getParentActivityIntent(this);
			//判断父类Activity和本Activity是否同属于一个Task，true则直接根据Intent跳转，否则重新创建一个Task
			if (NavUtils.shouldUpRecreateTask(this, mIntent)) {  
		           TaskStackBuilder.create(this)  
		                    .addNextIntentWithParentStack(mIntent)  
		                    .startActivities();  
		       } else {  
		    	   mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		           NavUtils.navigateUpTo(this, mIntent);  
		       }  
			break;
		default:
			break;
		}
		return true;
	}
	
	 //3、 创建Action Bar下拉导航事件监听类
	private class MyOnNavigationListener implements OnNavigationListener{

		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			// TODO Auto-generated method stub
			switch (itemPosition) {
			case 0:
				mTextView.setText("全部");
				break;
			case 1:
				mTextView.setText("热门信息");
				break;
			case 2:
				mTextView.setText("朋友");
				break;
			case 3:
				mTextView.setText("家人");
				break;
			default:
				break;
			}
			return true;
		}
	}
}
