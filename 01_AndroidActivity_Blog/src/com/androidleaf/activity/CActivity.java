package com.androidleaf.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * @author AndroidLeaf
 * @category Android四大组件之Activity
 */
public class CActivity extends BaseActivity {
	
	//声明控件对象
	private TextView mTextView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_main);
        Log.i(TAG, "C --> onCreate()");
        //根据ID得到代表该控件的对象
        mTextView = (TextView)findViewById(R.id.c_getactivity_id_textview);
        
        //得到所属栈的ID值
        mTextView.setText("This CActivity belong Task's ID: "+ this.getTaskId());
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
		 Log.i(TAG, "C --> onResume()");
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 Log.i(TAG, "C --> onStart()");
	}
    
    @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "C --> onRestart()");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 Log.i(TAG, "C --> onPause()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 Log.i(TAG, "C --> onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 Log.i(TAG, "C --> onDestroy()");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		 Log.i(TAG, "C --> onRestoreInstanceState(Bundle savedInstanceState)");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		 Log.i(TAG, "C --> onSaveInstanceState(Bundle outState)");
	}
}