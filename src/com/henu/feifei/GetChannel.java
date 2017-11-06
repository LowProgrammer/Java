package com.henu.feifei;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
		
		GetChannel a=new GetChannel();
		//a.rw();
		//a.getdata();
		//a.endians();
		//a.largeMappedFiles();
		//a.fileLcoking();
		a.lockingMappedFiles();
	}
	//新的方式读取写入文件void
	
	public void rw()throws Exception {
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
	static final int length=0x8FFFFFF;//128M
	//内存映射文件
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
	//文件加锁机制
	public void fileLcoking()throws Exception {
		FileOutputStream fos=new FileOutputStream("file.txt");
		FileLock fl=fos.getChannel().tryLock();
		if(fl!=null) {
			System.out.println("locked file");
			TimeUnit.MILLISECONDS.sleep(100);
			fl.release();
			System.out.println("Release lock");
		}
		fos.close();
		
	}
	//对映射文件的部分加锁
	static FileChannel fc;
	public void lockingMappedFiles() throws Exception{
		fc=new RandomAccessFile("test.dat", "rw").getChannel();
		MappedByteBuffer out=fc.map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i=0;i<length;i++) {
			out.put((byte)'x');
		}
		new LockAndModify(out, 0,0+length/3);
		new LockAndModify(out, length/2,length/2+length/4);
	}
	private static class LockAndModify extends Thread {
		private ByteBuffer buffer;
		private int start,end;
		public LockAndModify(ByteBuffer mbb,int start,int end) {
			// TODO Auto-generated constructor stub
			this.start=start;
			this.end=end;
			mbb.limit(end);
			mbb.position(start);
			buffer=mbb.slice();//创建缓冲区和用户修改
			start();
		}
		@Override
		public void run() {
			try {
				long beg=System.nanoTime();
				FileLock fl=fc.lock(start,end,false);
				System.out.println("locked: "+start+" to "+end);
				
				while(buffer.position()<buffer.limit()-1) {
					buffer.put((byte)(buffer.get()+1));
				}
				fl.release();
				System.out.println("released: "+start+" to "+end);
				long dur=System.nanoTime()-beg;
				System.out.format("%.2f\n", dur/1.0e9);
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}
	}
}
