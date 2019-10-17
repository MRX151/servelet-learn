package com.mrx.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.util.Date;

public class FileHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String infoString = readFile("F:\\Desktop\\testfile.txt");
		System.out.println(infoString);
		String content = "我爱java" + new Date().toString();
		writeFile("F:\\Desktop\\testfile2.txt", content);
	}

	/**
	 * 读取指定文件的内容
	 * 
	 * @param filePath ： 文件的路径
	 * @return 返回的结果
	 */
	public static String readFile(String filePath) {
		FileInputStream fis = null;
		String result = "";
		try {
			// 根据path路径实例化一个输入流的对象
			fis = new FileInputStream(filePath);

			// 2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
			int size = fis.available();
			// 3. 根据输入流中的字节数创建byte数组；
			byte[] array = new byte[size];
			// 4.把数据读取到数组中；
			fis.read(array);

			// 5.根据获取到的Byte数组新建一个字符串，然后输出；
			result = new String(array);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

    /**
     * 根据文件路径创建输出流
     * @param filePath ： 文件的路径
     * @param content : 需要写入的内容
     */
    public static void writeFile( String filePath , String content ){
        FileOutputStream fos = null ;
        try {
            //1、根据文件路径创建输出流
            fos  = new FileOutputStream( filePath );

            //2、把string转换为byte数组；
            byte[] array = content.getBytes() ;
            //3、把byte数组输出；
            
            fos.write( array );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
