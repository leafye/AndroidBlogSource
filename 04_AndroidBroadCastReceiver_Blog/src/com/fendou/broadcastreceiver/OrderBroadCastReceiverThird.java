package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OrderBroadCastReceiverThird extends BroadcastReceiver {

	private static final String TAG = "leaf";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(StaticContens.ORDER_NORMAL_BROADCASTRECEIVER))
		{	
			//得到从OrderBroadCastReceiverFirst设置的结果值
			String info = getResultData();
			info += "; Third";
			Log.i(TAG, "OrderBroadCastReceiverThird  "+ info);		
			//show一个Notification
			StaticContens.showNotification(context,"新广播","您接收了一条新广播");
		}
	}
}
