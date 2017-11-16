package com.henu.feifei.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
	*@ClassName:ProcessParser
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月16日-下午1:18:18
	*@version:1.0
	*/
public class ProcessParser {
	public static void main(String[] args)throws Exception {
		//Process process=Runtime.getRuntime().exec("ipconfig -all");
		//Process process=Runtime.getRuntime().exec("ping www.baidu.com");
		//Process process=Runtime.getRuntime().exec("netstat -ano");
		Scanner sc=new Scanner(System.in);
		String order=sc.nextLine();
		Process process=Runtime.getRuntime().exec(order);
		InputStreamReader inputStream=new InputStreamReader(process.getInputStream(),"gbk");
		BufferedReader reader=new BufferedReader(inputStream);
		String content=null;
		while((content=reader.readLine())!=null) {
			System.out.println(content);
		}
	}
}
