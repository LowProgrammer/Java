package com.henu.feifei.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


class CheckoutTask<T> implements Runnable{
	private static int counter=0;
	private final int id=counter++;
	private Pool<T> pool;
	public CheckoutTask(Pool<T> pool){
		this.pool=pool;
	} 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			T item=pool.checkOut();
			System.out.println(this+"checked out "+item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this+"checking in "+item);
			pool.checkIn(item);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "checkouttask "+id;
	}
}
public class SemaphoreDemo {
	final static int SIZE=25;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final Pool<Fat> pool=new Pool<>(Fat.class, SIZE);
		ExecutorService exec=Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new CheckoutTask<Fat>(pool));
		}
		System.out.println("all checkouts created");
		List<Fat> list=new ArrayList<Fat>();
		for (int i = 0; i < SIZE; i++) {
			Fat fat=pool.checkOut();
			System.out.println(i+": main() thread additional checkout");
			fat.operation();
			list.add(fat);
		}
		Future<?> blocked=exec.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		System.out.println("checking in objects in"+list);
		for(Fat f:list){
			pool.checkIn(f);
		}
		for(Fat f:list){
			pool.checkIn(f);
		}
		exec.shutdown();
	}

}
