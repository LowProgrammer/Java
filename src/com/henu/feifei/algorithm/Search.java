package com.henu.feifei.algorithm;

import static com.henu.feifei.utils.Print.*;

/**
	*@ClassName:Search
	*@Description:查找方法
	*@author:feifei
	*@date :2017年10月26日-下午7:49:28
	*@version:1.0
	*/
public class Search {
	
	public static void main(String[] args) {
		int[] A= {1,2,3,4,5,6,7,8,9,10};
		twoDivid(A, 0, 8, 1);
		twoDivid(A, 0, 8, 2);
		twoDivid(A, 0, 8, 3);
		twoDivid(A, 0, 8, 4);
		twoDivid(A, 0, 8, 5);
		twoDivid(A, 0, 8, 6);
		twoDivid(A, 0, 8, 7);
		twoDivid(A, 0, 9, 11);
		
	}
	
	//二分查找--递归实现
	public static void twoDivid(int[] arr,int start,int end,int value) {
		int mid=(start+end)/2;
		if(value<arr[start]||value>arr[end]) {
			print("输入数值不在数组中");
			return;
		}
		if(value==arr[mid]) {
			print(mid+1);
		}else if(value==arr[end]){//剩余两个数时，可能是后面那个值，会陷入死循环
			print(end+1);
		}else {
			if(value>arr[mid]) {
				twoDivid(arr, mid, end, value);
			}else {
				twoDivid(arr, start, mid, value);
			}
			
		}
	}
}
