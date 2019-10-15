package com.mrx.lst;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgServelet
 */
@WebServlet("/ImgServelet")
public class ImgServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setHeader("content-type", "image/png");//使用content-type响应头指定发送给浏览器的数据类型为"image/jpeg"
        //读取位于项目根目录下的img文件夹里面的WP_20131005_002.jpg这张图片，返回一个输入流
        InputStream in = this.getServletContext().getResourceAsStream("/static/image/yifabu.png");
        byte buffer[] = new byte[1024];
        int len = 0;
        OutputStream out = response.getOutputStream();//得到输出流
        while ((len = in.read(buffer)) > 0) {//读取输入流(in)里面的内容存储到缓冲区(buffer)
            out.write(buffer, 0, len);//将缓冲区里面的内容输出到浏览器
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
