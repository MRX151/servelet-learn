package com.mrx.basic;

import java.lang.reflect.Proxy;

public class JavaDeveloper implements Developer{

	private String name;
	
	public JavaDeveloper(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	public void code() {
		// TODO Auto-generated method stub
		System.out.println(this.name + " is coding");
	}

	@Override
	public void debug() {
		// TODO Auto-generated method stub
		System.out.println(this.name + " is debugging");
	}

	public static void main(String[] args) {
		JavaDeveloper zack = new JavaDeveloper("zack");
		JavaDeveloper jon = new JavaDeveloper("jon");
		
		Developer zackProxy = (Developer) Proxy.newProxyInstance(
//				zack.getClass().getClassLoader(),
				JavaDeveloper.class.getClassLoader(), 
				JavaDeveloper.class.getInterfaces(), 
				(proxy, method, agrs) -> {
					if(method.getName().equals("code")) {
						System.out.println("zack is praying for the code!");
						return method.invoke(jon, args);
					}
					if(method.getName().equals("debug")) {
						System.out.println("zack's have no bug!no need to debug!");
					}
					return null;
				});
		
		zackProxy.code();
		zackProxy.debug();
	}
}
