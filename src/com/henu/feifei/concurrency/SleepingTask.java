package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:SleepingTask
 * @Description:TODO
 * @author:feifei
 * @date :2017年11月11日-上午11:58:00
 * @version:1.0
 */
public class SleepingTask extends LiftOff {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (countDown-- > 0) {
				System.out.println(status());
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Interrupted");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}

}
