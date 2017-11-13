package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
	*@ClassName:Atomicity
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月13日-下午3:52:59
	*@version:1.0
	*/
public class AtomicityTest implements Runnable{
	private int i=0;
	public int getValue() {
		return i;
	}
	private synchronized void evenIncrement() {
		i++;i++;
	} 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool();
		AtomicityTest at=new AtomicityTest();
		exec.execute(at);
		while(true) {
			TimeUnit.MILLISECONDS.sleep(10);
			int val=at.getValue();
			if(val%2!=0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
