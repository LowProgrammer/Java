package com.henu.feifei.concurrency;
/**
	*@ClassName:ExpliciticalSection
	*@Description:显示的lock创建临界区
	*@author:feifei
	*@date :2017年11月16日-下午2:55:43
	*@version:1.0
	*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ExpliciticalManager1 extends PairManager{
	private Lock lock=new ReentrantLock();
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
}
class ExpliciticalManager2 extends PairManager{
	private Lock lock=new ReentrantLock();
	@Override
	public void increment() {
		// TODO Auto-generated method stub
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp=getPair();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
}
public class ExpliciticalSection {
	public static void main(String[] args) {
		PairManager
			pama1=new ExpliciticalManager1(),
			pama2=new ExpliciticalManager2();
		CriticalSection.testApproaches(pama1, pama2);
	}
}
