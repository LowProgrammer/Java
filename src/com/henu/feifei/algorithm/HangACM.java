package com.henu.feifei.algorithm;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HangACM {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext()){			
			int degree=scanner.nextInt();
			double sec=1,min=1.0/60,hour=1.0/120;
			int counter=0;
			for(int i=0;i<12*60*60;i++) {
				if(IsHappy(i, degree))counter++;
			}
			 DecimalFormat df = new DecimalFormat("0.000");
			 df.setRoundingMode(RoundingMode.HALF_UP);
			System.out.println(df.format(counter*1.0/(43200)*100));
		}
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
	public void tickAndTickOfTrue() {
		Scanner s=new Scanner(System.in);
        while(true){
            double D=s.nextDouble();
            if(D==-1)
                break;
            double mhmin[]=new double[11];
            double mhmax[]=new double[11];
            for(int i=0;i<11;i++){
                mhmin[i]=(360*i+D)*120/11;
                mhmax[i]=(360*i+360-D)*120/11;
            }
            double smmin[]=new double[708];
            double smmax[]=new double[708];
            for(int i=0;i<708;i++){
                smmin[i]=(360*i+D)/5.9;
                smmax[i]=(360*i+360-D)/5.9;
            }
            double shmin[]=new double[719];
            double shmax[]=new double[719];
            for(int i=0;i<719;i++){
                shmin[i]=(360*i+D)*120/719;
                shmax[i]=(360*i+360-D)*120/719;
            }
            double countTime=0;
            for(int i=0;i<11;i++){
                for(int j=0;j<708;j++){
                    if(smmin[j]<mhmax[i]&&smmax[j]>mhmin[i]){
                        for(int k=0;k<719;k++){
                            if(shmin[k]<smmax[j]&&shmax[k]>smmin[j]){
                                if(shmin[k]<mhmax[i]&&shmax[k]>mhmin[i]){
                                    double min=Max(shmin[k],smmin[j],mhmin[i]);
                                    double max=Min(shmax[k],smmax[j],mhmax[i]);
                                    countTime=countTime+max-min;
                                }
                            }
                        }
                    }
                }
            }
            double percent=countTime/432;
            System.out.printf("%.3f", percent);
            System.out.println();
            
        }
    }
	/**
	 * 三个数中最小值
	 * @param d
	 * @param e
	 * @param f
	 * @return
	 */
	private static double Min(double d, double e, double f) {
		// TODO Auto-generated method stub
		double min=0;
		min=d>e?e:d;
		min=min>f?f:min;
		return min;
	}
	/**
	 * 三个数中最大值
	 * @param d
	 * @param e
	 * @param f
	 * @return
	 */
	private static double Max(double d, double e, double f) {
		// TODO Auto-generated method stub
		double max=0;
		max=d>e?d:e;
		max=max>f?max:f;
		return max;
	}
	/**
	 * 1006(存在误差)
	 */
	public void tickAndTick() {
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext()){			
			int degree=scanner.nextInt();
			double sec=1,min=1.0/60,hour=1.0/120;
			int counter=0;
			for(int i=0;i<12*60*60;i++) {
				if(IsHappy(i, degree))counter++;
			}
			 DecimalFormat df = new DecimalFormat("0.000");
			 df.setRoundingMode(RoundingMode.HALF_UP);
			System.out.println(df.format(counter*1.0/(43200)*100));
		}
	}
	/**
	 * 1006 判断是否大于standard N秒时(存在错误)
	 * @param N
	 * @param standard
	 * @return
	 */
	public static boolean IsHappy(int N,int standard) {
		double a1=N%360,a2=N*(1.0/60)%360,a3=N*(1.0/120)%360;
		boolean result=false;
		if(a1<a2&&a2<a3) {
			if((a2-a1)>=standard&&(a3-a2)>=standard&&(a1+360-a3)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		if(a1<a3&&a3<a2) {
			if((a3-a1)>=standard&&(a2-a3)>=standard&&(a1+360-a2)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		if(a2<a1&&a1<a3) {
			if((a1-a2)>=standard&&(a3-a1)>=standard&&(a2+360-a3)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		if(a2<a3&&a3<a1) {
			if((a3-a2)>=standard&&(a1-a3)>=standard&&(a2+360-a1)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		if(a3<a1&&a1<a2) {
			if((a1-a3)>=standard&&(a2-a1)>=standard&&(a3+360-a2)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		if(a3<a2&&a2<a1) {
			if((a2-a3)>=standard&&(a1-a2)>=standard&&(a3+360-a1)>=standard) {
				result=true;
			}else {
				result=false;
			}
		}
		return result;
	}	
	/**
	 * 1005 完整方法，f(n-1) f(n-2)在AB确定的情况下，周期性变化(7x7)
	 */
	public static void getF() {
		        Scanner in = new Scanner(System.in);  
		        int a, b;  
		        int n;  
		        while (in.hasNext()) {  
		            a = in.nextInt();  
		            b = in.nextInt();  
		            n = in.nextInt();  
		            if (a < 1 & a > 1000 & b < 1 & b > 1000 & b < 1 & a > 100000000)  
		                System.exit(0);  
		            if (a == 0 & b == 0 & n == 0)  
		                System.exit(0);  
		            int f[] = new int[50];  
		            for (int i = 1; i < 50; i++) {  
		                if (i == 1 || i == 2) {  
		                    f[i] = 1;  
		                } else {  
		                    f[i] = (a * f[i - 1] + b * f[i - 2]) % 7;  
		                }  
		            }  
		            System.out.println(f[n % 49]);  
		        }  
		        in.close();  
	}
	/**
	 * 1005 递归
	 * @param A
	 * @param B
	 * @param N
	 * @return
	 */
	public static int getNum(int A,int B,int N) {
		if(N==1)return 1;
		if(N==2)return 1;
		return (A*getNum(A, B, N-1)+B*getNum(A, B, N-2))%7;
	}
	/**
	 * 1005 迭代
	 * @param A
	 * @param B
	 * @param N
	 * @return
	 */
	public static int getNumOfTemp(int A,int B,int N) {
		int result = 0, a1 = 1, a2 = 1;
		if (N == 1) {
			a1 = 1;
			result = 1;
		} else if (N == 2) {
			a2 = 1;
			result = 1;
		} else {
			for (int i = 3; i <= N; i++) {
				result = (a2 * A + a1 * B)%7;
				a1 = a2;
				a2 = result;
			}
		}
		return result;
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
