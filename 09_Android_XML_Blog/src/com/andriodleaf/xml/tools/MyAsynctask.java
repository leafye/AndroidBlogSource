package com.andriodleaf.xml.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.andriodleaf.xml.https.HttpRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;


/**
 * 异步请求工具类
 * @author AndroidLeaf
 */
public class MyAsynctask extends AsyncTask<Object, Void, Object>  {

	private ImageView mImageView;
	private ImageCallBack mImageCallBack;
	//请求类型，分为XML文件请求和图片下载请求
	private int typeId;
	//使用的XML解析类型ID
	private int requestId;
	
	/**
	  * 定义一个回调，用于监听网络请求，当请求结束，返回访问结果
	  */
	 public HttpDownloadedListener mHttpDownloadedListener;
	
	 public interface HttpDownloadedListener{
		public void onDownloaded(String result,int requestId);
	 }
	 
	 public void setOnHttpDownloadedListener(HttpDownloadedListener mHttpDownloadedListener){
		 this.mHttpDownloadedListener = mHttpDownloadedListener;
	 }
	
	public MyAsynctask(ImageView mImageView,ImageCallBack mImageCallBack,int requestId){
		this.mImageView = mImageView;
		this.mImageCallBack = mImageCallBack;
		this.requestId = requestId;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		InputStream mInputStream = HttpRequest.getInputStreamFromNetWork((String)params[0]);
		if(mInputStream != null){
			switch ((int)params[1]) {
			case Constants.TYPE_STR:
				typeId = Constants.TYPE_STR;
				return WriteIntoFile(mInputStream);
			case Constants.TYPE_STREAM:
				typeId = Constants.TYPE_STREAM;
				return getBitmap(HttpRequest.readStream(mInputStream),
						200, 200);
			default:
				break;
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		if(result != null){
			switch (typeId) {
			case Constants.TYPE_STR:
				mHttpDownloadedListener.onDownloaded((String)result,requestId);
				break;
			case Constants.TYPE_STREAM:
				mImageCallBack.resultImage(mImageView,(Bitmap)result);
				break;
			default:
				break;
			}
			typeId = -1;
		}
		super.onPostExecute(result);
	}
	
	public Bitmap getBitmap(byte[] bytes,int width,int height){
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
	
	/**
	 * 将下载的XML文件流存储到手机指定的SDcard目录下
	 * @param mInputStream 需要读入的流
	 * @return String 返回存储的XML文件的路径
	 */
	public String WriteIntoFile(InputStream mInputStream){
		if(isSDcard()){
			try {
				FileOutputStream mOutputStream = new FileOutputStream(new File(getFileName()));
				int len = -1;
				byte[] bytes = new byte[2048];
				try {
					while((len = mInputStream.read(bytes)) != -1){
						mOutputStream.write(bytes, 0, len);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(mOutputStream != null){
							mOutputStream.close();
						}
						if(mInputStream != null){
							mInputStream.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getFileName();
		}
		return null;
	}
	
	/**
	 * 检测SDcard是否可用
	 * @return
	 */
	public boolean isSDcard(){
		 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	/**
	 * 获取需要存储的XML文件的路径
	 * @return String 路径
	 */
	public String getFileName(){
		String path = Environment.getExternalStorageDirectory().getPath() +"/XMLFiles";
		File mFile = new File(path);
		if(!mFile.exists()){
			mFile.mkdirs();
		}
		return mFile.getPath() + "/xmlfile.xml";
	}
}
