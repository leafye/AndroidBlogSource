package com.androidleaf.animation.customproperty;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class CustomEvaluator implements TypeEvaluator<Number> {

	@Override
	public Float evaluate(float fraction, Number startValue, Number endValue) {
		// TODO Auto-generated method stub
		float startFloat = startValue.floatValue();
		return (startFloat + fraction * (endValue.floatValue() - startFloat));
	}
}
