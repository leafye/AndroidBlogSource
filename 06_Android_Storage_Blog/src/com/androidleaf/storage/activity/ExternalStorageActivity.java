package com.androidleaf.storage.activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 使用外部设备存储文件数据，外部存储又分为永久性公共数据和非永久性公共数据两种
 * 
 * @author Leaf
 */
public class ExternalStorageActivity extends Activity {

	private static final String EXTERNAL_STORAGE_PUBLIC = "externalStoragePublic";
	private static final String EXTERNAL_STORAGE = "externalStorage";
	private static final String EXTERNAL_STORAGE_CACHE = "externalStorageCache";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_externalstorage);

	}

	/**
	 * 永久性公共数据存储
	 */
	public void getThePicturesPublicDirectory(View view) {
		if (isExternalStorageWritable()) {
			File mFile = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					EXTERNAL_STORAGE_PUBLIC);
			if (!mFile.exists()) {
				try {
					mFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
			}
			try {
				BufferedOutputStream mBufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream(mFile));
				mBufferedOutputStream.write(new String(
						"getThePicturesPublicDirectory").getBytes());
				mBufferedOutputStream.flush();
				mBufferedOutputStream.close();
				Toast.makeText(getApplicationContext(),
						"The file have saved!!", Toast.LENGTH_LONG).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(getApplicationContext(), "The SDCard can't work!!",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 非永久性公共数据存储
	 */
	public void getThePictureDirectory(View view) {
		File mFile = new File(
				getExternalFilesDir(Environment.DIRECTORY_PICTURES),
				EXTERNAL_STORAGE);
		if (!mFile.exists()) {
			try {
				if (mFile.createNewFile()) {
					Toast.makeText(getApplicationContext(),
							mFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
		try {
			BufferedOutputStream mBufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(mFile));
			mBufferedOutputStream.write(new String("getThePictureDirectory")
					.getBytes());
			mBufferedOutputStream.flush();
			mBufferedOutputStream.close();
			Toast.makeText(getApplicationContext(), "The file have saved!!",
					Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 非永久性数据缓存
	 */
	public void getThePictureCacheDirectory(View view) {
		File mFile = new File(getExternalCacheDir(), EXTERNAL_STORAGE_CACHE);
		if (!mFile.exists()) {
			try {
				if (mFile.createNewFile()) {
					Toast.makeText(getApplicationContext(),
							mFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
		try {
			BufferedOutputStream mBufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(mFile));
			mBufferedOutputStream.write(new String(
					"getThePictureCacheDirectory").getBytes());
			mBufferedOutputStream.flush();
			mBufferedOutputStream.close();
			Toast.makeText(getApplicationContext(), "The file have cached!!",
					Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
}
