package com.androidleaf.test;

public class AccelerateDecelerateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//当前的时间
		float time = 10;
		//时间比例因子
		float timeFactor = 0.0f;
		for(int i = 0;i < 4;i++){
			System.out.println("时间 = " + time +"ms");
			timeFactor = time/40;
			System.out.println("时间因子 = "+ timeFactor);
			//插值因子
			float interpolatorFactor = AccelerateDecelerateTest.getInterpolation(timeFactor);
			System.out.println("插值因子 = " + interpolatorFactor);
			//属性值
			float propertyValue = evaluate(interpolatorFactor, 0, 40);
			System.out.println("属性值 = " + propertyValue);
			time += 10;
			System.out.println();
		}
		
	}
	
	/**
	 * 计算插值因子
	 * @param input 时间比例因子
	 * @return float 返回插值因子
	 */
	public static float getInterpolation(float input) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
	
	/**
	  * 计算当前的属性值
	  * @param fraction 插值因子
	  * @param startValue 属性起始值
	  * @param endValue 属性结束值
	  * @return Integer 当前的属性值
	  */
	public static Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }
	
}


