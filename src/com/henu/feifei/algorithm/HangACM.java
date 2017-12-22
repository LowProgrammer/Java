package com.henu.feifei.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HangACM {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		
		//求最大子数组
		//int arr[]={};
//		int start=0,end=0,temp=0,max=0;
//		for(int i=0;i<arr.length;i++) {
//			temp+=arr[i];
//			if(temp<0) {
//				start=i+1;
//				temp=0;
//			}
//			if(temp>max) {
//				max=temp;
//				end=i;
//			}
//		}
//		System.out.println(start+"===="+end);
		
//		while((string=scanner.nextLine())!="") {
//			String[] num=string.split(" ");
//			int a=Integer.parseInt(num[0]);
//			int b=Integer.parseInt(num[1]);
//			for(int i=1;i<b;i++) {
//				System.out.print(i+" ");
//			}
//			String str="";
//			for(int j=a;j>=b;j--) {
//				if((j-a)%2==0) {					
//					str+=j+" ";
//				}
//				else {
//					str=j+" "+str;
//				}
//			}
//			System.out.println(str);
//		}
	}
	/**
	 * 1004 let the ballon Rise
	 */
	
	public void maxNumOfArr() {
		Scanner scanner=new Scanner(System.in);
		int N;
		while((N=scanner.nextInt())!=0){
			String[] arr=new String[N];
			for(int i=0;i<N;i++) {
				arr[i]=scanner.next();
			}
			int maxNum=0;
			String key = null;
			for (int i = 0; i < arr.length; i++) {
				int max=0;
				for (int j = i; j < arr.length; j++) {
					if(arr[j].equals(arr[i])) {
						max++;
					}
				}
				if(max>maxNum) {
					key=arr[i];
					maxNum=max;
				}
			}
			System.out.println(key);
		}
	}
	//1003求最大子数组
	public void maxSum() {
		Scanner scanner=new Scanner(System.in);
		int lines=Integer.parseInt(scanner.nextLine());
		for(int i=0;i<lines;i++) {
			String string=scanner.nextLine();
			String[] as=string.split(" ");
			int[] arr=new int[Integer.parseInt(as[0])];
			for(int j=1;j<as.length;j++) {
				arr[j-1]=Integer.parseInt(as[j]);
			}
			int start=0,end=0,temp=0,max=0;
			for(int m=0;m<arr.length;m++) {
				temp+=arr[m];
				if(temp<0) {
					start=m+1;
					temp=0;
				}
				if(temp>max) {
					max=temp;
					end=m;
				}
			}
			System.out.println("Case "+(i+1)+":");
			System.out.println(max+" "+(start+1)+" "+(end+1));
			if(i<lines-1) {System.out.println();}
		}
	}
	public void safeCraker() {
		Scanner scanner=new Scanner(System.in);
		String string;
		while(!(string=scanner.nextLine()).equals(null)) {
			String[] arr=string.split(" ");
			int target=Integer.parseInt(arr[0]);
			char[] a=arr[1].toCharArray();
			boolean re=false;
			int stand='A'-1;
			String result="";
			for(int i=0;i<a.length;i++) {
				for (int j = 0; j < a.length; j++) {
					if(i!=j)
					for (int j2 = 0; j2 < a.length; j2++) {
						if(j2!=j&&j2!=i)
						for (int k = 0; k < a.length; k++) {
							if(k!=j2&&k!=j&&k!=i)
							for (int k2 = 0; k2 < a.length; k2++) {
								if(k2!=k&&k2!=i&&k2!=j&&k2!=j2)
								if(((int)a[i]-stand-((int)a[j]-stand)^2+((int)a[j2]-stand)^3-((int)a[k]-stand)^4+((int)a[k2]-stand)^5)==target) {
									result=a[i]+""+a[j]+a[j2]+a[k]+a[k2];
									re=true;
								}
								
							}
						}
					}
				}
			}
			if(!re)System.out.println("no solution");
			else {
				System.out.println(result);
			}
		}
		
	}
	/**
	 * 1098
	 */
	public void Ignatius() {
		Scanner scanner=new Scanner(System.in);
		int a;
		while(scanner.hasNext()) {
			a=scanner.nextInt();
			boolean re=false;
			for (int i = 1; i<=65; i++) {
				if((18+i*a)%65==0) {
					System.out.println(i);
					re=true;
				}
			}
			if(!re) {
				System.out.println("no");
			}
			
		}
		//
		
	}
	/**
	 * 1008
	 */
	public void elevator()
	{
		//上升为6 中间停止6 下降4 开头第一个数为数量N
		//计算总时间
		Scanner scanner=new Scanner(System.in);
		int up=6,stop=5,down=4;
		String str;
		while((str=scanner.nextLine())!="0") {	
			String[] arr=str.split(" ");
			if(Integer.parseInt(arr[0])==0)break;
			int sum=0;
			for(int i=1;i<1+Integer.parseInt(arr[0]);i++) {
				if(i==1)
				{
					sum=sum+Integer.parseInt(arr[1])*up;
				}else {
					if(Integer.parseInt(arr[i])>Integer.parseInt(arr[i-1])) {
						sum+=(Integer.parseInt(arr[i])-Integer.parseInt(arr[i-1]))*up;
					}else { 
						sum+=(Integer.parseInt(arr[i-1])-Integer.parseInt(arr[i]))*down;
					}
				}
				sum=sum+stop;
			}
			System.out.println(sum);
		}
	}
}
