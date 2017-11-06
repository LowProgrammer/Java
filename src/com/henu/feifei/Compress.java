package com.henu.feifei;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
	*@ClassName:Compress
	*@Description:文件压缩功能的实现
	*@author:feifei
	*@date :2017年11月6日-下午1:07:06
	*@version:1.0
	*/
public class Compress {
	public static void main(String[] args)throws Exception {
		Compress cs=new Compress();
		String[] string= {"test.txt"};
		cs.GZIPcompress(string);
	}
	public void GZIPcompress(String[] arr)throws Exception {
		if(arr.length==0) {
			System.out.println(
					"Usage: \nGZIPcompress file\n"+
					"\tUses GZIP compression to compress  "+
					"the file to test.gz"
					);
			System.exit(1);
		}
		
		BufferedReader in=new BufferedReader(new FileReader(arr[0]));
		BufferedOutputStream out=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
		System.out.println(" writing file");
		int c;
		while((c=in.read())!=-1) {
			out.write(c);
		}
		in.close();
		out.close();
		System.out.println("reading file");
		BufferedReader in2=new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
		String s;
		while((s=in2.readLine())!=null) {
			System.out.println(s);
		}
	}
}
