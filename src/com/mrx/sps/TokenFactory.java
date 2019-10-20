package com.mrx.sps;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;
import sun.security.jca.GetInstance;

public class TokenFactory {
	
	/**
	 * 单例模式要点：
	 * 1.显示声明一个构造方法，并将其设为私有
	 * 2.设置一个私有、静态、不可变的实例，在初始化时即调用1中的私有构造方法初始化一个
	 * 3.设置一个静态、public方法，用来获取唯一的实例
	 */
	private TokenFactory() {}
	private static final TokenFactory instance = new TokenFactory();
	public static TokenFactory GetInstance() { return instance;}
	
    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){  //checkException
        //  7346734837483  834u938493493849384  43434384
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static void main(String[] args) {
		TokenFactory tokenFactory = TokenFactory.GetInstance();
		System.out.println(tokenFactory.makeToken());
	}
}
