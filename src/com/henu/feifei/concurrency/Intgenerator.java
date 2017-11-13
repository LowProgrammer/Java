package com.henu.feifei.concurrency;

public abstract class Intgenerator {
	private volatile boolean canceled=false;
	public abstract int next();
	public void cancel() {
		canceled=true;
	}
	public boolean isCanceled() {
		return canceled;
	}
	
}
