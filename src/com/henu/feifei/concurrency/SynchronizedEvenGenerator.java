package com.henu.feifei.concurrency;
/**
	*@ClassName:SynchronizedEvenGenerator
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月13日-下午2:19:11
	*@version:1.0
	*/
public class SynchronizedEvenGenerator extends Intgenerator{
	private int currentEvenvalue=0;
	@Override
	public synchronized int next() {
		// TODO Auto-generated method stub
		++currentEvenvalue;
		Thread.yield();
		++currentEvenvalue;
		return currentEvenvalue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
