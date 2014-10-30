package com.androidleaf.audiorecord;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.androidleaf.audiorecord.R;

/**
 * 动画工具类
 * 
 */
public class AnimUtils {

	/**
	 * 显示View
	 * 
	 * @param context
	 * @param view
	 */
	public static void showView(Context context, View view, int animId) {
		if (view == null)
			return;
		if (view.getVisibility() == View.VISIBLE)
			return;
		view.setVisibility(View.VISIBLE);
		view.clearAnimation();
		view.startAnimation(AnimationUtils.loadAnimation(context, animId));
	}

	/**
	 * 隐藏View
	 * 
	 * @param context
	 * @param view
	 * @param animId
	 */
	public static void hideView(Context context, final View view, int animId) {
		if (view == null)
			return;
		if (view.getVisibility() == View.GONE)
			return;
		view.clearAnimation();
		// view.setVisibility(View.GONE);
		Animation localAnim = AnimationUtils.loadAnimation(context, animId);
		localAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				view.setVisibility(View.GONE);
			}
		});
		view.startAnimation(localAnim);
	}

	/**
	 * 从底部显示一个View
	 * 
	 * @param context
	 * @param view
	 */
	public static void showViewFromBottom(Context context, View view) {
		showView(context, view, R.anim.base_pop_show_from_bottom);
	}

	/**
	 * 从底部隐藏一个View
	 * 
	 * @param context
	 * @param view
	 */
	public static void hideViewFromBottom(Context context, View view) {
		hideView(context, view, R.anim.base_pop_dismiss_from_bottom);
	}

	/**
	 * 从Top显示View
	 * 
	 * @param context
	 * @param view
	 */
	public static void showViewFromTop(Context context, View view) {
		showView(context, view, R.anim.base_pop_show_from_top);
	}

	/**
	 * 从Top显示View
	 * 
	 * @param context
	 * @param view
	 */
	public static void hideViewFromTop(Context context, View view) {
		hideView(context, view, R.anim.base_pop_dismiss_from_top);
	}

}
