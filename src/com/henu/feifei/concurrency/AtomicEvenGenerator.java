package com.henu.feifei.concurrency;
/**
	*@ClassName:MuteEvenGenerator
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月14日-下午8:51:29
	*@version:1.0
	*/

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends Intgenerator{
	private AtomicInteger currentEvenValue=new AtomicInteger(0);
	public int next() {
		return currentEvenValue.addAndGet(2);
	}
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
