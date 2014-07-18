package com.fendou.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.fendou.R;
import com.fendou.activity.MainActivity;

public class StaticContens {
	public static final String NORMAL_BROADCASTRECEIVER = "com.fendou.NORMAL_BROADCASTRECEIVER";
	public static final String ORDER_NORMAL_BROADCASTRECEIVER = "com.fendou.ORDER_NORMALBROADCASTRECEIVER";
	
	public static final String SEND_USE_NORMAL_RECEIVER = "com.fendou.SEND_USE_NORMAL_RECEIVER";
	public static final String SEND_USE_STICKY_RECEIVER = "com.fendou.SEND_USE_STICKY_RECEIVER";
	
	//定义showNotification方法
	public static void showNotification(Context mContext,String title,String content) {
		 //得到消息管理对象
		NotificationManager manager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.ic_launcher, content,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
       /* PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this,MainActivity.class), 0);*/
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(mContext, title,
        		content, contentIntent);

        // Send the notification.
        // We use a string id because it is a unique number.  We use it later to cancel.
        manager.notify(R.string.show_notification, notification);
    }
		
}
