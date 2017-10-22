package com.henu.feifei.algorithm;
/**
 * descri 插入排序
 * @author Administrator
 *
 */
public class sortInsert {
	public static void main(String[] args) {
//		int[] arr= {12,25,26,12,23,24,28,50,64,57,41,68,91};
		//int[] arr= {36,25,26,12,23,24,28,50,64,57,41,68,91,5};
		int[] arr= {10,9,8,7,6,5,4,3,2,1,0};
		sortInsert sortInsert=new sortInsert();
		sortInsert.insertSort(arr);
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
}
