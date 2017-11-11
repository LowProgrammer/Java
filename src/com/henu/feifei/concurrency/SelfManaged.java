package com.henu.feifei.concurrency;
/**
	*@ClassName:SelfManaged
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午7:48:56
	*@version:1.0
	*/
public class SelfManaged implements Runnable{
	private int countDown=5;
	private Thread t=new Thread(this);
	public SelfManaged() {
		t.start();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Thread.currentThread().getName()+" ("+countDown+" )";
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println(this);
			if(--countDown==0) {
				return;
			}
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
			new SelfManaged();
		}
	}
}
