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
	
	//判断字符串是否不为空
	public static boolean isNoEmpty(String content) {
		return !isEmpty(content);
	}
	//获取文件类型
	public static String getFileType(String path) {
		if(isEmpty(path)) {
			return path;
		}
		return path.substring(path.lastIndexOf(".")+1);
	}
	//获取文件名
	public static String getFileName(String path) {
		if(isEmpty(path)) {
			return path;
		}
		return path.substring(path.lastIndexOf("/")+1,path.indexOf("."));
	}
	//获取带点的文件类型
	public static String getFileTypeWithPoint(String path) {
		if (isEmpty(path)) {
			return path;
		}
		return path.substring(path.indexOf("."));
	}
}
