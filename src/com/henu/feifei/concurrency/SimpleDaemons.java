package com.henu.feifei.concurrency;

import java.util.concurrent.TimeUnit;

import com.henu.feifei.utils.Print;

/**
	*@ClassName:SimpleDaemons
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午4:18:24
	*@version:1.0
	*/
public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				Print.print(Thread.currentThread()+ "  "+this);
			}			
		} catch (Exception e) {
			Print.print("sleep() interrupted");
			// TODO: handle exception
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<10;i++) {
			Thread dameon=new Thread(new SimpleDaemons());
			dameon.setDaemon(true);
			dameon.start();
		}
		Print.print(" all daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}

}
