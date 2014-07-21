package com.androidleaf.contentprovider.access.activity;

import java.util.ArrayList;

import com.androidleaf.contentprovider.access.adapter.PersonListAdapter;
import com.androidleaf.contentprovider.access.entity.Person;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	PersonListAdapter mAdapter = null;
	public static final int INSERT_ACTION = 0;
	public static final int UPDATE_ACTION = 1;
	public static final int DELETE_ACTION = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	public void init(){
		
		ArrayList<Person> mArrayList =  getAllPersons();
		if(mArrayList != null && mArrayList.size() > 0){
			mAdapter = new PersonListAdapter(getApplicationContext(),mArrayList);
			
			//适配器绑定数据
			setListAdapter(mAdapter);
			//将ListView列表添加到上下文Menu中
			this.registerForContextMenu(getListView());
		}
	}
	
	public ArrayList<Person> getAllPersons(){
		ArrayList<Person> mList = new ArrayList<Person>();
		Cursor mCursor = getContentResolver().query(MyContentProviderMetaData.ProviderMetaData.Persons.PERSONS_URI, null, null, null, null);
		if(mCursor != null && mCursor.getCount() > 0){
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				int id = mCursor.getInt(mCursor.getColumnIndex(MyContentProviderMetaData.ProviderMetaData.Persons._ID));
				String name = mCursor.getString(mCursor.getColumnIndex(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME));
				String email = mCursor.getString(mCursor.getColumnIndex(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL));
				float height = mCursor.getFloat(mCursor.getColumnIndex(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT));
				mList.add(new Person(id, name, email, height));
				mCursor.moveToNext();
			}
			return mList;
		}else{
			((TextView)findViewById(R.id.empty_textview)).setText("获取的数据为空！！！");
			return null;
		}
	}
	
	/**
	 * 删除数据
	 * @param id 列表ID
	 */
	public void deletePerson(final int id){
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this)
		.setTitle("是否删除？")
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri mUri = ContentUris.withAppendedId(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_URI, id);
				getContentResolver().delete(mUri, null, null);
				//重新设置数据
				mAdapter.setData(getAllPersons());
				//更新数据列表
				mAdapter.notifyDataSetChanged();
			}
		})
		.setNegativeButton("取消", null);
		mBuilder.show();
	}
	
	/**
	 * 更新数据
	 * @param id 列表ID
	 */
	public void updatePerson(int id,Person mPerson){
		createDialog("更新", UPDATE_ACTION, id,mPerson);
	}
	
	/**
	 * 插入数据
	 */
	public void insertPerson(){
		createDialog("添加", INSERT_ACTION, -1,null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		insertPerson();
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle("文件操作");
		menu.add(0, 1, Menu.NONE, "修改");
		menu.add(0, 2, Menu.NONE, "删除");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 得到当前被选中的item信息
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int id = -1;
		Person mPerson = null;
		if(mAdapter != null){
			mPerson = (Person)mAdapter.getItem(menuInfo.position);
			id = mPerson.getId();
		}
		
		switch (item.getItemId()) {
		case UPDATE_ACTION:
			updatePerson(id,mPerson);
			break;
		case DELETE_ACTION:
			deletePerson(id);
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * 创建Dialog实例
	 * @param title 设置Dialog标题
	 * @param action 设置操作类型 ，UPDATE_ACTION为更新操作，INSERT_ACTION为插入操作
	 * @param id 被选中的选项ID
	 */
	public void createDialog(String title,final int action,final int id,final Person mPerson){
		
		final View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_operation, null);
		String titlName = getResources().getString(R.string.operation, new Object[]{title});
		
		final EditText mEditText_name = (EditText)mView.findViewById(R.id.name);
		final EditText mEditText_email = (EditText)mView.findViewById(R.id.email);
		final EditText mEditText_height = (EditText)mView.findViewById(R.id.height);
		
		//初始化数据
		if(action == UPDATE_ACTION){
			mEditText_name.setText(mPerson.getName());
			mEditText_email.setText(mPerson.getEmail());
			mEditText_height.setText(String.valueOf(mPerson.getHeight()));
		}
		
		//创建Dialog实例对象
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this)
		.setTitle(titlName)
		.setView(mView)
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String name = mEditText_name.getText().toString().trim();
				String email = mEditText_email.getText().toString().trim();
				float height = Float.parseFloat(mEditText_height.getText().toString().trim());
				
				if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && 
						!TextUtils.isEmpty(mEditText_height.getText().toString().trim())){
					ContentValues mValues = new ContentValues();
					mValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_PERSONNAME, name);
					mValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_EMAIL, email);
					mValues.put(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_FIELD_HEIGHT, height);
					switch (action) {
					case INSERT_ACTION:
						MainActivity.this.getContentResolver().insert(MyContentProviderMetaData.ProviderMetaData.Persons.PERSONS_URI, mValues);
						break;
					case UPDATE_ACTION:
						Uri mUri = ContentUris.withAppendedId(MyContentProviderMetaData.ProviderMetaData.Persons.PERSON_URI, id);
						MainActivity.this.getContentResolver().update(mUri, mValues, null, null);
						break;
					default:
						break;
					}
					//重新设置数据
					mAdapter.setData(getAllPersons());
					//更新数据列表
					mAdapter.notifyDataSetChanged();
				}
			}
		})
		.setNegativeButton("取消", null);
		mBuilder.show();
	}

}
