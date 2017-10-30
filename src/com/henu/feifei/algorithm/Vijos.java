package com.henu.feifei.algorithm;

import java.math.BigInteger;
import java.text.BreakIterator;
import java.util.Scanner;

public class Vijos {
	public static void main(String[] args) {
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Long.MAX_VALUE);
		Scanner scanner = new Scanner(System.in);
		byte num=scanner.nextByte();
		String[] arr=new String[num+1];
		int i=0;
		while(scanner.hasNext()) {
			arr[i++]=scanner.nextLine();
			if(num+1==i)break;
		}
		for (int j2 = 1; j2 < arr.length; j2++) {
			System.out.println("Case "+j2+":");
			long a=Long.parseLong(arr[j2].split(" ")[0]);
			long b=Long.parseLong(arr[j2].split(" ")[1]);
			System.out.println(a+" + "+b+" = "+(a+b));
			System.out.println();
		}
		
	}
	
	
	public static int su(int n) {
		return n==1?1:n+su(n-1);
	}
	//递归求和
	public void sum1() {

		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			 int sum=su(scanner.nextInt());
			 System.out.println(sum);
			 System.out.println();
		}
	}
	//两数相加
	public void sum() {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {				
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
