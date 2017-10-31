package com.henu.feifei.algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.BreakIterator;
import java.util.Scanner;

public class Vijos {
	//两个大数相加BigDemimal
	public static void aaa() {
		        Scanner scanner = new Scanner(System.in);   
		        String temp1=null;   
		        String temp2=null;   
		        String result=null;   
		        int i; 

		        int a=scanner.nextInt();   
		        for(i=0;i<a;i++){    
		            temp1=scanner.next();    
		            temp2=scanner.next(); 
		            BigDecimal bigdecimal=new BigDecimal(temp1);    
		            BigDecimal bigdecimal2=new BigDecimal(temp2);    

		            result=bigdecimal.add(bigdecimal2).toString();  
		            if(i!=(a-1)) {
		                System.out.println("Case"+" "+
		                (i+1)+":\r\n"+bigdecimal+" + "+bigdecimal2+" = "+result+"\r\n");
		            } 
		            else {
		                System.out.println("Case"+" "+(i+1)
		                +":\r\n"+bigdecimal+" + "+bigdecimal2+" = "+result);
		            }   
		        }      
	}
	public static void main(String[] args) {
		aaa();
		// System.out.println(Integer.MAX_VALUE);
		// System.out.println(Long.MAX_VALUE);
		Scanner scanner = new Scanner(System.in);
		byte num = scanner.nextByte();
		String[] arr = new String[num + 1];
		int i = 0;
		while (scanner.hasNext()) {
			arr[i++] = scanner.nextLine();
			if (num + 1 == i)
				break;
		}
		for (int j2 = 1; j2 < arr.length; j2++) {
			long a = Long.parseLong(arr[j2].split(" ")[0]);
			long b = Long.parseLong(arr[j2].split(" ")[1]);
			StringBuilder builder = new StringBuilder();
			builder.append("Case ")
					.append(j2)
					.append(":\n")
					.append(a)
					.append(" + ")
					.append(b)
					.append(" = ")
					.append(a + b).append("\n");
			System.out.println(builder);
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
