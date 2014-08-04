package com.androidleaf.animation.widget;

import com.androidleaf.animation.activity.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class LoadingProgressView extends ImageView {

	private AnimationDrawable mAnimationDrawable;
	
	public LoadingProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public LoadingProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	 private void init() {
	        this.setBackgroundResource(R.drawable.drawable_animation);
	        mAnimationDrawable = (AnimationDrawable) this.getBackground();
	    }

	    @Override
	    protected void onAttachedToWindow() {
	        super.onAttachedToWindow();
	        mAnimationDrawable.start();
	    }

	    @Override
	    protected void onDetachedFromWindow() {
	        super.onDetachedFromWindow();
	        mAnimationDrawable.stop();
	    }
}
