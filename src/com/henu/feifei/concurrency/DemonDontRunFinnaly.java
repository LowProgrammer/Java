package com.henu.feifei.concurrency;

import java.util.concurrent.TimeUnit;

import com.henu.feifei.utils.Print;

/**
	*@ClassName:DemonDontRunFinnaly
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午7:00:34
	*@version:1.0
	*/
class ADemon implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Print.print("Starting ADemon");
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			Print.print("exiting via interruptedexception");
		}finally {
			Print.print("this should always run?");
		}
	}
	
}
public class DemonDontRunFinnaly {
	public static void main(String[] args)throws Exception {
		Thread t=new Thread(new ADemon());
		t.setDaemon(true);
		t.start();
	}
}
