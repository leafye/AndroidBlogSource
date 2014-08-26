package com.androidleaf.handler.activity;

import com.androidleaf.handler.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity{
	
	private ImageView mImageView ;
	private static final String TAG = "androidleaf";
	
	private  String urlStr = "http://img.my.csdn.net/uploads/201408/25/1408936379_4781.png" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView)findViewById(R.id.dialog_imageview );
	}
	
	public void mainHanlderMainLooper(View view){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			 Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
             //1、实例化一个Message对象
             Message message = Message.obtain();
             //将图片流赋值给Message对象
             message. obj = mBitmap;
             //定义标签
             message. what = 0;
             //3、发送消息到主线程中的Handler
             mHandler .sendMessage(message);
			}
		}).start();
		
	}
	//2、在主线程中实例化Handler对象
	Handler  mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			 //4、接收消息并执行UI的更新操作
            if (msg.obj != null)
            {
                Bitmap mBitmap = (Bitmap) msg.obj;
                mImageView .setImageBitmap(mBitmap);
            }
            else
            {
                Log. i( TAG, "Can not download the image source!!");
            }
        }
			
	};
	
	public void subHanlderMainLooper(View view){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
		        //实例化一个Message对象
		        Message message = Message.obtain();
		        //将图片流赋值给Message对象
		        message. obj = mBitmap;
				 Handler mHandler3 = new Handler(Looper.getMainLooper())
	             {
	                  @Override
	                  public void handleMessage(Message msg) {
	                       // TODO Auto-generated method stub
	                      super .handleMessage(msg);
	                       if (msg.obj != null)
	                      {
	                          Log. i( TAG, "使用子线程的Handler和主线程的Looper" );
	                      }
	                 }
	             };
	            mHandler3.sendMessage(message);
			}
		}).start();
		
	}
	
	public void subHanlderSubLooper(View view){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap mBitmap = HttpUtils.getBitmapFromNetWork(urlStr);
				//1、创建Looper和MessageQueue对象
                Looper. prepare();
			    //2、实例化Handler对象
                Handler mHandler4 = new Handler()
                {
                     @Override
                     public void handleMessage(Message msg) {
                          // TODO Auto-generated method stub
                         super .handleMessage(msg);
                          //6、接收消息并执行UI的更新操作
                          if (msg.obj != null)
                         {
                             Log. i( TAG, "使用子线程的Handler和子线程的Looper" );
                         }
                    }
                };
                //3、实例化一个Message对象
                Message message = Message.obtain();
                //将图片流赋值给Message对象
                message. obj = mBitmap;
                //4、发送消息
                mHandler4.sendMessage(message);
                //5、开启消息循环机制
                Looper. loop();
			}
		}).start();
	}
	
	public void welcome(View view){
		Intent mIntent = new Intent();
        mIntent.setClass(getApplicationContext(), WelcomeActivity.class );
        MainActivity.this.startActivity(mIntent);
	}
}
