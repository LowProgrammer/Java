package com.henu.feifei.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
	*@ClassName:AtomicIntegerTest
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月14日-下午8:40:36
	*@version:1.0
	*/
public class AtomicIntegerTest implements Runnable{
	private AtomicInteger i=new AtomicInteger(10);
	public int getVAlue() {
		return i.get();
	}
	public void evenIncrement() {
		i.addAndGet(2);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			evenIncrement();
			//System.out.println(getVAlue());
		}
	}
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.err.println("aborting");
				System.exit(0);
			}
		}, 5000);
		ExecutorService exec=Executors.newCachedThreadPool();
		AtomicIntegerTest ait=new AtomicIntegerTest();
		exec.execute(ait);
		while(true) {
			int val=ait.getVAlue();
			if(val%2!=0) {
				System.out.println(12);
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
