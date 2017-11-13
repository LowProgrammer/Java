package com.henu.feifei.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
	*@ClassName:MutexEvenGenerator
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月13日-下午2:37:04
	*@version:1.0
	*/
public class MutexEvenGenerator extends Intgenerator{
	private int currentEvenValue=0;
	private Lock lock=new ReentrantLock();
	@Override
	public int next() {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			return currentEvenValue;
		} finally {
			lock.unlock();
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}
}
