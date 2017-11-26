package com.henu.feifei.concurrency;

public class Fat {
	public volatile double d;
	private static int counter=0;
	private final int id=counter++;
	public Fat(){
		for (int i = 0; i <1000; i++) {
			d+=(Math.E+Math.PI)/(double)i;
		}
		
	}
	public void operation(){
		System.out.println(this);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Fat id:"+id;
	}

}
