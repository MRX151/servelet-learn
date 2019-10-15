package com.mrx.lst;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GzipServelet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/GzipServelet" })
public class GzipServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GzipServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String data = "abcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabc" +
                "dabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdab" +
                "cdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        System.out.println("原始数据的大小为：" + data.getBytes().length);
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout); //buffer
        gout.write(data.getBytes());
        gout.close();
        //得到压缩后的数据
        byte g[] = bout.toByteArray();
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Content-Length",g.length +"");
        response.setHeader("Content-type", "text/html;charset=utf-8");
        response.getOutputStream().write(g);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
