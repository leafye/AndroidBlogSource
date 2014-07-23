package com.androidleaf.storage.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *使用 SharedPreference存储方式保存用户名和密码
 *使用SharedPreference进行数据存储的步骤一般分为以下几步：
 *	1、获取当前上下文的SharedPreferences对象
 *	2、获取SharedPreferences的编辑对象
 *	3、调用putString(),putInt()方法或getString(),getInt()等方法添加或获取数据
 *  4、调用commit()方法提交数据，(若是获取数据不许提交)
 * @author Leaf
 */
public class SharedPreferenceActivity extends Activity {

	private EditText editTextName;
	private EditText editTextPassword;
	private SharedPreferences mPreferences;
	private static final String SHAREDPREFERENCE_NAME = "sharepreference_name";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);
        /**
         * 若希望为一个Activity使用SharedPreferences方式分多个文件来保存数据，使用以下方法获取SharedPreferences对象，
         * 第一个参数为文件指定文件名
         * mPreferences = this.getSharedPreferences(SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
         */
        //1.若只需使用一个文件为单个Activity来保存数据，可以使用以下方法获取SharedPreferences对象，文件名默认为所属Activity的类名
        mPreferences = this.getPreferences( Context.MODE_PRIVATE);
        editTextName = (EditText)findViewById(R.id.username_edittext);
        editTextPassword = (EditText)findViewById(R.id.password_edittext);
        getPreferences();
    }
   
    public void saveButton(View view){
    	//2.获取SharedPreferences的编辑对象
    	SharedPreferences.Editor mEditor = mPreferences.edit();
    	String username = editTextName.getText().toString().trim();
    	String password = editTextPassword.getText().toString().trim();
    	if(username.equals("")||password.equals("")){
    		Toast.makeText(getApplicationContext(), "用户名或密码不能为空！！", Toast.LENGTH_LONG).show();
    	}else{
    		mEditor.putString(USERNAME, username);
        	mEditor.putString(PASSWORD, password);
        	if(mEditor.commit()){
        		Toast.makeText(getApplicationContext(), "保存成功！！", Toast.LENGTH_LONG).show();
        		 getPreferences();
        	}
    	}
    }
    
    public void cancelButton(View view){
    	//3.获取SharedPreferences的编辑对象
    	SharedPreferences.Editor mEditor = mPreferences.edit();
    	mEditor.putString(USERNAME, "");
    	mEditor.putString(PASSWORD, "");
    	//4.提交数据
    	if(mEditor.commit()){
    		Toast.makeText(getApplicationContext(), "已删除！！", Toast.LENGTH_LONG).show();
    		 getPreferences();
    	}
    }
    /**
     * 获取缓存的用户名和密码
     */
    public void getPreferences(){
        editTextName.setText(mPreferences.getString(USERNAME, ""));
        editTextPassword.setText(mPreferences.getString(PASSWORD, ""));
    }
}
