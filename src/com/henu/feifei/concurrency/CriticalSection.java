package com.henu.feifei.concurrency;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
	*@ClassName:CriticalSection
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月16日-下午12:54:18
	*@version:1.0
	*/
class Pair{
	private int x,y;
	public Pair(int x,int y) {
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}
	public Pair() {
		// TODO Auto-generated constructor stub
		this(0, 0);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void incrementX() {
		x++;
	}
	public void incrementY() {
		y++;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: "+x+" y: "+y;
	}
	public class PairValueNotEqualException extends RuntimeException{
		public PairValueNotEqualException() {
			// TODO Auto-generated constructor stub
			super("Apir values not equal"+Pair.this);
		}
	}
	public void CheckState() {
		if(x!=y) {
			throw new PairValueNotEqualException();
		}
	}
}
abstract class PairManager{
	AtomicInteger checkCounter=new AtomicInteger(0);
	protected Pair p=new Pair();
	private java.util.List<Pair> storage=Collections.synchronizedList(new ArrayList<Pair>());
	public synchronized Pair getPair() {
		return new Pair(p.getX(),p.getY());
	}
	
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public abstract void increment();
}
class PairManager1 extends PairManager{
	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}
class PairManager2 extends PairManager{
	@Override
	public void increment() {
		// TODO Auto-generated method stub
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp=getPair();
		}
		store(temp);
	}
}
class PairManipulator implements Runnable{
	private PairManager pm;
	public PairManipulator(PairManager manager) {
		// TODO Auto-generated constructor stub
		this.pm=manager;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			pm.increment();
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pair: "+pm.getPair()+" checkerCounter = "+pm.checkCounter.get();
	}
}
class PairChecker implements Runnable{
	private PairManager pm;
	public PairChecker(PairManager pm) {
		// TODO Auto-generated constructor stub
		this.pm=pm;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().CheckState();
		}
	}
}
public class CriticalSection {
	static void testApproaches(PairManager pman1,PairManager pman2) {
		ExecutorService exec=Executors.newCachedThreadPool();
		PairManipulator
			pm1=new PairManipulator(pman1),
			pm2=new PairManipulator(pman2);
		PairChecker
			pChecker1=new PairChecker(pman1),
			pChecker2=new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pChecker1);
		exec.execute(pChecker2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sleep interrupted");
		}
		System.out.println("pm1 : "+pm1+"\npm2 : "+pm2);
		System.exit(0);
	}
	public static void main(String[] args) {
		PairManager
			pama1=new PairManager1(),
			pama2=new PairManager2();
		testApproaches(pama1, pama2);
	}
}
