package com.henu.feifei.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private Intgenerator generator;
	private final int id;

	public EvenChecker(Intgenerator g, int ident) {
		// TODO Auto-generated constructor stub
		generator = g;
		id = ident;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even");
				generator.cancel();
			}
		}
	}

	public static void test(Intgenerator gp, int count) {
		System.out.println("press control-c to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}

	public static void test(Intgenerator gp) {
		test(gp, 10);
	}
}
