package com.henu.feifei.algorithm;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.BreakIterator;

import java.util.Scanner;

public class Vijos {
	
	public static void main(String[] args) throws ClassNotFoundException {
			Vijos a=new Vijos();
			a.max();
		
		
//		Class cl=Class.forName("com.henu.feifei.Test");
//		try {
//			Field field=cl.getDeclaredField("age");
//			System.out.println(field.getName());
//			System.out.println(field.getType());
//			System.out.println(field.getModifiers());
//			System.out.println();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public  void max() {
		Scanner scanner=new Scanner(System.in);
		int num=scanner.nextInt();
		
		for (int i = 0; i < num; i++) {
			long start=scanner.nextLong();
			String[] arr=scanner.nextLine().split(" ");
			int temp_max=0;
			int start_sub=0;
			int end=0;
			int sums=0;
			for (int j = 1; j < arr.length; j++) {//null 6 -1 5 4 -7
					sums+=Integer.parseInt(arr[j]);
					temp_max=compare(sums, temp_max);
					if(temp_max==sums) {
						end=j;
					}
			}
			if(i<(num-1))
				System.out.println("Case "+(i+1)+":\n"+temp_max+" 1 "+end+"\n");
			else
				System.out.println("Case "+(i+1)+":\n"+temp_max+" 1 "+end);
		}
		
		
	}
	public int compare(int a,int b) {
		return a>b?a:b;
	}
	public int compare_min(int a,int b) {
		return a>b?b:a;
	}
	
	// 两个大数相加BigDemimal

	public static void aaa() {
		Scanner scanner = new Scanner(System.in);
		String temp1 = null;
		String temp2 = null;
		String result = null;
		int i;
		
		int a = scanner.nextInt();
		for (i = 0; i < a; i++) {
			temp1 = scanner.next();
			temp2 = scanner.next();
			BigDecimal bigdecimal = new BigDecimal(temp1);
			BigDecimal bigdecimal2 = new BigDecimal(temp2);
			
			result = bigdecimal.add(bigdecimal2).toString();
			if (i != (a - 1)) {
				System.out.println(
						"Case" + " " + (i + 1) + ":\r\n" + bigdecimal + " + " + bigdecimal2 + " = " + result + "\r\n");
			} else {
				System.out
				.println("Case" + " " + (i + 1) + ":\r\n" + bigdecimal + " + " + bigdecimal2 + " = " + result);
			}
		}
	}

	public static int su(int n) {
		return n == 1 ? 1 : n + su(n - 1);
	}

	// 递归求和
	public void sum1() {

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			int sum = su(scanner.nextInt());
			System.out.println(sum);
			System.out.println();
		}
	}

	// 两数相加
	public void sum() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String[] arr = scanner.nextLine().trim().split(" ");
			System.out.println(Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]));
		}
		// BigInteger a=new BigInteger(scanner.next());
		// BigInteger b=new BigInteger(scanner.next());
		// System.out.println(a.add(b));

		// Scanner scanner=new Scanner(System.in);
		// Short a=new Short(scanner.next());
		// Short b=new Short(scanner.next());
		// System.out.println((int)(a+b));
	}

}
