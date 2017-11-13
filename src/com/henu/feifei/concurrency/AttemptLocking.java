package com.henu.feifei.concurrency;
/**
	*@ClassName:AttemptLocking
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月13日-下午3:04:51
	*@version:1.0
	*/

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock=new ReentrantLock();
	public void untimed() {
		boolean captured=lock.tryLock();
		try {
			System.out.println("trylock() : "+captured);
		} finally{
			if(captured) {
				lock.unlock();
			}
			// TODO: handle exception
		}
	}
	
	public void timed() {
		boolean captured=false;
		try {
			captured=lock.tryLock(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new RuntimeException();
			// TODO: handle exception
		}
		
		try {
			System.out.println("trylock(2,TimeUNit):"+captured);
		} finally{
			if(captured) {
				lock.unlock();
			}
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		final AttemptLocking al=new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread() {
			{setDaemon(true);}
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		al.untimed();
		al.timed();
	}
}
