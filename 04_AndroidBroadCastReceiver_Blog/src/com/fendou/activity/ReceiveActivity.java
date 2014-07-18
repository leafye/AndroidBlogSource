package com.fendou.activity;

import com.fendou.R;
import com.fendou.utils.StaticContens;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReceiveActivity extends Activity {
	
	//声明控件对象
	private TextView mTextView;
	
	private static final String TAG = "leaf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		//根据ID得到代表该控件的对象
		mTextView = (TextView)findViewById(R.id.show_action_textview);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//实例化过滤器对象
		IntentFilter mFilter = new IntentFilter();
		//添加Action
		mFilter.addAction(StaticContens.SEND_USE_NORMAL_RECEIVER);
		mFilter.addAction(StaticContens.SEND_USE_STICKY_RECEIVER);
		//注册广播
		registerReceiver(mBroadcastReceiver, mFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//解除注册
		unregisterReceiver(mBroadcastReceiver);
	}
	//实例化BroadcastReceiver对象
	BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//获取发送过来的Action
			String action = intent.getAction();
			Log.i(TAG, "The Receive Action is: "+ action);
			mTextView.setText("The Receive Action is: "+ action);	
		}
	};
}
