package com.androidleaf.fragmentdemo.fragment;

import java.util.ArrayList;

import com.androidleaf.fragmentdemo.activity.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AddActionBarForFragment extends Fragment{

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置是否愿意添加item到选项菜单，true为真(必须设置，否则fragment接受不到onCreateOptionsMenu()的调用)
		setHasOptionsMenu(true);
		ActionBar mActionBar = getActivity().getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setIcon(R.drawable.inchengdu);
		mActionBar.setTitle("Fragment");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View inView = inflater.inflate(R.layout.fragment_addactionbar, container, false);
		return inView;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().getFragmentManager().beginTransaction().remove(this).commit();
			break;
		default:
			break;
		}
		showToast(item.getTitle().toString());
		return super.onOptionsItemSelected(item);
	}

	public void showToast(String info){
		Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
