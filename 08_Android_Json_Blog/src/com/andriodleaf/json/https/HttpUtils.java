package com.andriodleaf.json.https;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络访问类
 * @author AndroidLeaf
 */
public class HttpUtils {
	
	/**
	 * 使用HttpURLConnection方式从网络中获取数据
	 * @param urlStr
	 * @return
	 */
	public static String getStringFromNetWork(String urlStr)
	{
		URL mUrl = null;
		HttpURLConnection  mConnection= null;
		String result = null;
		try {
			mUrl = new URL(urlStr);
			mConnection = (HttpURLConnection)mUrl.openConnection();
			mConnection.setDoOutput(true);
			mConnection.setDoInput(true);
			mConnection.setReadTimeout(5 * 1000);
			mConnection.setConnectTimeout(15 * 1000);
			mConnection.setRequestMethod("GET");
			int responseCode = mConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				//获取下载资源的大小
				//contentLength = mConnection.getContentLength();
				result = changeInputStreamToString(mConnection.getInputStream());
				return result;
			}		
		} catch (IOException e) {
			// TODO: handle exception
		}
		return "";
	}
	
	/**
	 * 将字节流数据转换成字符串
	 * @param mInputStream 需要转换的字节流
	 * @return result 转换后的结果
	 */
	private static String changeInputStreamToString(InputStream mInputStream)
	{
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		String result = null;
		byte[] mBytes = new byte[2048];
		int len = -1;
		try {
			while((len = mInputStream.read(mBytes)) != -1)
			{
				mArrayOutputStream.write(mBytes, 0, len);
				mArrayOutputStream.flush();
			}
			result = new String(mArrayOutputStream.toByteArray(), "UTF-8");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(mInputStream != null){
					mInputStream.close();
				}
				if(mArrayOutputStream != null){
					mArrayOutputStream.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return "";
	}
}
