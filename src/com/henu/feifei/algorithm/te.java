package com.henu.feifei.algorithm;
import java.util.ArrayList;
import java.util.Scanner;
public class te {
		public static void main(String[] args){
	             Scanner scanner=new Scanner(System.in);
			int num=scanner.nextInt();
			for (int i = 0; i < num; i++) {
				long start=scanner.nextLong();
				ArrayList<Integer> arr=new ArrayList<>(); 
				for (int j = 0; j <start; j++) {
					arr.add(scanner.nextInt());
				}
				int temp_max=0;
				int sums=0;
				int start_sub=0;
				int end_sub=0;
				for (int j = 1; j < arr.size(); j++) {
					
				}
				if(i<(num-1))
					System.out.println("Case "+(i+1)+":\n"+temp_max+" 1 "+end_sub+"\n");
				else
					System.out.println("Case "+(i+1)+":\n"+temp_max+" 1 "+end_sub);
			}
			
	}
	public static int compare(int a,int b) {
		 		return a>b?a:b;
	}     
}
