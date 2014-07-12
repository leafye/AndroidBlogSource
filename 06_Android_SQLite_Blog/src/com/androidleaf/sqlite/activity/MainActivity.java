package com.androidleaf.sqlite.activity;

import java.util.ArrayList;

import com.androidleaf.sqlite.R;
import com.androidleaf.sqlite.adapter.PersonListAdapter;
import com.androidleaf.sqlite.database.PersonTableBusiness;
import com.androidleaf.sqlite.entity.Person;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends ListActivity {

	PersonTableBusiness mPersonTableBusiness;
	
	public static final int INSERT_ACTION = 0;
	public static final int UPDATE_ACTION = 1;
	public static final int DELETE_ACTION = 2;
	
	PersonListAdapter mAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	/**
	 * 初始化数据，展现数据列表
	 */
	public void init(){
		mPersonTableBusiness = new PersonTableBusiness(this);
		//添加一些默认数据
		mPersonTableBusiness.insertListPerson(initData());
		mAdapter = new PersonListAdapter(this,mPersonTableBusiness.queryAllPersons());
		//适配器绑定数据
		setListAdapter(mAdapter);
		//将ListView列表添加到上下文Menu中
		this.registerForContextMenu(getListView());
	}
	
	/**
	 * 插入数据
	 */
	public void insertPerson(){
		createDialog("添加", INSERT_ACTION, -1,null);
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
				mPersonTableBusiness.deletePersonById(id);
				//重新设置数据
				mAdapter.setData(mPersonTableBusiness.queryAllPersons());
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
	 * 初始化一些测试数据
	 * @return
	 */
	public ArrayList<Person> initData(){
		ArrayList<Person> mList = new ArrayList<Person>();
		for(int i = 0; i < 20;i++){
			Person mPerson = new Person(-1,"Steve P.Jobs "+ i, "45936455"+ i +"@qq.com", (170 + i));
			mList.add(mPerson);
		}
		return mList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 1, Menu.NONE, "添加");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
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
					switch (action) {
					case INSERT_ACTION:
						mPersonTableBusiness.insertPerson(name, email, height);
						break;
					case UPDATE_ACTION:
						mPersonTableBusiness.updatePerson(id, name, email, height);
						break;
					default:
						break;
					}
					//重新设置数据
					mAdapter.setData(mPersonTableBusiness.queryAllPersons());
					//更新数据列表
					mAdapter.notifyDataSetChanged();
				}
			}
		})
		.setNegativeButton("取消", null);
		mBuilder.show();
	}
	
	/**
	 * 当退出当前界面时，关闭数据库
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mPersonTableBusiness.closeDB();
	}
}
