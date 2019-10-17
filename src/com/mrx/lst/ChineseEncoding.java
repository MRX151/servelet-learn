package com.mrx.lst;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChineseEncoding
 */
@WebServlet("/ChineseEncoding")
public class ChineseEncoding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//outputStream(response);
		printWriter(response);
	}

	private void outputStream(HttpServletResponse response) throws IOException {
		/**
		 * 使用OutputStream输出中文注意问题： 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
		 * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
		 * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
		 * 可以通过设置响应头控制浏览器的行为，例如： response.setHeader("content-type",
		 * "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
		 */
		String data = "中国";
		OutputStream outputStream = response.getOutputStream();// 获取OutputStream输出流
		response.setHeader("content-type", "text/html;charset=GBK");// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		/**
		 * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表， 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
		 * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字 比如： "中"在GB2312的码表上对应的数字是98
		 * "国"在GB2312的码表上对应的数字是99
		 */
		/**
		 * getBytes()方法如果不带参数，根据你editor选择的默认编码格式来设置
		 */
		byte[] dataByteArr2 = data.getBytes("UTF-8");// 将字符转换成字节数组，指定以UTF-8编码进行转换
		System.out.println(dataByteArr2);
		byte[] dataByteArr = data.getBytes("GBK");
		System.out.println(dataByteArr);
		outputStream.write(dataByteArr);// 使用OutputStream流向客户端输出字节数组
	}
	
	private void printWriter(HttpServletResponse response) throws IOException {
		
//		response.setContentType("text/html;charset=utf-8");
		String str = URLEncoder.encode("中国abc", "GBK");
		System.out.println(str);
		response.setContentType("text/html;charset=GBK");
		response.getWriter().append("中国-printWriter1");
		
	}
}
