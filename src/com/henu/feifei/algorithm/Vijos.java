package com.henu.feifei.algorithm;

import java.math.BigInteger;
import java.text.BreakIterator;
import java.util.Scanner;

public class Vijos {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
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
