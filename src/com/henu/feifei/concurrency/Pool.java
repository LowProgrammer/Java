package com.henu.feifei.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T>{
	private int size;
	private List<T> items=new ArrayList<>();
	private volatile boolean[] checkout;
	private Semaphore available;
	public Pool(Class<T> classObject,int size){
		this.size=size;
		checkout=new boolean[size];
		available=new Semaphore(size,true);
		for (int i = 0; i < size; i++) {
			try {
				items.add(classObject.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public T checkOut() throws InterruptedException{
		available.acquire();
		return getItem();
	}
	public void checkIn(T x){
		if(releaseItem(x)){
			available.release();
		}
	}
	private synchronized T getItem(){
		for (int i = 0; i < size; i++) {
			if(!checkout[i]){
				checkout[i]=true;
				return items.get(i);
			}
			
		}
		return null;
	}
	private synchronized boolean releaseItem(T item){
		int index=items.indexOf(item);
		if(index==-1)return false;
		if(checkout[index]){
			checkout[index]=false;
			return true;
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
