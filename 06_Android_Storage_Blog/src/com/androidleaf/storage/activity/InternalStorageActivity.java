package com.androidleaf.storage.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 使用内部文件（Internal Storage）存储方式存储数据
 * 1、调用openFileInput()或openFileOutput()方法获取内部文件存储的输入流和输出流
 * 2、调用read()或write()方法输入或输出字节
 * 3、调用close()方法关闭输入输出流
 * @author Leaf
 */
public class InternalStorageActivity extends Activity {

	private static final String SAVE_FILE_NAME = "internal_storage_file_name";
	private EditText mEditText;
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internalstorage);
		mEditText = (EditText)findViewById(R.id.content_edittext);
		mTextView = (TextView) findViewById(R.id.content_textview);
	}
	
	public void readInternalFile(View view){
		StringBuffer mBuffer = new StringBuffer();
		byte[] b = new byte[1024];
		int index = -1;
		try {
			FileInputStream mFileInputStream = openFileInput(SAVE_FILE_NAME);
			while((index = mFileInputStream.read(b))!= -1){
				mBuffer.append(new String(b));
			}
			mEditText.setText(mBuffer.toString());
			mTextView.setText(mBuffer.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveInternalFile(View view){
		if(!mEditText.getText().toString().trim().equals("")){
			try {
				FileOutputStream mFileOutputStream = openFileOutput(SAVE_FILE_NAME, Context.MODE_PRIVATE);
				mFileOutputStream.write(mEditText.getText().toString().trim().getBytes());
				mEditText.setText("");
				mTextView.setText("");
				mFileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Toast.makeText(getApplicationContext(), "Save Sucessfully", Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getApplicationContext(), "The content can't be null", Toast.LENGTH_LONG).show();
		}
	}
}
