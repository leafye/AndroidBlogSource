package com.fendou.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;
import android.widget.Toast;

public class NetworkConnectBroadCastReceiver extends BroadcastReceiver {

	private static final String TAG = "leaf";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Log.i(TAG, "NetworkConnectBroadCastReceiver");
		doSomething(context);	
	}
	public void doSomething(Context mContext)
	{
		if(isNetworkConnect(mContext))
		{
			Toast.makeText(mContext, "网络连接成功！！", Toast.LENGTH_LONG).show();
			Log.i(TAG, "网络连接成功！！");
		}
		else
		{
			Toast.makeText(mContext, "网络已断开连接！！", Toast.LENGTH_LONG).show();
			Log.i(TAG, "网络已断开连接！！");
		}
	}
	//判断网络是否连接
	public boolean isNetworkConnect(Context mContext)
	{
		boolean flag = false;
		//获取网络连接管理对象
		ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
		//得到所有网络连接的信息
		NetworkInfo[] mInfo = manager.getAllNetworkInfo();
		if(mInfo != null){
		for(int i = 0 ; i < mInfo.length;i++)
		{
			//一一判断是否有已经连接的网络
			State mState = mInfo[i].getState();
			if(mState == NetworkInfo.State.CONNECTED)
			{
				flag = true;
				return flag;
			}
		}
		}
		return flag;
	}
}
