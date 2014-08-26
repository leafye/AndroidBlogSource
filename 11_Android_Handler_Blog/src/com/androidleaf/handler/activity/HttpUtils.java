package com.androidleaf.handler.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

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
	public static Bitmap getBitmapFromNetWork(String urlStr)
	{
		URL mUrl = null;
		HttpURLConnection  mConnection= null;
		Bitmap result = null;
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
				result = getBitmap(changeInputStreamToBytes(mConnection.getInputStream()), 80, 80);
				return result;
			}		
		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 将字节流数据转换成字节数组
	 * @param mInputStream 需要转换的字节流
	 * @return result 转换后的结果
	 */
	private static byte[] changeInputStreamToBytes(InputStream mInputStream)
	{
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		byte[] result = null;
		byte[] mBytes = new byte[2048];
		int len = -1;
		try {
			while((len = mInputStream.read(mBytes)) != -1)
			{
				mArrayOutputStream.write(mBytes, 0, len);
				mArrayOutputStream.flush();
			}
			result = mArrayOutputStream.toByteArray();
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
		return null;
	}
	
	private static Bitmap getBitmap(byte[] bytes,int width,int height){
		//获取屏幕的宽和高  
        /** 
         * 为了计算缩放的比例，我们需要获取整个图片的尺寸，而不是图片 
         * BitmapFactory.Options类中有一个布尔型变量inJustDecodeBounds，将其设置为true 
         * 这样，我们获取到的就是图片的尺寸，而不用加载图片了。 
         * 当我们设置这个值的时候，我们接着就可以从BitmapFactory.Options的outWidth和outHeight中获取到值 
         */  
        BitmapFactory.Options op = new BitmapFactory.Options();  
        op.inJustDecodeBounds = true;  
        Bitmap pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        
        int wRatio = (int) Math.ceil(op.outWidth / (float) width); //计算宽度比例  
        int hRatio = (int) Math.ceil(op.outHeight / (float) height); //计算高度比例
        
        /** 
         * 接下来，我们就需要判断是否需要缩放以及到底对宽还是高进行缩放。 
         * 如果高和宽不是全都超出了屏幕，那么无需缩放。 
         * 如果高和宽都超出了屏幕大小，则如何选择缩放呢》 
         * 这需要判断wRatio和hRatio的大小 
         * 大的一个将被缩放，因为缩放大的时，小的应该自动进行同比率缩放。 
         * 缩放使用的还是inSampleSize变量 
         */  
        if (wRatio > 1 && hRatio > 1) {  
            if (wRatio > hRatio) {  
                op.inSampleSize = wRatio;  
            } else {  
                op.inSampleSize = hRatio;  
            }  
        }  
        op.inJustDecodeBounds = false; //注意这里，一定要设置为false，因为上面我们将其设置为true来获取图片尺寸了  
        pic = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return pic;
	}
}
