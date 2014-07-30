package com.andriodleaf.xml.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.andriodleaf.xml.adapter.MyAdapter;
import com.andriodleaf.xml.entity.Person;
import com.andriodleaf.xml.tools.Constants;
import com.andriodleaf.xml.tools.ImageCallBack;
import com.andriodleaf.xml.tools.MyAsynctask;
import com.andriodleaf.xml.tools.MyAsynctask.HttpDownloadedListener;
import com.andriodleaf.xml.tools.XmlTools;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends ListActivity implements HttpDownloadedListener{

	private ProgressDialog mProgressDialog;
	//需要解析的节点名称
	private String nodeName = "person";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		//初始化数据
		initData();
	}
	
	public void initData(){
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setTitle("正在加载中.....");
		mProgressDialog.show();
	
		//首次进入界面是，默认使用SAX解析数据
		downloadData(this,Constants.XML_PATH,Constants.REQUEST_PULL_TYPE,null,null,Constants.TYPE_STR);
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
		int requestId = -1;
		switch (item.getItemId()) {
		case R.id.sax:
			requestId = Constants.REQUEST_SAX_TYPE;
			break;
		case R.id.dom:
			requestId = Constants.REQUEST_DOM_TYPE;
			break;
		case R.id.pull:
			requestId = Constants.REQUEST_PULL_TYPE;
			break;
		default:
			break;
		}
		downloadData(this, Constants.XML_PATH, requestId, null, null, Constants.TYPE_STR);
		mProgressDialog.show();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDownloaded(String result,int requestId) {
		// TODO Auto-generated method stub
		FileInputStream mFileInputStream = null;
		try {
			 mFileInputStream = new FileInputStream(new File(result));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Person> mList = null;
		switch (requestId) {
		case Constants.REQUEST_SAX_TYPE:
			mList = XmlTools.saxAnalysis(mFileInputStream,nodeName);
			break;
		case Constants.REQUEST_DOM_TYPE:
			mList = XmlTools.domAnalysis(mFileInputStream);
			break;
		case Constants.REQUEST_PULL_TYPE:
			mList = XmlTools.PullAnalysis(mFileInputStream,"UTF-8");
			break;
		default:
			break;
		}
		
		MyAdapter myAdapter = new MyAdapter(this, mList);
		setListAdapter(myAdapter);
		
		if(mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}
	
	//执行网络下载代码
	public static void downloadData(HttpDownloadedListener mDownloadedListener,String url,int requestId,ImageView mImageView,ImageCallBack mImageCallBack,int typeId){
		MyAsynctask mAsynctask = new MyAsynctask(mImageView,mImageCallBack,requestId);
		mAsynctask.setOnHttpDownloadedListener(mDownloadedListener);
		mAsynctask.execute(url,typeId);
	}
	
}
