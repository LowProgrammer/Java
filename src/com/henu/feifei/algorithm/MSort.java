package com.henu.feifei.algorithm;
/**
	*@ClassName:MSort
	*@Description:归并排序---递归实现
	*@author:feifei
	*@date :2017年10月26日-下午1:53:56
	*@version:1.0
	*/
import static com.henu.feifei.algorithm.Sort.*;
public class MSort {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {1,3,2,4,7,5,15,10,100,56};
		print(arr);
		merage(arr, 0,9);
		print(arr);
//		int[] s= {4,1};
//		mergeInsert(s,0,0,1);
 	}
	public static void mergeInsert(int[] arr,int p,int q,int r) {
		int len=r-p+1;//0-------1-----------3
		int a=q-p+1;
		int b=len-a;
		int[] arrL=new int[a];
		int[] arrR=new int[b];
		for(int i=0;i<a;i++) {
			arrL[i]=arr[p+i];
		}
		for(int i=0;i<b;i++) {
			arrR[i]=arr[q+1+i];
		}
		int m=0,n=0;
		for(int i=p;i<r+1;i++) {
			if (m<a&&n<b) {
				if(arrL[m]<arrR[n]) {
					arr[i]=arrL[m];
					m++;
				}else {
					arr[i]=arrR[n];
					n++;
				}
			}
			else {
				if(m==a&&n<b) {
					arr[i]=arrR[n];
					n++;
				}
				if(n==b&&m<a) {
					arr[i]=arrL[m];
					m++;
				}
			}
			
		}
		print(arr);
	}

	public static void merage(int[] arr,int p,int r) {
		if(p<r) {
			
			int q=(p+r)/2;
			System.out.println(p+"<=====>"+q+"<=======>"+r);
			merage(arr, p, q);
			merage(arr, q+1, r);
			mergeInsert(arr, p, q, r);
		}
		//print(arr);
	}
}
