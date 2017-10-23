package com.henu.feifei.algorithm;
/**
 * descri 插入排序
 * @author Administrator
 *
 */
public class Sort {
	public static void main(String[] args) {
//		int[] arr= {12,25,26,12,23,24,28,50,64,57,41,68,91};
		//int[] arr= {36,25,26,12,23,24,28,50,64,57,41,68,91,5};
		//int[] arr= {10,9,8,7,6,5,4,3,2,1,0};
		int[] arr= {6,7,8,9,10,0,1,2,3,4,5};
		Sort sortInsert=new Sort();
		//sortInsert.insertSort(arr);
		sortInsert.mergeInsert(arr);
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
	
	public void mergeInsert(int[] arr) {
		int[] ss=arr;
		int len=arr.length;
		int medium=len/2;
		int[] larr=new int[medium];
		int[] rarr=new int[len-medium];
		for(int i=0;i<medium;i++) {
			larr[i]=arr[i];
		}
		for(int i=medium;i<len;i++) {
			rarr[i-medium]=arr[i];
		}
		int a=0;
		int b=0;
		for(int i=0;i<len;i++) {
			if(a<larr.length&&b<rarr.length) {
				if(larr[a]<=rarr[b]) {
					ss[i]=larr[a];
					a++;
				}else{
					ss[i]=rarr[b];
					b++;
				}
			}else {
				if(!(a<larr.length)) {
					ss[i]=rarr[b];
					b++;
				}else {
					ss[i]=larr[a];
					a++;
				}
			}
		}
		for(int s:ss) {
			System.out.println(s);
		}
	}
}
