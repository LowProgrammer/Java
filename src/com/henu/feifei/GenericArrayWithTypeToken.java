package com.henu.feifei;

import java.lang.reflect.Array;

/**
	*@ClassName:GenericArrayWithTypeToken
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月21日-下午7:36:19
	*@version:1.0
	*/
public class GenericArrayWithTypeToken<T> {
	private T[] array;
	public GenericArrayWithTypeToken(Class<T> type,int sz) {
		array=(T[])Array.newInstance(type, sz);
		
	}
	public void put(int index,T item) {
		array[index]=item;
	}
	public T get(int index) {
		return array[index];
	}
	public T[] rep() {
		return array;
	}
	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gai=new GenericArrayWithTypeToken<Integer>(Integer.class, 10);
		Integer[] ia=gai.rep();
		int index=0;
		for(Integer i:ia) {
			gai.put(index++, 1);
		}
		for(Integer i:ia) {
			System.out.println(i);
		}
	}
}
