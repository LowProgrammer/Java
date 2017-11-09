package com.henu.feifei;

import java.util.Random;

/**
	*@ClassName:Input
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月9日-下午7:47:43
	*@version:1.0
	*/
public enum Input {
	NICKEL(5),DIME(10),QUARTER(25),DOLLER(100),
	TOOTHPASTE(200),CHIPS(75),SODA(100),SOAP(50),
	ABORT_TRANSACTION{
		public int amout() {
			throw new RuntimeException("abort.amount()");
		}
	},
	STOP{
		public int amout() {
			throw new RuntimeException("shutdown.amount()");
		}
	};
	int value;
	Input(int value) {
		this.value=value;
		// TODO Auto-generated constructor stub
	}
	Input(){}
	int amount() {
		return value;
	}
	static Random rand=new Random(47);
	public static Input randomSelection() {
		return values()[rand.nextInt(values().length-1)];
	}
}
