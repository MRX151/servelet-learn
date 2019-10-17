package com.mrx.basic;

import java.io.UnsupportedEncodingException;

public class CharTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		char a = 'A';
		int ai = (int)a;
		System.out.println(ai);//打印65
		ai++;
		char aa = (char)ai;
		System.out.println(aa);//打印B
		
		String letters = "hello";
		char[] carray = letters.toCharArray();
		System.out.println(carray);
		
		byte[] hello = "中国".getBytes();//[-28, -72, -83, -27, -101, -67]
		byte[] hello2 = "中国".getBytes("GBK");//[-42, -48, -71, -6]
		
		String s1= "你好啊的"; 
		 String gbk = new String(s1.getBytes("UTF-8"), "gbk"); 
		 System.out.println(gbk);//浣犲ソ鍟婄殑
		 String utf8 = new String(gbk.getBytes("gbk"), "utf-8");
		 System.out.println(utf8);//你好啊的
	}

}
