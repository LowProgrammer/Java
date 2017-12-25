package com.henu.feifei.algorithm;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
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
	}
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
}