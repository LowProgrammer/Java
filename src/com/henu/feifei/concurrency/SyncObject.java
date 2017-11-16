package com.henu.feifei.concurrency;

import java.security.spec.DSAGenParameterSpec;

/**
	*@ClassName:SyncObject
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月16日-下午4:05:36
	*@version:1.0
	*/
class DualSynch{	
	private Object syncobject=new Object();
	public synchronized void f() {
		for(int i=0;i<5;i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	public void g() {
		synchronized(syncobject) {
			for(int i=0;i<5;i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}
public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds=new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
	
}
