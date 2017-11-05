package com.henu.feifei;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

import com.henu.feifei.utils.PPrint;
import com.henu.feifei.utils.Print;

/**
	*@ClassName:GetChannel
	*@Description:新的io输入输出  bytebuffer
	*@author:feifei
	*@date :2017年11月4日-下午1:08:52
	*@version:1.0
	*/
public class GetChannel {
	private static final int BSIZE=1024;
	public static void main(String[] args)throws Exception {
		FileChannel fc=new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("some text ".getBytes()));
		fc.close();
		
		fc=new RandomAccessFile("data.txt ", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("some more".getBytes()));
		fc.close();
		
		fc=new FileInputStream("data.txt").getChannel();
		ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
		fc.read(buffer);
		buffer.flip();
		
		while(buffer.hasRemaining()) {
			System.out.print((char)buffer.get());
		}
		
		GetChannel a=new GetChannel();
		a.getdata();
		a.endians();
	}
	//获取基本类型
	public void getdata() {
		ByteBuffer bb=ByteBuffer.allocate(BSIZE);
		int i=0;
		while(i++<bb.limit()) {
			if(bb.get()!=0) {
				Print.print("none zero");
			}
		}
		Print.print("i="+i);
		bb.rewind();
		
		bb.asCharBuffer().put("Howdy");
		char c;
		while((c=bb.getChar())!=0) {
			Print.print(c);
		}
		System.out.println();
		bb.rewind();
		
		bb.asShortBuffer().put((short)471142);
		Print.print(bb.getShort());
		bb.rewind();
		
		bb.asIntBuffer().put(99778453);
		Print.print(bb.getInt());
		bb.rewind();
		
		bb.asLongBuffer().put(99778453);
		Print.print(bb.getLong());
		bb.rewind();
		
		bb.asFloatBuffer().put(99778453);
		Print.print(bb.getFloat());
		bb.rewind();
		
		bb.asDoubleBuffer().put(99778453);
		Print.print(bb.getDouble());
		bb.rewind();
	}
	//字节存放序列
	private void endians() {
		ByteBuffer bb=ByteBuffer.wrap(new byte[12]);
		bb.asCharBuffer().put("abcdef");
		Print.print(Arrays.toString(bb.array()));
		bb.rewind();
		
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.asCharBuffer().put("abcdef");
		Print.print(Arrays.toString(bb.array()));
		bb.rewind();
		
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.asCharBuffer().put("abcdef");
		Print.print(Arrays.toString(bb.array()));
		bb.rewind();
	}
	static int length=0x8FFFFFF;
	private void largeMappedFiles() throws Exception {
		MappedByteBuffer out=new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i=0;i<length;i++) {
			out.put((byte)'x');
		}
		Print.print("finished writing");
		for(int i=length/2;i<length/2+6;i++) {
			Print.print((char)out.get(i));
		}
	}
}
