package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
	*@ClassName:SerialNumberChecker
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月14日-下午8:16:33
	*@version:1.0
	*/
class CircularSet{
	private int[] array;
	private int len;
	private int index=0;
	public CircularSet(int size) {
		// TODO Auto-generated constructor stub
		array=new int[size];
		len=size;
		for(int i=0;i<size;i++) {
			array[i]=-1;
		}
	}
	public synchronized void add(int i) {
		array[index]=i;
		index=++index%len;
	}
	public synchronized boolean contains(int val) {
		for(int i=0;i<len;i++) {
			if(array[i]==val) {
				return true;
			}
		}
		return false;
	}
}
public class SerialNumberChecker {
	public static final int SIZE=10;
	private static CircularSet serials=new CircularSet(1000);
	private static ExecutorService exec=Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				int serial=SerialNumberGenerator.nextSerialNumber();
				if(serials.contains(serial)) {
					System.out.println("Douplicate:"+serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
		
	}
	public static void main(String[] args)throws Exception {
		for(int i=0;i<SIZE;i++) {
			exec.execute(new SerialChecker());
		}
		if(args.length>0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("NO duplicates detected");
			System.exit(0);
		}
	}
}
