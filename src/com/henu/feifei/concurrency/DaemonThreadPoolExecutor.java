package com.henu.feifei.concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
	*@ClassName:DaemonThreadPoolExecutor
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月11日-下午4:36:19
	*@version:1.0
	*/
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor{
	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 
				60L, TimeUnit.SECONDS ,
				new SynchronousQueue<Runnable>(),
				new DaemonThreadFactory());
	}
}
