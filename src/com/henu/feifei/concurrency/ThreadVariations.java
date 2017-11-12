package com.henu.feifei.concurrency;

import java.util.concurrent.TimeUnit;

import com.henu.feifei.utils.Print;

/**
	*@ClassName:ThreadVariations
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午8:24:11
	*@version:1.0
	*/
class InnerThread1{
	private int countDown=5;
	private Inner inner;
	private class Inner extends Thread{
		public Inner(String name) {
			// TODO Auto-generated constructor stub
			super(name);
			start();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true) {
					Print.print(this);
					if(--countDown==0)return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
				Print.print("interrupted");
			}
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getName()+"  :  "+countDown;
		}
	}
	public InnerThread1(String name) {
		// TODO Auto-generated constructor stub
		inner=new Inner(name);
	}
}
class InnerThread2{
	private int countDown=5;
	private Thread t;
	public InnerThread2(String name) {
		t=new Thread(name) {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true) {
						Print.print(this);
						if(--countDown==0)return;
						sleep(10);
					}
				} catch (Exception e) {
					Print.print("sleep() innterrupted");
					// TODO: handle exception
				}
			}
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return getName()+" : "+countDown;
			}
		};
		t.start();
	}
}
class InnerRunnable{
	private int countDown=5;
	private Inner inner;
	private class Inner implements Runnable{
		Thread t;
		Inner(String name){
			t=new Thread(name);
			t.start();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true) {
					Print.print(this);
					if(--countDown==0)return;
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (Exception e) {
				// TODO: handle exception
				Print.print("sleep() innerrupted");
				
			}
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return t.getName()+ " : "+countDown;
		}
	}
	public InnerRunnable(String name) {
		// TODO Auto-generated constructor stub
		inner=new Inner(name);
	}
}
class InnerRunnable2{
	private int countDown=5;
	private Thread t;
	public InnerRunnable2(String name) {
		t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true) {
						Print.print(this);
						if(--countDown==0)return;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (Exception e) {
					// TODO: handle exception
					Print.print("sleep() interrupted"); 
				}
			}
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return Thread.currentThread().getName()+" : "+countDown;
			}
		},name);
		t.start();
	}
}
class ThreadMethod{
	private int countDown=5;
	private Thread t;
	private String name;
	public ThreadMethod(String name) {
		this.name=name;
	}
	public void runTask() {
		if(t==null) {
			t=new Thread(name) {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						while(true) {
							Print.print(this);
							if(--countDown==0)return;
							sleep(10);
						}
					} catch (Exception e) {
						// TODO: handle exception
						Print.print("sleep() interrupted ");
					}
				}
			};
			t.start();
		}
	}
}
public class ThreadVariations {

	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable("innerRunnable");
		new InnerRunnable2("innerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}

}
