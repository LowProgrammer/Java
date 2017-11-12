package com.henu.feifei.concurrency;

import com.henu.feifei.utils.Print;

class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name,int sleeptime) {
		super(name);
		this.duration=sleeptime;
		start();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sleep(duration);
		} catch (Exception e) {
			Print.print(getName()+" was interrupted ,"+"isInterrupted():"+isInterrupted());
			// TODO: handle exception
		}
		Print.print(getName()+" has awakened");
	}
}
class Joiner extends Thread{
	private Sleeper sleeper;
	public Joiner(String name,Sleeper sleeper) {
		super(name);
		this.sleeper=sleeper;
		start();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sleeper.join();
		} catch (Exception e) {
			Print.print("interrupted");
			// TODO: handle exception
		}
		Print.print(getName()+" join completed");
	}
}
public class Joining {
	public static void main(String[] args) {
		Sleeper
			sleepy=new Sleeper("sleepy", 1500),
			grumpy=new Sleeper("grumpy", 2500);
		Joiner
			dopey=new Joiner("Dopey", sleepy),
			doc=new Joiner("Doc",grumpy);
		grumpy.interrupt();//被中断则两个线程一同结束
		//sleepy.interrupt();
	}
}
