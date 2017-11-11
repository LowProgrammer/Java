package com.henu.feifei;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * F://a.rar 100MB
 * 
 * a-part01.rar 10mb
 * a-part02.rar 10mb
 * a-part03.rar 10mb
 * a-part04.rar 10mb
 * a-part05.rar 10mb
 * a-part06.rar 101mb
 * 
 * 
 * �ϲ�
 * a-part01.rar 10mb
 * a-part02.rar 10mb
 * a-part03.rar 10mb
 * a-part04.rar 10mb
 * a-part05.rar 10mb
 * a-part06.rar 10mb
 * 
 * a.rar
 * 
 * 
 * 
 * 
 * @author arry
 *
 */
public class SperaratorFileAndDiv {

	private String fileName;//ԭʼ�ļ���
	long fileSize = 0;//Դ�ļ���С
	long blockNum = 0;//�ɷֵĿ���
	
	/**
	 * ��ʼ���ļ������ƣ������ļ��ĳ���
	 * @param filePath
	 */
	private void getFileAttribute(String filePath){
		File file = new File(filePath);
		fileName = file.getName();
		fileSize = file.length();
	}

	
	/**
	 * ���ݴ�С���зָ�
	 * @param size
	 * @return
	 */
	private long getBlockNum(long size) {
		long fileSize = this.fileSize;
		if(fileSize <= size){
			return 1;
		}else{
			if(fileSize % size > 0){
				return fileSize / size + 1;
			}else{
				return fileSize /size;
			}
		}
	}
	
	/**
	 * ��ֵ����ļ���������
	 * @param filePath
	 * @param currentBlock
	 * @return
	 */
	private String generrateSperatorName(String filePath,int currentBlock){
		String rname = filePath+".part"+currentBlock;
		System.out.println("�ļ���ֳɣ�"+rname);
		return rname;
	}
	
	
	
	private boolean writerFile(String sourcePath,String speratorPath,long blockSize,long beginPos){
		//�������������
		RandomAccessFile randomAccessFile = null;
		FileOutputStream out = null;
		byte[] bt = new byte[1024];
		long writeByte = 0;
		int len = 0;
		try {
			//����������ʶ�����ֻ���ķ�ʽ
			randomAccessFile = new RandomAccessFile(sourcePath, "r");
			randomAccessFile.seek(beginPos);//���ö���ʼ��ȡ��λ��
			//������ָ�����ļ���
			out = new FileOutputStream(speratorPath);
			while((len=randomAccessFile.read(bt))!=-1){
				if(writeByte < blockSize){
					writeByte = writeByte+len;
					if(writeByte <=blockSize){
						out.write(bt, 0, len);
					}else{
						len = len-(int)(writeByte-blockSize);
						out.write(bt, 0, len);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				if(out!=null)out.close();
				if(randomAccessFile!=null)randomAccessFile.close();
			} catch (Exception e2) {
			}
		}
	}
	
	
	
	
	/*�ļ��ָ��*/
	private boolean seperatorFile(String sourcePath,long blockSize){
		//��ʼ���ļ������ԣ���ȡ�ļ�������ȡ�ļ���С
		getFileAttribute(sourcePath);
		//���ݽ����Ĵ�С������������Էָ���ٸ����ļ�
		blockNum = getBlockNum(blockSize);
		System.out.println("�����Ϊ��"+blockNum+"�����ļ�!");
		if(blockNum==1){
			blockSize = fileSize;
		}
		
		long writeSize = 0;//ÿ��д����ֽ�
		long writeTotal = 0;//ÿ��д�˶����ֽ�
		String currentNameAndPath = null;
		for(int i=1;i<=blockNum;i++){
			if(i<blockNum){
				writeSize = blockSize;
			}else{
				writeSize = fileSize - writeTotal;
			}
			
			if(blockNum==1){
				currentNameAndPath =sourcePath+".bat";
			}else{
				currentNameAndPath = generrateSperatorName(sourcePath, i);
			}
			
			if(!writerFile(sourcePath, currentNameAndPath, writeSize, writeTotal)){
				return false;
			}
			writeTotal = writeTotal + writeSize;
		}
		
		return true;
	}
	
	
	//�ϲ��ļ�
	public static String unite(String[] fileNames,String targetFile){
		File inFile = null;
		//д���Ŀ���ļ�
		File outFile = new File(targetFile);
		//�����
		FileOutputStream out = null;
		byte[] b = new byte[1024*1024];
		try {
			//��ʼ�����������
			out = new FileOutputStream(targetFile);
			for (int i = 0; i < fileNames.length; i++) {
				inFile = new File(fileNames[i]);
				FileInputStream in = new FileInputStream(inFile);
				int c = 0;
				while((c=in.read(b))!=-1){
					out.write(b,0,c);
				}
			}
			return outFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				if(out!=null)out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	private String[] getPartFileNames(String sourcePath,long blockSize){
		//��ʼ���ļ������ԣ���ȡ�ļ�������ȡ�ļ���С
		getFileAttribute(sourcePath);
		//���ݽ����Ĵ�С������������Էָ���ٸ����ļ�
		blockNum = getBlockNum(blockSize);
		System.out.println("�����Ϊ��"+blockNum+"�����ļ�!");
		if(blockNum==1){
			blockSize = fileSize;
		}
		String[] names = new String[(int)blockNum];
		String cname = null;
		for (int i = 1; i <=blockNum; i++) {
			if(blockNum==1){
				cname = sourcePath+".bat";
			}else{
				cname = generrateSperatorName(sourcePath, i);
			}
			names[i-1] = cname;
		}
		return names;
	}
	
	
	public static void main(String[] args) {
		SperaratorFileAndDiv sperarator = new SperaratorFileAndDiv();
		String sourcePath = "c://test/test.rar";
		long blockSize = 1024*100;
//		boolean flag = sperarator.seperatorFile(sourcePath, blockSize);
//		if(flag){
//			System.out.println("�ļ���ֳɹ�...");
//		}
		
		String[] names = sperarator.getPartFileNames(sourcePath, blockSize);
		String flag = sperarator.unite(names,"d://union.rar");
		System.out.println("�ϲ��ļ�������·���ǣ�"+flag);
		
		
	}
	
	
	
}
