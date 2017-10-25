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
		int[] arr2= {5,2,4,7,1,3,2,6};
		Sort sortInsert=new Sort();
		//sortInsert.insertSort(arr);
		//sortInsert.mergeInsert(arr);
		sortInsert.merage(arr2, 0, arr2.length);
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
	
	public void mergeInsert(int[] arr,int p,int q,int r) {
		int[] ss=arr;
		int n1=q-p+1;
		int n2=r-q;
		int[] larr=new int[n1];
		int[] rarr=new int[n2];
		for(int i=0;i<n1;i++) {
			larr[i]=arr[p+i];
		}
		for(int i=0;i<n2;i++) {
			rarr[i]=arr[q+i+1];
		}
		int a=0;
		int b=0;
		for(int i=p;i<r+1;i++) {
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
	public void merage(int[] arr,int p,int r) {
		if(p<r) {
			int q=(p+r)/2;
			System.out.println(p+"<=====>"+q+"<=======>"+r);
			merage(arr, p, q);
			merage(arr, q+1, r);
			mergeInsert(arr, p, q, r);
		}
	}
}
