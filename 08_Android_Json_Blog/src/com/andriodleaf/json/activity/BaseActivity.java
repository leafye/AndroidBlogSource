package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.https.MyAsynctask;
import com.andriodleaf.json.https.MyAsynctask.HttpDownloadedListener;
import com.andriodleaf.json.tools.Constants;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public abstract class BaseActivity extends ListActivity implements HttpDownloadedListener{

	 public ProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		//初始化数据
		initData();
		
	}

	public void initData(){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setTitle("正在加载中.....");
		mProgressDialog.show();
		downloadData(Constants.GET_PERSON_PATH,R.id.json_person);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TOdO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TOdO Auto-generated method stub
		String urlStr = null;
		int typeId = -1;
		switch (item.getItemId()) {
		case R.id.json_person:
			urlStr = Constants.GET_PERSON_PATH;
			typeId = R.id.json_person;
			break;
		case R.id.json_string:
			urlStr = Constants.GET_LISTSTRING_PATH;
			typeId = R.id.json_string;
		    break;
		case R.id.json_list_person:
			urlStr = Constants.GET_LISTPERSON_PATH;
			typeId = R.id.json_list_person;
			break;
		case R.id.json_listmap_person:
			urlStr = Constants.GET_LISTMAPPERSON_PATH;
			typeId = R.id.json_listmap_person;
			break;
		default:
			break;
		}
		downloadData(urlStr,typeId);
		mProgressDialog.show();
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 根据Url地址下载数据
	 * @param urlStr Url地址
	 * @param typeId 下载的类型ID，这里使用控件ID
	 */
	public void downloadData(String urlStr,int typeId){
		MyAsynctask mAsynctask = new MyAsynctask();
		mAsynctask.setOnHttpDownloadedListener(this);
		mAsynctask.execute(urlStr,typeId);
	}
	
	public void bindData(ArrayList<String> mList){
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mList);
		setListAdapter(mAdapter);
		if(mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}
	
	/**
	 * 将Person集合转换为String集合
	 * @param mList
	 * @return
	 */
	public ArrayList<String> personsToString(ArrayList<Person> mList){
		ArrayList<String> mStrings = new ArrayList<String>();
		for(int i = 0;i < mList.size();i++){
			mStrings.add(mList.get(i).toString());
		}
		return mStrings;
	}
	
	/**
	 * 将Persons Map集合装换为String集合
	 * @param listMap
	 * @return
	 */
	public ArrayList<String> listmappersonsToString(ArrayList<Map<String, Person>> listMap){
		ArrayList<String> mStrings = new ArrayList<String>();
		for(Map<String, Person> map: listMap){
			 for(Map.Entry<String, Person> entry: map.entrySet()){
			    	mStrings.add(entry.getKey() +":"+ entry.getValue().toString());
			    }
		}
		return mStrings;
	}
}
