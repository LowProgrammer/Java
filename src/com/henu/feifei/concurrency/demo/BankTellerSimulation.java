package com.henu.feifei.concurrency.demo;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.ShortSeqHelper;

class Customer{
	private final int serviceTime;
	public Customer(int tm) {
		serviceTime=tm;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+serviceTime+"]";
	}
}
class CustomerLine extends ArrayBlockingQueue<Customer>{
	public CustomerLine(int maxLineSize) {
		// TODO Auto-generated constructor stub
		super(maxLineSize);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.size()==0) {
			return "[empty]";
		}
		StringBuilder result=new StringBuilder();
		for(Customer customer:this) {
			result.append(customer);
		}
		return result.toString();
	}
}
class CustomerGenerator implements Runnable{
	private CustomerLine customers;
	private static Random rand=new Random(47);
	public CustomerGenerator(CustomerLine cq) {
		// TODO Auto-generated constructor stub
		customers=cq;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		} catch (Exception e) {
			System.out.println("CustomerGenerator interrupted");
			// TODO: handle exception
		}
		System.out.println("CustomerGenerator terminating");
	}
}
class Teller implements Runnable,Comparable<Teller>{
	private static int counter=0;
	private final int id=counter++;
	
	private int customersServed=0;
	private CustomerLine customers;
	private boolean servingCustomerLine=true;
	public Teller(CustomerLine cq) {
		// TODO Auto-generated constructor stub
		customers=cq;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				Customer customer=customers.take();
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customersServed++;
					wait();
				}
			}
		} catch (Exception e) {
			System.out.println(this+"interrupted");
			// TODO: handle exception
		}
		System.out.println(this+"terminating");
	}
	public synchronized void doSomethingElse(){
		customersServed=0;
		servingCustomerLine=false;
	}
	public synchronized void serveCustomerLine(){
		assert !servingCustomerLine:"already serving: "+this;
		servingCustomerLine=false;
		notifyAll();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Teller "+id+"  ";
	}
	public String shortString() {
		return "T"+id;
	}
	@Override
	public synchronized int compareTo(Teller other) {
		// TODO Auto-generated method stub
		return customersServed<other.customersServed?-1:(customersServed==other.customersServed?0:1);
	}
}

class TellerManager implements Runnable{
	private ExecutorService exec;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers=new PriorityQueue<Teller>();
	private Queue<Teller> tellersDoingOtherThings=new LinkedList<Teller>();
	private int adjustmentPeriod;
	private static Random rand=new Random(47);
	public TellerManager(ExecutorService e,CustomerLine c,int adjustmentPeriod) {
		// TODO Auto-generated constructor stub
		exec=e;
		this.customers=c;
		this.adjustmentPeriod=adjustmentPeriod;
		
		Teller teller=new Teller(customers);
		exec.execute(teller);
		workingTellers.add(teller);
	}
	public void adjustTellerNumber() {
		if(customers.size()/workingTellers.size()>0) {
			if(tellersDoingOtherThings.size()>0) {
				Teller teller=tellersDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}
		}
		if(workingTellers.size()>1&&customers.size()/workingTellers.size()<2) {
			reassignOneTeller();
		}
		if(customers.size()==0) {
			while(workingTellers.size()>1) {
				reassignOneTeller();
			}
		}
	}
	private void reassignOneTeller() {
		Teller teller=workingTellers.poll();
		teller.doSomethingElse();
		tellersDoingOtherThings.offer(teller);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers+"{");
				for(Teller teller:workingTellers) {
					System.out.print(teller.shortString()+" ");
				}
				System.out.println("}");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(this+"interrupted");
		}
		System.out.println(this+"terminating");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tellermanager ";
	}
}
public class BankTellerSimulation {
	static final int MAX_SIZE=50;
	static final int ADJUSTMENT_PERIOD=1000;
	public static void main(String[] args)throws Exception {
		ExecutorService exec=Executors.newCachedThreadPool();
		CustomerLine customers=new CustomerLine(MAX_SIZE);
		exec.execute(new CustomerGenerator(customers));
		
		exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
		if(args.length>0) {
			TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		}
		else {
			System.out.println("Press enter to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
