package com.henu.feifei;
/**
	*@ClassName:GenericArray2
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月21日-下午6:21:19
	*@version:1.0
	*/
public class GenericArray2<T> {
	private Object[] array;
	public GenericArray2(int sz) {
		array=new Object[sz];
	}
	public void put(int index,T item) {
		array[index]=item;
	}
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)array[index];
	}
	@SuppressWarnings("unchecked")
	public T[] rep() {
		return (T[])array;
	}
	public static void main(String[] args) {
		GenericArray2<Integer> gai=new GenericArray2<Integer>(10);
		int length=10;
		for(int i=0;i<length;i++) {
			gai.put(i, i);
		}
		for(int i=0;i<length;i++) {
			System.out.print
			(gai.get(i)+"  ");
		}
		System.out.println();
		Integer[] ia=gai.rep();
	}
 }
