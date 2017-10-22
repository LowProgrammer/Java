package com.henu.feifei;

import java.util.ArrayList;
import java.util.List;

/**
	*@ClassName:GenericsAndCovariance
	*@Description:TODO
	*@author:feifei
	*@date :2017年10月22日-下午3:50:45
	*@version:1.0
	*/
public class GenericsAndCovariance {
	public static void main(String[] args) {
		List<? extends Fruit> flist=new ArrayList<Apple>();
		flist.add(null);
		Fruit fruit=flist.get(0);
				
	}
}
