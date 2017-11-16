package com.henu.feifei.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
	*@ClassName:ThreadLocalVariableHolder
	*@Description:线程本地存储
	*@author:feifei
	*@date :2017年11月16日-下午4:21:05
	*@version:1.0
	*/
class Accessor implements Runnable{
	private int id;
	public Accessor(int idn) {
		// TODO Auto-generated constructor stub
		id=idn;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "#"+id+" : "+ThreadLocalVariableHolder.get();
	}
}
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value=new ThreadLocal<Integer>() {
		private Random rand=new Random(47);
		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};
	public static void increment() {
		value.set(value.get()+1);
	}
	public static int get() {
		return value.get();
	}
	public static void main(String[] args) throws Exception{
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.MILLISECONDS.sleep(3);
		exec.shutdown();
	}
}
