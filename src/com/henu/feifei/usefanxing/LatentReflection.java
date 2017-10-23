package com.henu.feifei.usefanxing;
import static com.henu.feifei.utils.Print.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.lang.reflect.Method;
/**
	*@ClassName:LatentReflection
	*@Description:使用的一种方式反射
	*@author:feifei
	*@date :2017年10月23日-下午1:02:36
	*@version:1.0
	*/
public class LatentReflection {
	public static void main(String[] args) {
		CommunicateReflectively.perform(new SmartDog());
		try {
			CommunicateReflectively.perform(new Robot());
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommunicateReflectively.perform(new Mime());
	}
}
class Mime{
	public void walkAgainstTheWind() {}
	public void sit() {
		print("Pretending to sit");
	}
	public void pushInvisibleWalls() {}
	public String toString() {
		return "Mime";
	}
}
class SmartDog{
	public void speak() {
		print("Woof!");
	}
	public void sit() {
		print("sitting");
	}
	public void response() {}
}
class CommunicateReflectively{
	public static void perform(Object speaker) {
		Class<?> spkr=speaker.getClass();
		try {
			try {
				Method speak=spkr.getMethod("speak");
				speak.invoke(speaker);
			} catch (Exception e) {
				print(speaker+"can't speak");
				// TODO: handle exception
			}
			try {
				Method sit=spkr.getMethod("sit");
				sit.invoke(speaker);
			} catch (Exception e) {
				
				print(speaker+"can't sit");
				// TODO: handle exception
			}
		} catch (Exception e) {
			throw new RuntimeException(speaker.toString(),e);
			// TODO: handle exception
		}
	}
}