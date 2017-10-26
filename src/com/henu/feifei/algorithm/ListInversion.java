package com.henu.feifei.algorithm;
/**
	*@ClassName:ListInversion
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月26日-下午8:36:45
	*@version:1.0
	*/

public class ListInversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {2,3,8,6,1};
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if(arr[i]>arr[j]&&i<j) {
					System.out.println(arr[i]+"======="+arr[j]);
				}
			}
		}
		
	}
	
}
