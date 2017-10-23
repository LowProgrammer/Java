package com.henu.feifei.utils;

import java.util.Iterator;
import java.util.LinkedList;

/**
	*@ClassName:SimpleQueue
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月23日-下午2:09:29
	*@version:1.0
	*/
public class SimpleQueue<T> implements Iterable<T>{
	private LinkedList<T> storage=new LinkedList<T>();
	public void add( T t){
		storage.offer(t);
	}
	public T get() {
		return storage.poll();
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return storage.iterator();
	}
	
}
