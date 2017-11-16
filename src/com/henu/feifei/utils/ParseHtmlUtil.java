package com.henu.feifei.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class ParseHtmlUtil {

	/**
	 * 获取网页源代码
	 * 方法名：getHtmlSource<br/>
	 * 创建人：xuchengfei <br/>
	 * 时间：2016年5月21日-下午11:48:10 <br/>
	 * 手机:1564545646464<br/>
	 * @param netUrl
	 * @param encoding
	 * @return String<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static String getHtmlSource(String netUrl,String encoding){
		try {
			URL url = new URL(netUrl);
			//打开网络服务的连接对象
			URLConnection connection = url.openConnection();
			StringBuilder builder = new StringBuilder();
			try(
				//获取连接的字节输入流
				InputStream inputStream = connection.getInputStream();
				//网络图片、视频---字节流(8--1byte)
				//网络源代码---字符流(16--1byte)
				//将字节流转换成字符流--网页的源代码
				InputStreamReader isr = new InputStreamReader(inputStream,encoding);//char--中
				BufferedReader reader = new BufferedReader(isr); 
			){
				String content = null;
				while((content=reader.readLine())!=null){
					builder.append(content+"\r\n");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	/**
	 * 下载网络资源文件
	 * 方法名：downloadFile<br/>
	 * 创建人：xuchengfei <br/>
	 * 时间�?2016�?5�?19�?-下午11:34:14 <br/>
	 * 手机:1564545646464<br/>
	 * @param linkFile
	 * @param path void<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static void downloadFile(String linkFile,String path){
		try {
			//第一步：初始化以URL对象
			URL url =new URL(linkFile);
			//第二步：获取打开URL和java程序之间连接
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//伪装浏览器的方式去抓取网络信�?
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			String name = linkFile.substring(linkFile.lastIndexOf("/")+1);
			//创建磁盘目录
			File file = new File(path);
			//如果文件夹不存在，就创建
			if(!file.exists())file.mkdirs();
			//输入到目标文�?
			File filename = new File(file,UUID.randomUUID().toString()+".jpg");	
			try (
				//字节�?
				InputStream inputStream = connection.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(filename)
			){
				byte[] b = new byte[2048];
				int len = 0;
				while((len=inputStream.read(b))!=-1){
					outputStream.write(b, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		String netUrl = "http://www.163.com/";
		String encoding = "gbk";
		String htmlsource = getHtmlSource(netUrl, encoding);
		//字符串---正则表达式(学习成本太高)----HtmlParser(apache)(移除掉了)---tika(htmlparse,mp3,word,pdf，excel)
		//Jsoup---文本解析/xml解析工具类---它javascript+jquery结合体（java版本而已）
//		Document document = Jsoup.parse(new File("G:/Java基础班视频/Java网络编程/index.html"), "utf-8");
//		System.out.println(document.html());
//		String result = document.getElementById("results").html();
//		System.out.println(result);
		
//		Document document = Jsoup.parse(new URL(netUrl),3000);
//		System.out.println(document.html());
//		System.out.println(document.text());
		
		
//		Document document = Jsoup.parse(htmlsource);
//		Element element =  document.getElementById("newsContent01");
//		Elements elements = element.select("li");
//		List<HashMap<String, Object>> maps = new ArrayList<>();
//		for (Element ele : elements) {
//			try {
//				String href = ele.getElementsByTag("a").get(0).attr("href");
//				String html = getHtmlSource(href, "gbk");
//				Document doc = Jsoup.parse(html);
//				Element element2 = doc.getElementById("C-Main-Article-QQ");
//				String title2 = element2.getElementsByTag("h1").get(0).text();
//				String time = element2.getElementsByClass("article-time").get(0).text();
//				String content = element2.getElementById("Cnt-Main-Article-QQ").html();
//				HashMap<String, Object> map = new HashMap<>();
//				map.put("title", title2);
//				map.put("time", time);
//				map.put("content", content);
//				maps.add(map);
//				System.out.println(href+"下载完毕......");
//			} catch (Exception e) {
//				continue;
//			}
//		}
//		
//		
//		
//		for (HashMap<String, Object> hashMap : maps) {
//			System.out.println("=========================================================");
//			System.out.println(hashMap.get("title"));
//			System.out.println(hashMap.get("content"));
//			System.out.println(hashMap.get("time"));
//			System.out.println("=========================================================");
//		}
		
		//jdbc--数据连接---存入数据库
		
		
		
		//抓取网络图片
		String html = getHtmlSource("http://www.luoo.net", "utf-8");
		Document document = Jsoup.parse(html);
		Elements elements = document.getElementsByTag("img");
		for (Element element : elements) {
			String src = element.attr("src");
			if(src!=null && !"".equals(src) && src.indexOf("jpg")!=-1){
				System.out.println(src);
				downloadFile(src,"D:/download/image");
			}
		}
		
		
		//===>jsoup 指定标签的过滤--CSDN--xml
		//io流mysql数据库的备份与还原
		Jsoup.parse("评论的内容<script></script></link></link><iframe></iframe><object></object>");
		Jsoup.clean(htmlsource, Whitelist.simpleText());
		
		
	}
}
