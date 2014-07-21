package com.androidleaf.contentprovider.activity;

import com.androidleaf.contentprovider.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.UserDictionary;

public class ImagesProviderActivity extends ListActivity {

	ImagesProviderItemAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images_provider);
		init();
	}

	
	 /** ------------------检索ImagesProvider中的数据，初始化数据列表 ----------------------*/
	public void init() {
		mAdapter = new ImagesProviderItemAdapter(getApplicationContext(),
				query(), 0);
		// 绑定适配器
		setListAdapter(mAdapter);
		// 将ListView对象注册到上下文中
		this.registerForContextMenu(getListView());
	}

	/**
	 * 检索系统提供的Images Provider中的图片信息
	 */
	@SuppressLint("NewApi")
	public Cursor query() {
		Cursor mCursor = getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.Media._ID,
						MediaStore.Images.Media.TITLE,
						MediaStore.Images.Media.DATE_TAKEN }, null, null,
				"_id desc");
		
		return mCursor;
	}

	/** ----------------------根据_ID删除ImagesProvider中的数据 ---------------------- */
	
	
	/**
	 * 删除系统提供的Images Provider中的图片信息
	 */
	public void deleteData(final String _id) {
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setMessage("是否删除数据？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// 执行删除操作
						int numberOfRows = delete(_id);
						// 重新查询Images Provider中的数据并为Adapter重新绑定数据
						mAdapter.changeCursor(query());
						// 更新数据列表
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"删除了 " + numberOfRows + "条数据！！",
								Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("取消", null);
		mDialog.show();
	}

	/**
	 * 根据_id属性删除数据
	 * 
	 * @param _id
	 *            Primary Key(主键值)
	 * @return numberOfRows 删除数据的数量
	 */
	public int delete(String _id) {
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		int numberOfRows = getContentResolver().delete(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, where,
				selectionArgs);
		return numberOfRows;
	}

	
	/** ----------------------根据_ID更新Images Provider中的数据---------------------- */
	
	/**
	 * 更新系统提供的Images Provider中的图片信息,这里修改图片的名称
	 */
	public void updateData(final String _id) {
		final View mView = LayoutInflater.from(ImagesProviderActivity.this)
				.inflate(R.layout.images_provider_update, null);
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setTitle("更新操作").setView(mView)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText mEditText_name = (EditText) mView
								.findViewById(R.id.images_name_edittext);
						int numberOfRows = 0;
						if (!TextUtils.isEmpty(mEditText_name.getText())) {
							// 执行更新操作
							numberOfRows = update(_id, mEditText_name.getText()
									.toString());
						} else {
							Toast.makeText(ImagesProviderActivity.this,
									"名称不能为空！！", Toast.LENGTH_LONG).show();
						}
						// 重新查询Images Provider中的数据并为Adapter重新绑定数据
						mAdapter.changeCursor(query());
						// 更新数据列表
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"更改了 " + numberOfRows + "条数据！！",
								Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("取消", null);
		mDialog.show();
	}

	/**
	 * 执行更新数据操作
	 * 
	 * @param _id
	 * @param title
	 * @return numberOfRows
	 */
	public int update(String _id, String title) {
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		// 定义需要修改的数据集合
		ContentValues mValues = new ContentValues();
		mValues.put(MediaStore.Images.Media.TITLE, title);
		int numberOfRows = getContentResolver().update(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mValues, where,
				selectionArgs);
		return numberOfRows;
	}

	
	/** ----------------------向ImagesProvider增加数据---------------------- */
	
	/**
	 * 添加系统提供的Images Provider中的图片信息
	 */
	public void insertData() {
		final View mView = LayoutInflater.from(ImagesProviderActivity.this)
				.inflate(R.layout.images_provider_update, null);
		AlertDialog.Builder mDialog = new AlertDialog.Builder(
				ImagesProviderActivity.this).setTitle("添加操作").setView(mView)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText mEditText_name = (EditText) mView
								.findViewById(R.id.images_name_edittext);
						String savePath = null;
						if (!TextUtils.isEmpty(mEditText_name.getText())) {
							// 执行添加操作
							savePath = insert(mEditText_name.getText()
									.toString());
						} else {
							Toast.makeText(ImagesProviderActivity.this,
									"名称不能为空！！", Toast.LENGTH_LONG).show();
						}
						// 重新查询Images Provider中的数据并为Adapter重新绑定数据
						mAdapter.changeCursor(query());
						// 更新数据列表
						mAdapter.notifyDataSetChanged();
						Toast.makeText(ImagesProviderActivity.this,
								"保存成功，路径： " + savePath + "。", Toast.LENGTH_LONG)
								.show();
					}
				}).setNegativeButton("取消", null);
		mDialog.show();
	}

	/**
	 * 执行添加图片操作
	 * 
	 * @param title
	 *            图片名称
	 * @return savePath 图片存储路径
	 */
	@SuppressLint("NewApi")
	public String insert(String title) {
		// 得到本地图片的Bitmap对象
		Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.stevejobs)).getBitmap();
		// 插入图片
		String savePath = MediaStore.Images.Media.insertImage(
				getContentResolver(), mBitmap, title, "插入的图片");

		System.out.println("savePath = "+ savePath);
		ContentValues mContentValues = new ContentValues();
		mContentValues.put(MediaStore.Images.Media.DATE_TAKEN,
				System.currentTimeMillis());
		String _id = savePath.substring(savePath.lastIndexOf("/") + 1);
		String where = MediaStore.Images.Media._ID + "= ?";
		String[] selectionArgs = { _id };
		getContentResolver().update(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mContentValues,
				where, selectionArgs);
		return savePath;
	}

	/** ----------------------这里是分割线 ---------------------- */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, Menu.NONE, "添加");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		insertData();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle("文件操作");
		menu.add(0, 1, Menu.NONE, "删除");
		menu.add(0, 2, Menu.NONE, "修改");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 得到当前被选中的item信息
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String _id = null;
		if (mAdapter != null) {
			Cursor mCursor = (Cursor) mAdapter.getItem(menuInfo.position);
			_id = mCursor.getString(mCursor
					.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
		}
		switch (item.getItemId()) {
		case 1:
			deleteData(_id);
			break;
		case 2:
			updateData(_id);
			break;
		default:
			return super.onContextItemSelected(item);
		}
		return true;
	}
}
