package com.henu.feifei.concurrency;

public class BasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Thread t=new Thread(new LiftOff());
		t.start();
		//Thread.sleep(1000);
		System.out.println("waiting for liftoff");
	}

}
