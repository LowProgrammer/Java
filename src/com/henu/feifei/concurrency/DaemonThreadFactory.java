package com.henu.feifei.concurrency;

import java.util.concurrent.ThreadFactory;

/**
	*@ClassName:DaemonThreadFactory
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午4:29:22
	*@version:1.0
	*/
public class DaemonThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread thread=new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
	
}
