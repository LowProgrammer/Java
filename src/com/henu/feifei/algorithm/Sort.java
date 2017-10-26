package com.henu.feifei.algorithm;

import static com.henu.feifei.algorithm.Sort.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * descri 插入排序
 * @author Administrator
 *
 */
public class Sort {
	public static void main(String[] args) {
		Sort sortInsert=new Sort();
		int[] A= {7,6,5,4,3,2,1,0};
		//sortInsert.insertionSert(A, 7);
		sortInsert.bubbleSort(A);
		print(A);
		
		
	}
	//插入排序
	public void insertSort(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			int key=arr[i];
			int j=i-1;
			while(j>=0&&arr[j]>key) {
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=key;
			
		}
		for(int x:arr) {
			System.out.println(x);
		}
	}
	//插入排序递归实现
	public static void insert(int[] arr,int n) {
		int i=n-1;
		int key=arr[n];
		while(i>=0&&arr[i]>key) {
			arr[i+1]=arr[i];
			i--;
		}
		arr[i+1]=key;
	} 
	public static void insertionSert(int[] arr,int index) {
		if(index>0) {
			insertionSert(arr, index-1);
			insert(arr, index);
		}
	}
	//归并排序--递归
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
	
	//冒泡排序
	public static void bubbleSort(int[] arr) {
		int len=arr.length;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if(arr[i]>arr[j]) {
					arr[i]=arr[i]^arr[j];
					arr[j]=arr[j]^arr[i];
					arr[i]=arr[i]^arr[j];
				}
			}
		}
	}
	
	//一些方法
	public static void print(int[] arr) {
		for(int a:arr) {
			System.out.print(a+"   ");
		}
		System.out.println();
	}
	public static void print(String mess) {
		System.out.println(mess);
	}
	public static void print(int a) {
		System.out.println(a);
	}
	//截取数组
	public static int[] subArr(int[] arr,int begin,int end) {
		int[] temp=new int[end-begin];
		for(int i=begin;i<end;i++) {
			temp[i]=arr[i];
		}
		return temp;
	}
}
