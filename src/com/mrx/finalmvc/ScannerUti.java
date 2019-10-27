package com.mrx.finalmvc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ScannerUti {

	public static void scan(String pack, Set<Class<?>> set) {
		String path = pack.replace('.', '/');// 转换的原因是classLoader的getResources方法只接受这种分隔符风格的输入
		String environmentPath = pack.replace('.', File.separatorChar);
		try {
			Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(path);
			while (urlEnumeration.hasMoreElements()) {
				URL url = (URL) urlEnumeration.nextElement();
				File f = new File(url.toURI());
				getFiles(environmentPath, f, set);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getFiles(String environmentPath, File f, Set<Class<?>> set) {
		if (f.isFile()) {
			loadClass(environmentPath, f, set);
		} else {
			File[] files = f.listFiles();
			for (File file : files) {
				getFiles(environmentPath, file, set);// 组合进新的pack
			}
		}
	}

	private static void loadClass(String environmentPath, File f, Set<Class<?>> set) {
		String name = f.getPath();
		if (name.endsWith(".class")) {
			int beginIndex = name.indexOf(environmentPath);
			String packName = name.substring(beginIndex, name.length() - 6).replace(File.separatorChar, '.');
			try {
				Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(packName);
				set.add(clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Set<Class<?>> set = new HashSet<Class<?>>();
		scan("com.mrx.finalmvc", set);
		System.out.println("扫描完毕，得到class个数：" + set.size());
	}
}
