package com.henu.feifei.concurrency;
/**
	*@ClassName:SerialNumberGenerator
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月14日-下午8:12:27
	*@version:1.0
	*/
public class SerialNumberGenerator {
	private static volatile int serialNumber=0;
	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
