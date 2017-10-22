package com.henu.feifei;
/**
	*@ClassName:CovariantArrays
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月22日-下午3:37:18
	*@version:1.0
	*/
public class CovariantArrays {
	public static void main(String[] args) {
		Fruit[] fruits=new Apple[10];
		fruits[0]=new Apple();
		fruits[1]=new Jonathan();
		try {
			fruits[0]=new Fruit();
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		try {
			fruits[0]=new Orange();
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
}
class Fruit{}
class Apple extends Fruit{}
class Jonathan extends Fruit{}
class Orange extends Fruit{}

