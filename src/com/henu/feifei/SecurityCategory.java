package com.henu.feifei;

import com.henu.feifei.utils.Enums;

/**
	*@ClassName:Food
	*@Description:枚举类型分类的实现
	*@author:feifei
	*@date :2017年11月9日-下午1:35:36
	*@version:1.0
	*/
public enum SecurityCategory {
	STOCK(Security.Stock.class),
	BOND(Security.Bond.class);
	Security[] values;
	SecurityCategory(Class<? extends Security> kind) {
		values=kind.getEnumConstants();
		// TODO Auto-generated constructor stub
	}
	interface Security{
		enum Stock implements Security{SHORT,LONG,MARGIN}
		enum Bond implements Security{MUNICIPAL,JUNK}
	}
	public Security randomSelection() {
		return Enums.random(values);
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			SecurityCategory category=Enums.random(SecurityCategory.class);
			System.out.println(category+" : "+category.randomSelection());
		}
	}
}
