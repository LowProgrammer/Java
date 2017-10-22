package com.henu.feifei;

import java.awt.Color;

/**
	*@ClassName:BasicBounds
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月21日-下午8:54:03
	*@version:1.0
	*/
interface HashColor{
	java.awt.Color getColor();
}
class Colored<T extends HashColor>{
	T item;
	Colored(T item){
		this.item=item;
	}
	T getItem() {
		return item;
	}
	java.awt.Color color() {
		return item.getColor();
	}
}
class Dimension{
	public int x,y,z;
}
class ColoredDimension<T extends Dimension & HashColor>{
	T item;
	ColoredDimension(T item){
		this.item=item;
	}
	T getItem() {
		return item;
	}
	java.awt.Color color(){
		return item.getColor();
	}
	int getX() {
		return item.x;
	}
	int getY() {
		return item.y;
	}
	int getZ() {
		return item.z;
	}
}
interface Weight{
	int weight();
}
class Solid<T extends Dimension & HashColor & Weight>{
	T item;
	Solid(T item){
		this.item=item;
	}
	T getItem() {
		return item;
	}
	java.awt.Color color(){
		return item.getColor();
	}
	int getX() {
		return item.x;
	}
	int getY() {
		return item.y;
	}
	int getZ() {
		return item.z;
	}
	int weight() {
		return item.weight();
	}
}
class Bounded extends Dimension implements HashColor, Weight{
	@Override
	public java.awt.Color getColor(){
		return null;
	}
	@Override
	public int weight() {
		return 0;
	}
}
public class BasicBounds {
	public static void main(String[] args) {
		Solid<Bounded> solid=new Solid<Bounded>(new Bounded());
		solid.color();
		solid.getY();
		solid.weight();
	}
}
