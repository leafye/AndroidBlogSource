package com.androidleaf.animation.widget;

import com.androidleaf.animation.activity.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class LoadingRobotProgressView extends ImageView {

	private AnimationDrawable mAnimationDrawable;
	
	public LoadingRobotProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingRobotProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingRobotProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	 private void init() {
		   //设置背景
	        this.setBackgroundResource(R.drawable.drawable_animation_robot);
	        //获取当前的背景
	        mAnimationDrawable = (AnimationDrawable) this.getBackground();
	    }

	    /**
	     * 当进入当前窗口时，开启动画
	     */
	    @Override
	    protected void onAttachedToWindow() {
	        super.onAttachedToWindow();
	        mAnimationDrawable.start();
	    }
        /**
         * 当离开当前窗口时，关闭动画
         */
	    @Override
	    protected void onDetachedFromWindow() {
	        super.onDetachedFromWindow();
	        mAnimationDrawable.stop();
	    }
}
