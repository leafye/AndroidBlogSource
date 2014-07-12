package com.androidleaf.sqlite.adapter;

import java.util.ArrayList;

import com.androidleaf.sqlite.R;
import com.androidleaf.sqlite.entity.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Person> mList;
	
	public PersonListAdapter(Context mContext,ArrayList<Person> mList){
		this.mContext = mContext;
		this.mList = mList;
	}
	
	public void setData(ArrayList<Person> mList){
		this.mList = mList;
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
		ViewHolder mViewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
			mViewHolder = new ViewHolder();
			mViewHolder.mTextView_id = (TextView)convertView.findViewById(R.id.person_id);
			mViewHolder.mTextView_name = (TextView)convertView.findViewById(R.id.person_name);
			mViewHolder.mTextView_height = (TextView)convertView.findViewById(R.id.person_height);
			mViewHolder.mTextView_email = (TextView)convertView.findViewById(R.id.person_email);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		mViewHolder.mTextView_id.setText(mList.get(position).getId()+"");
		mViewHolder.mTextView_name.setText(mList.get(position).getName());
		mViewHolder.mTextView_height.setText(mList.get(position).getHeight()+"");
		mViewHolder.mTextView_email.setText(mList.get(position).getEmail());
		return convertView;
	}
	
	class ViewHolder{
		TextView mTextView_id;
		TextView mTextView_name;
		TextView mTextView_height;
		TextView mTextView_email;
	}

}
