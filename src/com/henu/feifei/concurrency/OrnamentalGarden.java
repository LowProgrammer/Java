package com.henu.feifei.concurrency;
/**
	*@ClassName:OrnamentalGarden
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月16日-下午4:40:03
	*@version:1.0
	*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.henu.feifei.utils.Print;

class Count{
	private int count=0;
	private Random rand=new Random(47);
	public synchronized int increment() {
		int temp=count;
		if(rand.nextBoolean()) {
			Thread.yield();
		}
		return (count= ++temp);
	}
	public synchronized int value() {
		return count;
	}
}
class Entrance implements Runnable{
	private static Count count=new Count();
	private static List<Entrance> entrances=new ArrayList<>();
	private int number=0;
	private final int id;
	private static volatile boolean canceled=false;
	public static void cancel() {
		canceled=true;
	}
	public Entrance(int id) {
		this.id=id;
		// TODO Auto-generated constructor stub
		entrances.add(this);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!canceled) {
			synchronized (this) {
				++number;
			}
			Print.print(this+" total : "+count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (Exception e) {
				Print.print("sleep interrupted");
				// TODO: handle exception
			}
		}
		Print.print("stopping  "+this);
	}
	
	public synchronized int getValue() {
		return number;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Entrance "+id+" : "+getValue();
	}
	
	public static int getTotalCount() {
		return count.value();
	}
	
	public static int sumEntrance() {
		int sum=0;
		for(Entrance entrance:entrances) {
			sum+=entrance.getValue();
		}
		return sum;
	}
}
public class OrnamentalGarden {
	public static void main(String[] args)throws Exception {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.MILLISECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			Print.print("some tasks were not terminated!");
		}
		Print.print("Total:"+Entrance.getTotalCount());
		Print.print("Sum of Entrance:"+Entrance.sumEntrance());
	}
}
