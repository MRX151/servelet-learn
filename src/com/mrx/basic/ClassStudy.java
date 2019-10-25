package com.mrx.basic;

class Dog {
	static {//静态代码块，在被加载时执行，用来初始化
		System.out.println("Loading dog");
	}
}

class Cat {
	static {
		System.out.println("Loading cat");
	}
}

public class ClassStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
