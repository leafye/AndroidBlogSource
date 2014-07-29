package com.andriodleaf.json.https;

import android.os.AsyncTask;

/**
 * 异步任务处理类
 * @author AndroidLeaf
 */
public class MyAsynctask extends AsyncTask<Object, Void, String> {

	/**
	 * 解析数据类型的ID
	 */
	 int typeId = -1;
	 
	 /**
	  * 定义一个回调，用于监听网络请求，当请求结束，返回访问结果
	  */
	 public HttpDownloadedListener mHttpDownloadedListener;
	
	 public interface HttpDownloadedListener{
		public void onDownloaded(String result,int typeId);
	 }
	 
	 public void setOnHttpDownloadedListener(HttpDownloadedListener mHttpDownloadedListener){
		 this.mHttpDownloadedListener = mHttpDownloadedListener;
	 }
	 
	@Override
	protected String doInBackground(Object... params) {
		// TODO Auto-generated method stub
		//执行网络访问请求
		String result = HttpUtils.getStringFromNetWork((String)params[0]);
		typeId = (int) params[1];
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mHttpDownloadedListener.onDownloaded(result,typeId);
	}
}
