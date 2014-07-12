package com.androidleaf.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Android四大组件之Activity
 * @author AndroidLeaf
 */
public class BActivity extends BaseActivity {
    /** Called when the activity is first created. */
	//声明控件对象
	private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_main);
        Log.i(TAG, "B --> onCreate()");
        
        //根据ID得到代表该控件的对象
        mTextView = (TextView)findViewById(R.id.getactivity_id_textview2);
        /**
         * 得到所属栈的ID值
         * 当 android:launchMode="singleinstance"时，获取该Activity所在的Task
         */
        //mTextView.setText("This BActivity belong Task's ID: "+ this.getTaskId());
        /**
         * 当 android:launchMode="standard"时，获取该Activity的实例
         */
        mTextView.setText("This BActivity Instance :"+ BActivity.this);
    }
    
    public void startAactivity(View view){
    	intentChange(AActivity.class);
    }
    
    public void startCactivity(View view){
    	intentChange(CActivity.class);
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Log.i(TAG, "B --> onResume()");
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(TAG, "B --> onStart()");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "B --> onRestart()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(TAG, "B --> onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(TAG, "B --> onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(TAG, "B --> onDestroy()");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		 Log.i(TAG, "B --> onRestoreInstanceState(Bundle savedInstanceState)");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		 Log.i(TAG, "B --> onSaveInstanceState(Bundle outState)");
	}
}