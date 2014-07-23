package com.androidleaf.storage.activity;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    public void sharedPreferenceMethod(View view){
    	intentChange(SharedPreferenceActivity.class);
    }
    
    public void internalStorage(View view){
    	intentChange(InternalStorageActivity.class);
    }
    
    public void externalStorage(View view){
    	intentChange(ExternalStorageActivity.class);
    }
    
    public <T> void intentChange(Class<T> mClass){
    	Intent mIntent = new Intent();
    	mIntent.setClass(getApplicationContext(), mClass);
    	this.startActivity(mIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
