package com.henu.feifei;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
	*@ClassName:GetChannel
	*@Description:新的io输入输出
	*@author:feifei
	*@date :2017年11月4日-下午1:08:52
	*@version:1.0
	*/
public class GetChannel {
	private static final int BSIZE=1024;
	public static void main(String[] args)throws Exception {
		FileChannel fc=new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("some text".getBytes()));
		fc.close();
		
		fc=new RandomAccessFile("data.txt", "rw").getChannel();
		fc.position(fc.size());
		fc.close();
		
		fc=new FileInputStream("data.txt").getChannel();
		ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
		fc.read(buffer);
		buffer.flip();
		
		while(buffer.hasRemaining()) {
			System.out.println((char)buffer.get());
		}
	}
}
