package com.henu.feifei.utils;
/**
	*@ClassName:Enums
	*@Description:获取枚举类型的值，
	*@author:feifei
	*@date :2017年11月9日-下午1:59:10
	*@version:1.0
	*/

import java.util.Random;

public class Enums {
	private static Random rand=new Random(47);
	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}
	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}
