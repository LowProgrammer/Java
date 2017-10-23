package com.henu.feifei.utils;
/**
	*@ClassName:StringUtils
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月23日-下午1:19:30
	*@version:1.0
	*/
public class StringUtils {
	
	
	//判断字符串 是否为空
	public static boolean isEmpty(String content) {
		return null!=content&&"".equals(content);
	}
	public static boolean isNoEmpty(String content) {
		return !isEmpty(content);
	}
}
