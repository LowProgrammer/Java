package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.henu.feifei.utils.Print;

/**
	*@ClassName:DaemonFormFactory
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午4:31:14
	*@version:1.0
	*/
public class DaemonFormFactory implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				Print.print(Thread.currentThread()+" "+this);
			}
		} catch (InterruptedException e) {
			Print.print(" interrupted");
			// TODO: handle exception
		}
	}
	public static void main(String[] args)throws Exception {
		ExecutorService exec=Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<10;i++) {
			exec.execute(new DaemonFormFactory());
		}
		Print.print("all daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}
