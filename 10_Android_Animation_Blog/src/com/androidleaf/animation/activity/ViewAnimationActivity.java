package com.androidleaf.animation.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.os.Build;

public class ViewAnimationActivity extends ActionBarActivity {

	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewanimation);
		mImageView = (ImageView)findViewById(R.id.view_animation_imageview);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.view_animation_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Animation mAnimation = null;
		switch (item.getItemId()) {
		case R.id.view_animation_alpha:
			mAnimation = getAlphaAnimation();
			break;
		case R.id.view_animation_scale:
			mAnimation = getScaleAnimation();
			break;
		case R.id.view_animation_translate:
			mAnimation = getTranslateAnimation();
			break;
		case R.id.view_animation_rotate:
			mAnimation = getRotateAnimation();
			break;
		case R.id.view_animation_set:
			mAnimation = getSetAnimation();
			break;
		default:
			break;
		}
		
		//设置控件开始执行动画
		if(mImageView != null){
			ImageView mImageView = (ImageView)findViewById(R.id.view_animation_imageview);
			//设置控件开始执行动画
			mImageView.startAnimation(mAnimation);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Animation getAlphaAnimation(){
		Animation mAnimation = null;
		/**
		 * 代码创建图片渐变(Alpha)动画
		 * 实例化AlphaAnimation对象：
		 * mAnimation = new AlphaAnimation(fromAlpha, toAlpha);
		 * fromAlpha:设置动画开始时控件的透明度，0.0为透明，控件不显示，1.0为不透明，控件全部显示
		 * toAlpha:设置动画结束时的控件的透明度
		 */
		mAnimation = new AlphaAnimation(0.0f, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml文件加载图片渐变(Alpha)动画
		 */
		// mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_alpha);
		return mAnimation;
	}
	
	public Animation getScaleAnimation(){
		Animation mAnimation = null;
		/**
		 * 代码创建图片渐变(Scale)动画
		 * mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
		 * fromX:设置动画开始时X轴方向的缩放起始值。0.0为X轴方向的控件缩成一点，1.0为X轴方向的控件不缩放;
		 * toX:设置动画结束时X轴方向的缩放结束值;
		 * fromY:设置动画开始时Y轴方向的缩放起始值。0.0为Y轴方向的控件缩成一点，1.0为Y轴方向的控件不缩放;
		 * toY:设置动画结束时Y轴方向的缩放结束值;
		 * pivotXtype:动画在X轴相对于物件位置类型 ,分为RELATIVE_TO_SELF、RELATIVE_TO_PARENT和ABSOLUTE三种类型：
		 * 						1、RELATIVE_TO_SELF：相对于控件自身；
		 * 						2、RELATIVE_TO_PARENT：相对于父控件；
		 * 						3、ABSOLUTE：绝对坐标；
		 * pivotXValue:动画相对于物件的X坐标的开始位置;
		 * pivotYType:动画在Y轴相对于物件位置类型 ,分为RELATIVE_TO_SELF、RELATIVE_TO_PARENT和ABSOLUTE三种类型;
		 * pivotYValue:动画相对于物件的Y坐标的开始位置;
		 */
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY)
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY)
		//mAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
		mAnimation = new ScaleAnimation(0.0f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml文件加载图片缩放(Scale)动画
		 */
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_scale);
		return mAnimation;
	}
	
	public Animation getTranslateAnimation(){
		Animation mAnimation = null;
		/**
		 * 代码创建图片位置位移(Translate)动画
		 * mAnimation = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType,
		 *  fromYValue, toYType, toYValue);
		 * fromXType:动画开始执行时在X轴相对于物件位置类型
		 * fromXValue:动画开始执行时X轴方向的的起始位置，当位置类型为RELATIVE_TO_SELF时，Value取0.0f~1.0f之间，
		 * 当位置类型为RELATIVE_TO_PARENT或ABSOLUTE时， Value使用(px)像素值
		 * toXType:动画结束执行时在X轴相对于物件位置类型
		 * toXValue:动画结束执行时X轴方向的的结束位置，Value取值方式同上
		 * fromYType:动画开始执行时在Y轴相对于物件位置类型
		 * fromYValue:动画开始执行时Y轴方向的的起始位置，Value取值方式同上
		 * toYType:动画在结束执行时在Y轴相对于物件位置类型
		 * toYValue:动画结束执行时Y轴方向的的结束位置，Value取值方式同上
		 */
		//mAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, 
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		//xml文件加载图片位置位移(Translate)动画
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_translate);
		return mAnimation;
	}
	
	public Animation getRotateAnimation(){
		Animation mAnimation = null;
		/**
		 * 代码创建图片旋转(Rotate)动画
		 * mAnimation = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue)；
		 * fromDegrees：动画开始执行时的控件起始状态的角度;
		 * toDegrees：动画结束执行时的控件结束状态的角度；
		 * pivotXType：动画在X轴相对于物件位置类型 ,分为RELATIVE_TO_SELF、RELATIVE_TO_PARENT和ABSOLUTE三种类型：
		 * 						1、RELATIVE_TO_SELF：相对于控件自身；
		 * 						2、RELATIVE_TO_PARENT：相对于父控件；
		 * 						3、ABSOLUTE：绝对坐标；
		 * pivotXValue：动画开始执行时X轴方向的的起始位置，当位置类型为RELATIVE_TO_SELF时，Value取0.0f~1.0f之间，当位置类型为RELATIVE_TO_PARENT或ABSOLUTE时，
		 * 			   Value使用(px)像素值;
		 * pivotYType：动画在Y轴相对于物件位置类型 ,分为RELATIVE_TO_SELF、RELATIVE_TO_PARENT和ABSOLUTE三种类型;
		 * pivotYValue：旋转动画的中心店的Y轴方向的纵坐标，原理同上；
		 */
		//mAnimation = new RotateAnimation(fromDegrees, toDegrees)
		//mAnimation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY)
		mAnimation = new RotateAnimation(-50f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml文件加载图片旋转(Rotate)动画
		 */
		//mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_rotate);
		return mAnimation;
	}
	
	public Animation getSetAnimation(){
		Animation mAnimation = null;
		/**
		 * 代码创建图片集合(Set)动画
		 */
		AnimationSet mAnimationSet = new AnimationSet(true);
		//定义渐变动画对象
		AlphaAnimation mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
		mAlphaAnimation.setRepeatCount(1);
		mAlphaAnimation.setRepeatMode(Animation.REVERSE);
		mAlphaAnimation.setFillAfter(true);
		mAlphaAnimation.setDuration(1000);
		//定义缩放动画对象
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		mScaleAnimation.setRepeatCount(1);
		mScaleAnimation.setRepeatMode(Animation.REVERSE);
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setDuration(1000);
		//定义位移动画对象
		TranslateAnimation mTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, 
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		mTranslateAnimation.setRepeatCount(1);
		mTranslateAnimation.setRepeatMode(Animation.REVERSE);
		mTranslateAnimation.setFillAfter(true);
		mTranslateAnimation.setDuration(1000);
		//定义旋转动画对象
		RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setRepeatCount(1);
		mRotateAnimation.setRepeatMode(Animation.REVERSE);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setDuration(1000);
		//添加动画到集合动画对象中
		mAnimationSet.addAnimation(mAlphaAnimation);
		mAnimationSet.addAnimation(mScaleAnimation);
		mAnimationSet.addAnimation(mTranslateAnimation);
		mAnimationSet.addAnimation(mRotateAnimation);
		
		//mAnimation = mAnimationSet;
		
		if(mAnimation != null){
			setAnimationFlags(mAnimation);
		}
		
		/**
		 * xml文件加载图片集合(Set)动画
		 */
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.view_animation_set);
		return mAnimation;
	}
	
	public void setAnimationFlags(Animation mAnimation){
		/**
		 * 代码设置动画效果
		 */
		//设置动画重复播放的次数
		mAnimation.setRepeatCount(2);
		/*
		 * 设置重复播放的模式：
		 * reverse为0.0f -> 1.0f ->0.0f -> 1.0f,图片动画反复播放
		 * restart为0.0f -> 1.0f , 0.0f -> 1.0f，图片动画每次都从重新开始播放
		 */
		mAnimation.setRepeatMode(Animation.REVERSE);
		//设置每一次动画播放所持续的时间，单位为ms(毫秒)
		mAnimation.setDuration(2000);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.page_right_in, R.anim.page_right_out);
			return true;
		}
		 return false;
	}
}
