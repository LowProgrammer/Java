package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new RuntimeException();
	}
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
