package com.henu.feifei;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import com.henu.feifei.utils.Print;

/**
	*@ClassName:WormSerializable
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月8日-下午2:09:47
	*@version:1.0
	*/
public class WormSerializable implements Serializable{
	public static Random rand=new Random(47);
	private Data[] d= {
		new Data(rand.nextInt(10)),
		new Data(rand.nextInt(10)),
		new Data(rand.nextInt(10))
	};
	private WormSerializable next;
	private char c;
	
	public WormSerializable(int i,char x) {
		Print.print("Worm constructor: "+i);
		c=x;
		if(--i>0) {
			next=new WormSerializable(i, (char)(x+1));
		}
		// TODO Auto-generated constructor stub
	}
	public WormSerializable() {
		Print.print("default Constructor");
	}
	public String toString() {
		StringBuilder result=new StringBuilder(":");
		result.append(c);
		result.append("(");
		for(Data d:d) {
			result.append(d);
		}
		result.append(")");
		if(next!=null) {
			result.append(next);
		}
		return result.toString();
	}
	public static void main(String[] args)throws ClassNotFoundException,IOException{
		WormSerializable w=new WormSerializable(6,'a');
		Print.print("w="+w);
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("WormSerializable.out"));
		out.writeObject("worm Storage\n");
		out.writeObject(w);
		out.close();
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("WormSerializable.out"));
		String s=(String)in.readObject();
		WormSerializable w2=(WormSerializable)in.readObject();
		Print.print(s+"w2="+w2);
		
		ByteArrayOutputStream bout=new ByteArrayOutputStream();
		ObjectOutputStream out2=new ObjectOutputStream(bout);
		out2.writeObject("worm storage\n");
		out2.writeObject(w);
		out2.flush();
		
		ObjectInputStream in2=new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
		s=(String)in2.readObject();
		WormSerializable w3=(WormSerializable)in2.readObject();
		Print.print(s+"w3="+w3);
	}
}
class Data implements Serializable{
	private int n;
	public Data(int n) {
		this.n=n;
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return Integer.toString(n);
	}
}
