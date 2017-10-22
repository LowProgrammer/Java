package com.henu.feifei;
/**
	*@ClassName:InheritBounds
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月22日-上午10:51:18
	*@version:1.0
	*/
class HoldItem<T>{
	T item;
	public HoldItem(T item) {
		this.item=item;
		// TODO Auto-generated constructor stub
	}
	T getItem() {
		return item;
	}
}
class Colored1<T extends HashColor> extends HoldItem<T>{
	Colored1(T item){
		super(item);
	}
	java.awt.Color color(){
		return item.getColor();
	}
}
class ColoredDimension2<T extends Dimension & HashColor> extends Colored1<T>{
	ColoredDimension2(T item){
		super(item);
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
class Solid2<T extends Dimension & HashColor & Weight>extends ColoredDimension2<T>{
	Solid2(T item){
		super(item);
	}
	int weight() {return item.weight();}
}
/**
 * 
 * @author feifei
 *
 */
public class InheritBounds {
	public static void main(String[] args) {
		Solid2<Bounded> solid2=new Solid2<Bounded>(new Bounded());
		solid2.color();
		solid2.getY();
		solid2.weight();
	}
}
