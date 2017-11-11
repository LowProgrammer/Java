package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec=Executors.newSingleThreadExecutor();
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
