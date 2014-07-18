package com.fendou.activity;

import com.fendou.R;
import com.fendou.utils.StaticContens;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */

	//声明控件对象
	private Button mButton_normalbroadcast;
	private Button mButton_orderbroadcast;
	private Button mButton_send_use_normalreceiver;
	private Button mButton_send_use_stickyreceiver;
	private Button mButton_startactivity;
	
	private static final String TAG = "leaf";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //根据ID得到代表该控件的对象
        mButton_normalbroadcast = (Button)findViewById(R.id.normal_broadcastreceiver);
        mButton_orderbroadcast = (Button)findViewById(R.id.order_broadcastreceiver);
        mButton_send_use_normalreceiver = (Button)findViewById(R.id.send_use_normal_broadcastreceiver);
        mButton_send_use_stickyreceiver = (Button)findViewById(R.id.send_use_sticky_broadcastreceiver);
        mButton_startactivity = (Button)findViewById(R.id.startactivity);
        
        mButton_send_use_normalreceiver.setOnClickListener(this);
        mButton_send_use_stickyreceiver.setOnClickListener(this);
        mButton_startactivity.setOnClickListener(this);
        mButton_normalbroadcast.setOnClickListener(this);
        mButton_orderbroadcast.setOnClickListener(this);
        
        //注册BroadcastReceiver
        
      
    }

    public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.normal_broadcastreceiver:
			sendBroadcastReceiverMethod();
			break;
		case R.id.order_broadcastreceiver:
			sendOrderBroadcastReceiverMethod();
			break;
		case R.id.send_use_normal_broadcastreceiver:
			sendUseNormalBroadcastReceiverMethod();
			break;
		case R.id.send_use_sticky_broadcastreceiver:
			sendUseStickyBroadcastReceiverMethod();
			break;
		case R.id.startactivity:
			startReceiveActivity();
			break;
		default:
			break;
		}
	}
    
   /* //注册BroadcastReceiver方法
    public void filter()
    {
    	IntentFilter mFilter = new IntentFilter();
    	mFilter.addAction("com.fendou.NORMAL_BROADCASTRECEIVER");
    	registerReceiver(mBroadcastReceiver, mFilter);
    }
    
    //实例化BroadcastReceiver
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals("com.fendou.NORMAL_BROADCASTRECEIVER"))
			{
				//dosomething
			}
		}
	};*/
    //发送一个普通广播
    public void sendBroadcastReceiverMethod()
    {
    	//实例化一个Intent对象
    	Intent mIntent = new Intent();
    	//mIntent.setAction(StaticContens.NORMAL_BROADCASTRECEIVER);
    	mIntent.setAction(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER);
    	mIntent.putExtra("info", "Hello");
    	//发送普通广播
    	this.sendBroadcast(mIntent);
    }
    //发送一个有序广播
    public void sendOrderBroadcastReceiverMethod()
    {
    	//实例化一个Intent对象
    	Intent mIntent = new Intent();
    	mIntent.setAction(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER);
    	mIntent.putExtra("info", "Hello");
    	 //发送有序广播
    	this.sendOrderedBroadcast(mIntent, "com.fendou.order_broadcastreceiver_permission");
    }
    //发送粘性广播
    public void sendUseStickyBroadcastReceiverMethod()
    {
    	//实例化Intent对象
    	Intent mIntent = new Intent();
    	//设置Action
    	mIntent.setAction(StaticContens.SEND_USE_STICKY_RECEIVER);
    	//发送广播
    	this.sendStickyBroadcast(mIntent);
    }
    //发送普通广播
    public void sendUseNormalBroadcastReceiverMethod()
    {
    	//实例化Intent对象
    	Intent mIntent = new Intent();
    	//设置Action
    	mIntent.setAction(StaticContens.SEND_USE_NORMAL_RECEIVER);
    	//发送广播
    	this.sendBroadcast(mIntent);
    }
    //开启ReceiveActivity
    public void startReceiveActivity()
    {
    	//实例化Intent对象
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), ReceiveActivity.class);
    	//开启Activity
    	this.startActivity(mIntent);
    }
  
}