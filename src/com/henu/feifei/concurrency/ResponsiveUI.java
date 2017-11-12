package com.henu.feifei.concurrency;

class UnresponsiveUI{
	private volatile double d=1;
	public UnresponsiveUI()throws Exception {
		while(d>0) {
			d=d+(Math.PI+Math.E)/d;
		}
		System.in.read();
	}
}
public class ResponsiveUI extends Thread{
	private static volatile double d=1;
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			d=d+(Math.PI+Math.E)/d;
		}
	}
	public static void main(String[] args)throws Exception {
		//new UnresponsiveUI();
		new ResponsiveUI();//main方法结束后台进程结束
		//System.out.println(d);
		System.in.read();
		System.out.println(d);
	}
}
