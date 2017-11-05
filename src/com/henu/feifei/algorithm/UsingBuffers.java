package com.henu.feifei.algorithm;

import java.nio.CharBuffer;

/**
	*@ClassName:UsingBuffers
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月5日-下午4:14:46
	*@version:1.0
	*/
public class UsingBuffers {
	/**
	 * 交换相邻字符
	 * @param buffervoid
	 */
	public static void symmetricScramble(CharBuffer buffer) {
		while(buffer.hasRemaining()) {
			buffer.mark();
			char c1=buffer.get();
			char c2=buffer.get();
			buffer.reset();
			buffer.put(c2).put(c1);
		}
	}
}
