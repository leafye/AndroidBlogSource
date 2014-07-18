package com.fendou.broadcastreceiver;

import com.fendou.utils.StaticContens;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NormalBroadReceiver extends BroadcastReceiver {

	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		if(arg1.getAction().equals(StaticContens.NORMAL_BROADCASTRECEIVER))
		{
			//show一个通知
			//StaticContens.showNotification(arg0,"新广播","您接收了一条新广播");
		}
	}
	
}
