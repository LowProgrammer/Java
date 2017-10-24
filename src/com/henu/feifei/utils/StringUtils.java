package com.henu.feifei.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	//判断是否为闰年
	public static boolean isLeapYear(int year) {
		return (year%4==0&&year%100!=0)||year%400==0;
	} 
	//获取月份的天数
	public static int getMonthDay(int year,int month){
		int result = 31;	
		switch (month) {
//				case 1:
//				case 3:
//				case 5:
//				case 7:
//				case 8:
//				case 10:
//				case 12:
//					result = 31;
//				break;
				case 4:
				case 6:
				case 9:
				case 11:
					result = 30;
				break;
			case 2:
				result = StringUtils.isLeapYear(year)?29:28;
				break;
			default:
				result = 31;
				break;
		}
		return result;
	}
	//字符串格式转化为日期
	public static Date stringToDate(String dateString,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).parse(dateString);
	}
	//日期转化为字符串
	public static String dateToString(Date date,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).format(date);
	}
	//数字转化为格式
	public static String doubleToString(Double num,String pattern) throws ParseException{
		return new DecimalFormat(pattern).format(num);
	}
	//把传入的数转换为中文金额大写形式
	public static String numFormat(String s,int flag) {
		int sLength = s.length();
		// 货币大写形式
		String bigLetter[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		// 货币单位
		String unit[] = {"元", "拾", "佰", "仟", "万", 
				// 拾万位到仟万位
				"拾", "佰", "仟",
				// 亿位到万亿位
				"亿", "拾", "佰", "仟", "万"};
		String small[] = {"分", "角"};
		// 用来存放转换后的新字符串
		String newS = "";
		// 逐位替换为中文大写形式
		for(int i = 0; i < sLength; i ++) {
			if(flag == 1) {
				// 转换整数部分为中文大写形式（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48] + unit[sLength - i - 1];
			} else if(flag == 2) {
				// 转换小数部分（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48] + small[sLength - i - 1];
			}
		}
		return newS;
	}
	//替换字符串中所有的空格
	public static String replaceAllTrim(String content){
		return content.replaceAll("//s*", "");
	}
	//判断一个字符串是不是中文
	public static boolean isChineseChar(char c) {
		try {
			return String.valueOf(c).getBytes("GBK").length>1;
		} catch (Exception e) {
			return false;
		}
	}
	//截取字符串
	public static String subString(String string,int count){
		if(isEmpty(string))return "";
		int start=0;
		StringBuilder builder = new StringBuilder();
		for (int i = start; i < count; i++) {
			char c=string.charAt(i);
			builder.append(c);
			if(isChineseChar(c)){//判断一个字符是不是汉子
				--count;
			}
		}
		return builder.toString();
	}
	//验证码，文件随机数
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}
	//获取随机文件名
	public static String getNewFileName(String filename){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+getRandomString(5)+getFileType(filename);
	}
	//根据用户ID获取文件随机名
	public static String getNewFileName(String filename,Integer userId){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+getRandomString(5)+"_"+userId+getFileType(filename);
	}
	//md5
	public static byte[] md5(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(src.getBytes());
			return md;
		} catch (Exception e) {
		}
		return null;
	}
	//md5加载
	public static String md5Base64(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return base64Encode(md5.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String base64Encode(byte[] b) {
		if (b == null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(b);
	}
	//凯撒密码加密
	public static String encryption(String str,int k){
		String string = "";
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += k%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=k%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	
	public static String dencryption(String str,int n){
		String string = "";
		int k = Integer.parseInt("-"+n);
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += k%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=k%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	//判断后缀是不是图片
	public static boolean isImage(String ext) {
		return ext.toLowerCase().matches("jpg|gif|bmp|png|jpeg");
	}
	//判断后缀是不是文件
	public static boolean isDoc(String ext) {
		return ext.toLowerCase().matches("doc|docx|xls|xlsx|pdf|txt|ppt|pptx");
	}
	//判断后缀是不是视频
	public static boolean isVideo(String ext) {
		return ext.toLowerCase().matches("mp4|flv|mp3");
	}
	//替换标签符号转化位转义符号
	public static String htmlEncode(String txt) {
		if (null != txt) {
			txt = txt.replace("&", "&amp;").replace("&amp;amp;", "&amp;")
					.replace("&amp;quot;", "&quot;").replace("\"", "&quot;")
					.replace("&amp;lt;", "&lt;").replace("<", "&lt;")
					.replace("&amp;gt;", "&gt;").replace(">", "&gt;")
					.replace("&amp;nbsp;", "&nbsp;");
		}
		return txt;
	}
	//整数的转化与补0对齐
	public static String formatNO(int str, int length) {
		float ver = Float.parseFloat(System
				.getProperty("java.specification.version"));
		String laststr = "";
		if (ver < 1.5) {
			try {
				NumberFormat formater = NumberFormat.getNumberInstance();
				formater.setMinimumIntegerDigits(length);
				laststr = formater.format(str).toString().replace(",", "");
			} catch (Exception e) {
				System.out.println("格式化字符串时的错误信息：" + e.getMessage());
			}
		} else {
			Integer[] arr = new Integer[1];
			arr[0] = new Integer(str);
			laststr = String.format("%0" + length + "d", arr);
		}
		return laststr;
	}
	//字符串数组转化为字符串
	public static String arrToString(String[] strings, String separtor) {
		StringBuffer buffer = new StringBuffer();
		if (strings != null) {
			for (String string : strings) {
				buffer.append(string + separtor);
			}
			String result = buffer.toString();
			return result.substring(0, result.length() - 1);
		} else {
			return "";
		}
	}
	
	/** 首字母转换为大写 **/
	public static String toUpperCaseFirst(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

	/** 首字母转换为小写 **/
	public static String toLowerCaseFirst(String text) {
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}
	/**
	 * @作用:判断是否为数字
	 */
	public static boolean isNumeric(String str) {
		Matcher isNum = Pattern.compile("(-|\\+)?[0-9]+(.[0-9]+\\+)?").matcher(
				str);
		return isNum.matches();
	}
	
	
	/**
	 * 判断字符串是否都是数字组成
	 * 
	 * @param numString
	 * @return
	 */
	public static boolean isNumber(String numString) {
		return StringUtils.isNumeric(numString);
	}

	/**
	 * 邮箱验证 方法名：isEmail 创建人：xuchengfei 时间：2014年6月6日-下午3:14:46
	 * 
	 * @param str
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 网络地址验证 方法名：isHomepage 创建人：xuchengfei 时间：2014年6月6日-下午3:15:10
	 * 
	 * @param str
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isHomepage(String str) {
		String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);// 将给定的正则表达式编译到具有给定标志的模式中
		Matcher matcher = pattern.matcher(str);// 模式进行匹配字符�?
		return matcher.matches();
	}
	
	/**
	 * 
	 * 方法名：listToString
	 * 创建人：xuchengfei 
	 * 时间：2016年3月27日-上午12:19:48 
	 * 手机:1564545646464
	 * @param params
	 * @param sepator
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String listToString(List<String> params, String sepator) {
		if (params.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (String string : params) {
				buffer.append(string + sepator);
			}
			String result = buffer.toString();
			return result.substring(0, result.length() - 1);
		} else {
			return "";
		}
	}
}
