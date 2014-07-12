package com.androidleaf.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AActivity extends BaseActivity {
	//声明控件对象
	private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "A --> onCreate()");
        //根据ID得到代表该控件的对象
        mTextView = (TextView)findViewById(R.id.getactivity_id_textview);
        
        /**
         * 得到所属栈的ID值
         * 当 android:launchMode="singleinstance"时，获取该Activity所在的Task
         */
        //mTextView.setText("This AActivity belong Task's ID: "+ this.getTaskId());
        
        /**
         * 当 android:launchMode="standard"时，获取该Activity的实例
         */
        mTextView.setText("This AActivity Instance :"+ AActivity.this);
    }
    
    public void startAactivity(View view){
    	intentChange(AActivity.class);
    }
    
    public void startBactivity(View view){
    	intentChange(BActivity.class);
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.i(TAG, "A --> onResume()");
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(TAG, "A --> onStart()");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "A --> onRestart()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(TAG, "A --> onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(TAG, "A --> onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(TAG, "A --> onDestroy()");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//获取字符串
		String str = savedInstanceState.getString("parameters");
		//显示字符串
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
		super.onRestoreInstanceState(savedInstanceState);
		 Log.i(TAG, "A --> onRestoreInstanceState(Bundle savedInstanceState)");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		//保存一个字符串
		outState.putString("parameters", "Save somethings");
		super.onSaveInstanceState(outState);
		 Log.i(TAG, "A --> onSaveInstanceState(Bundle outState)");
	}
}