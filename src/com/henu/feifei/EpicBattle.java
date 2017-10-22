package com.henu.feifei;

import javax.xml.ws.Action;

/**
	*@ClassName:EpicBattle
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月22日-下午2:40:19
	*@version:1.0
	*/
public class EpicBattle {
	static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
	}
	static <POWER extends SuperHearing & SuperSmell> void superFriend(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
		hero.getPower().trackBySmell();
	}
	public static void main(String[] args) {
		DogBoy dogBoy=new DogBoy();
		useSuperHearing(dogBoy);
		superFriend(dogBoy);
	}
}
interface SuperPower{}
interface XRayVision extends SuperPower{
	/**
	 * @param
	 * void
	 */
	void seeThroughWalls() ;
}
interface SuperHearing extends SuperPower{
	/**
	 * @param
	 * void
	 */
	void hearSubtleNoises();
}
interface SuperSmell extends SuperPower{
	/**
	 * @param
	 * void
	 */
	void trackBySmell();
}
class SuperHero<POWER extends SuperPower>{
	POWER power;
	public SuperHero(POWER power) {
		this.power=power;
		// TODO Auto-generated constructor stub
	}
	POWER getPower() {
		return power;
	}
}
class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER>{

	public SuperSleuth(POWER power) {
		super(power);
		// TODO Auto-generated constructor stub
	}
	void see() {
		power.seeThroughWalls();
	}
}
class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER>{
	public CanineHero(POWER power) {
		super(power);
		// TODO Auto-generated constructor stub
	}
	void hear() {
		power.hearSubtleNoises();
	}
	void smell() {
		power.trackBySmell();
	}
}
class SuperHearSmall implements SuperHearing,SuperSmell{

	@Override
	public void trackBySmell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hearSubtleNoises() {
		// TODO Auto-generated method stub
		
	}
	
}
class DogBoy extends CanineHero<SuperHearSmall>{
	public DogBoy() {
		super(new SuperHearSmall());
		// TODO Auto-generated constructor stub
	}
}