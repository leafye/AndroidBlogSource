package com.androidleaf.animation.customproperty;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class CustomInterpolator implements TimeInterpolator {

	@Override
	public float getInterpolation(float input) {
		input *= 0.8f;
		return input * input;
	}
}
