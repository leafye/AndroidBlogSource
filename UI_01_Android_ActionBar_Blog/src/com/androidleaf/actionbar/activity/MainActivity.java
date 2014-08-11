package com.androidleaf.actionbar.activity;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends BaseActivity implements OnClickListener{

	private Button mButtonSkip;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButtonSkip = (Button)findViewById(R.id.skip_screenfrist_button);
		mButtonSkip.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		Resources mResources = getResources();
		switch (id) {
		case R.id.action_delete:
			showInformation(mResources.getString(R.string.action_delete));
			break;
		case R.id.action_tip:
			showInformation(mResources.getString(R.string.action_tip));
			break;
		case R.id.action_settings:
			showInformation(mResources.getString(R.string.action_setting));
			break;
		case R.id.action_send:
			showInformation(mResources.getString(R.string.action_send));
			break;
		default:
			break;
		}
		return true;
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
			//通过发射机制根据类名获取setOptionalIconsVisible(boolean visible)方法，并为其设置true
	        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
	            try {  
	                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);  
	                method.setAccessible(true);  
	                method.invoke(menu, true);  
	            } catch (Exception e) {  
	            }  
	        }  
	    }  
		return super.onMenuOpened(featureId, menu);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		intentChange(ScreenFirstActivity.class);
	}
	
}
