package com.henu.feifei.net;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
	*@ClassName:InternetAddressTest
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月16日-下午1:10:24
	*@version:1.0
	*/
public class InternetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress address=InetAddress.getByName("www.baidu.com");
		System.out.println(address.getHostAddress());
		System.out.println(address.isReachable(200));
		System.out.println(address.getHostName());
		System.out.println(address.getCanonicalHostName());
		
		System.out.println("=====================");
		InetAddress address2=InetAddress.getLocalHost();
		System.out.println(address2.getHostName());
		System.out.println(address2.getHostAddress());
		System.out.println(address2.getCanonicalHostName());
		
		String link="http://c.hiphotos.baidu.com/image/h%3D300/sign=b93d42f34236acaf46e090fc4cdb8d03/18d8bc3eb13533faf05992e4a1d3fd1f40345b08.jpg";
		String decoderString=URLDecoder.decode(link, "utf-8");
		System.out.println(decoderString);
		
		
		
		String string="www.baidu.com?name=哈哈";
		System.out.println(URLEncoder.encode(string,"utf-8"));
		String encode=URLEncoder.encode(URLEncoder.encode(string,"utf-8"),"utf-8");
		System.out.println(encode);
		
		
	}
}
