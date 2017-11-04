package com.henu.feifei;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * @ClassName:IO
 * @Description:输入输出流
 * @author:feifei
 * @date :2017年11月4日-上午9:54:21
 * @version:1.0
 */
public class IOTest {
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String string;
		StringBuilder sb = new StringBuilder();
		while ((string = in.readLine()) != null) {
			sb.append(string + "\n");
		}
		in.close();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		// System.out.println(read("IO.java"));

		// StringReader stringReader=new StringReader(IO.read("IO.java"));
		// int c;
		// while ((c=stringReader.read())!=-1) {
		// System.out.println((char)c);
		// }
		
		
//		File stream=new File("data.txt");
//		System.out.println(stream.isFile());
//		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
//		out.writeDouble(3.1415926);
//		out.writeUTF("That is pi");
//		out.writeDouble(1.41413);
//		out.writeUTF("Square root of 2 中文");
//		out.close();
//		
//		
//		DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
//		System.out.println(inputStream.readDouble());
//		System.out.println(inputStream.readUTF());
//		System.out.println(inputStream.readDouble());
//		System.out.println(inputStream.readUTF());
//		
//		
//		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
//		String string;
//		while((string=stdin.readLine())!=null&&string.length()!=0) {
//			System.out.println(string);
//		}
		
		
		PrintStream console=System.out;
		BufferedInputStream in=new BufferedInputStream(new FileInputStream("IOTest.java"));
		PrintStream out1=new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
		System.setIn(in);
		System.setOut(out1);
		System.setErr(out1);
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
		String string2;
		while((string2=bufferedReader.readLine())!=null) {
			System.out.println(string2);
		}
		out1.close();
		System.out.println(console);
	}
}
