package com.androidleaf.contentprovider.activity;


import com.androidleaf.contentprovider.R;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagesProviderItemAdapter extends CursorAdapter {

	private ViewHolder mViewHolder;
	@SuppressLint("NewApi")
	public ImagesProviderItemAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		mViewHolder = new ViewHolder();
		View mConvertView = LayoutInflater.from(context).inflate(R.layout.images_provider_query_item, null);
		mViewHolder.mTextView_Id = (TextView)mConvertView.findViewById(R.id.app_id);
		mViewHolder.mImageView = (ImageView)mConvertView.findViewById(R.id.image);
		mViewHolder.mTextView_image_name = (TextView)mConvertView.findViewById(R.id.image_name);
		mViewHolder.mTextView_dateadd = (TextView)mConvertView.findViewById(R.id.add_date);
		mConvertView.setTag(mViewHolder);
		return mConvertView;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder holder = (ViewHolder) view.getTag();
		if(holder != null){
			long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
			if(id != -1){
				holder.mTextView_Id.setText(String.valueOf(id));
				try {
					holder.mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(
							context.getContentResolver(),
							ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			long dateAdd = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN));
			if(dateAdd != -1){
				holder.mTextView_dateadd.setText(DateUtils.formatDateTime(context, dateAdd, -1));
			}
			String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
			if(!TextUtils.isEmpty(title)){
				holder.mTextView_image_name.setText(title.toString());
			}
		}
	}
	
	  class ViewHolder{
		TextView mTextView_Id;
		ImageView mImageView;
		TextView mTextView_image_name;
		TextView mTextView_dateadd;
	}
}
