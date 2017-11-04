package com.henu.feifei.utils;

import java.util.Arrays;
import java.util.Collection;

/**
 * @ClassName:PPrint
 * @Description:添加新行并且缩排所有元素
 * @author:feifei
 * @date :2017年11月4日-上午9:04:23
 * @version:1.0
 */
public class PPrint {

	public static String pformat(Collection<?> c) {
		if (c.size() == 0) {
			return "[]";
		}
		StringBuilder result = new StringBuilder("[");
		for (Object elem : c) {
			if (c.size() != 1) {
				result.append("\n  ");
			}
			result.append(elem);
		}
		if (c.size() != 1) {
			result.append("\n");
		}
		result.append("]");
		return result.toString();
	}

	public static void pprint(Collection<?> c) {
		System.out.println(pformat(c));
	}

	public static void pprint(Object[] c) {
		System.out.println(pformat(Arrays.asList(c)));
	}
}
