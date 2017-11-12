package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExecptionHandling {
	public static void main(String[] args) {
		try {
			ExecutorService exec=Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception has been handled");
		}
	}
}
