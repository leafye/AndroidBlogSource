package com.andriodleaf.xml.adapter;

import java.util.ArrayList;

import com.andriodleaf.xml.activity.MainActivity;
import com.andriodleaf.xml.activity.R;
import com.andriodleaf.xml.entity.Person;
import com.andriodleaf.xml.tools.Constants;
import com.andriodleaf.xml.tools.ImageCallBack;
import com.andriodleaf.xml.tools.MyAsynctask;
import com.andriodleaf.xml.tools.MyAsynctask.HttpDownloadedListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 界面的展示的适配器类
 * @author AndroidLeaf
 */
public class MyAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Person> mList;
	private Bitmap[] mBitmaps;
	
	public MyAdapter(Context mContext,ArrayList<Person> mList){
		this.mContext = mContext;
		this.mList = mList;
		mBitmaps = new Bitmap[mList.size()];
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		Person mPerson = mList.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
			mHolder = new ViewHolder();
			mHolder.mTextView_id = (TextView)convertView.findViewById(R.id.item_id);
			mHolder.mTextView_name = (TextView)convertView.findViewById(R.id.item_name);
			mHolder.mTextView_height = (TextView)convertView.findViewById(R.id.item_height);
			mHolder.mImageView_image = (ImageView)convertView.findViewById(R.id.item_image);
			//为Imageview设置TAG，作为每一个ImageView的唯一标识
			mHolder.mImageView_image.setTag(mPerson.getImageUrl());
			convertView.setTag(mHolder);
		}else{
			mHolder = (ViewHolder)convertView.getTag();
		}
		mHolder.mTextView_id.setText(String.valueOf(mPerson.getId()));
		mHolder.mTextView_name.setText(mPerson.getUserName());
		mHolder.mTextView_height.setText(String.valueOf(mPerson.getHeight()));
		/**
		 * 解决异步加载过程中Listview列表中图片显示错位问题
		 */
		//判断当前位置的ImageView和是否为上次执行加载操作的ImageView，若false则重置上次加载的那个Imageview中的图片资源
		if(!mPerson.getImageUrl().equals(String.valueOf(mHolder.mImageView_image.getTag()))){
			mHolder.mImageView_image.setImageResource(R.drawable.ic_launcher);
		}
		//重新为ImageView实例设置TAG
		mHolder.mImageView_image.setTag(mPerson.getImageUrl());
		if(mBitmaps[position] == null){
			//执行异步加载图片操作
			MainActivity.downloadData((HttpDownloadedListener)mContext, Constants.BASE_PATH + mPerson.getImageUrl(), -1, 
					mHolder.mImageView_image, new MyImageCallBack(position,mPerson.getImageUrl()), Constants.TYPE_STREAM);
		}else{
			mHolder.mImageView_image.setImageBitmap(mBitmaps[position]);
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView mTextView_id;
		TextView mTextView_name;
		TextView mTextView_height;
		ImageView mImageView_image;
	}
	
	class MyImageCallBack implements ImageCallBack{
		int index = -1;
		String imageUrl = null;
		public MyImageCallBack(int index,String imageUrl){
			this.index = index;
			this.imageUrl = imageUrl;
		}
		
		@Override
		public void resultImage(ImageView mImageView, Bitmap mBitmap) {
			// TODO Auto-generated method stub
			//判断当前显示的ImageView的URL是否与需要下载的图片ImageView的URL相同
			if(imageUrl.equals(String.valueOf(mImageView.getTag()))){
				mBitmaps[index] = mBitmap;
				mImageView.setImageBitmap(mBitmap);
			}
		}
	}
}
