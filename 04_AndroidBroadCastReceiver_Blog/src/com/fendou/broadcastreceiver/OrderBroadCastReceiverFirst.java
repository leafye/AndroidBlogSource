package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OrderBroadCastReceiverFirst extends BroadcastReceiver {

	private static final String TAG = "leaf";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER))
		{
			String info = intent.getStringExtra("info");
			Log.i(TAG, "OrderBroadCastReceiverFirst  "+ info);
			info += " First;";
			Log.i(TAG, "OrderBroadCastReceiverFirst  "+ info);
			//设置结果数据
			setResultData(info);				
		}
	}
}
