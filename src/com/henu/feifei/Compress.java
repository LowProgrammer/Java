package com.henu.feifei;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.henu.feifei.utils.Print;

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
		String[] string= {"test.txt","data.txt"};
		//cs.GZIPcompress(string);
		cs.ZIPcompress(string,"test.zip");
	}
	//使用gzip进行简单压缩
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
		BufferedOutputStream out=new BufferedOutputStream(
				new GZIPOutputStream(
						new FileOutputStream("test.gz")));
		System.out.println(" writing file");
		int c;
		while((c=in.read())!=-1) {
			out.write(c);
		}
		in.close();
		out.close();
		System.out.println("reading file");
		BufferedReader in2=new BufferedReader(
				new InputStreamReader(
						new GZIPInputStream(
								new FileInputStream("test.gz"))));
		String s;
		while((s=in2.readLine())!=null) {
			System.out.println(s);
		}
	}
	//用zip进行多文件压缩
	//arr:需要进行压缩的文件 name:压缩文件名称
	public void ZIPcompress(String[] arr,String name)throws Exception {
		FileOutputStream f=new FileOutputStream(name);
		CheckedOutputStream csum=new CheckedOutputStream(f,new Adler32());
		ZipOutputStream zos=new ZipOutputStream(csum);
		BufferedOutputStream out=new BufferedOutputStream(zos);
		zos.setComment("A test of java zipping");
		for(String a:arr) {
			Print.print("writing file "+a);
			BufferedReader in=new BufferedReader(new FileReader(a));
			zos.putNextEntry(new ZipEntry(a));
			int c;
			while((c=in.read())!=-1) {
				out.write(c);
			}
			in.close();
			out.flush();
		}
		out.close();
		Print.print("checksum: "+csum.getChecksum().getValue());
		Print.print("reading file");
		FileInputStream fi=new FileInputStream(name);
		CheckedInputStream csumi=new CheckedInputStream(fi,new Adler32());
		ZipInputStream in2=new ZipInputStream(csumi);
		BufferedInputStream bis=new BufferedInputStream(in2);
		ZipEntry ze;
		while((ze=in2.getNextEntry())!=null) {
			Print.print("reading file  "+ze);
			int x;
			while((x=bis.read())!=-1) {
				System.out.write(x);
			}
		}
		if(arr.length==1) {
			Print.print("checksum"+csumi.getChecksum().getValue());
		}
		bis.close();
		ZipFile zf=new ZipFile(name);
		Enumeration e=zf.entries();
		while(e.hasMoreElements()) {
			ZipEntry ze2=(ZipEntry)e.nextElement();
			Print.print("file: "+ze2);
		}
	}
}
