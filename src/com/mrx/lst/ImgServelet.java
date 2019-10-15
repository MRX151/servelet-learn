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
        response.setHeader("content-type", "image/png");//ʹ��content-type��Ӧͷָ�����͸����������������Ϊ"image/jpeg"
        //��ȡλ����Ŀ��Ŀ¼�µ�img�ļ��������WP_20131005_002.jpg����ͼƬ������һ��������
        InputStream in = this.getServletContext().getResourceAsStream("/static/image/yifabu.png");
        byte buffer[] = new byte[1024];
        int len = 0;
        OutputStream out = response.getOutputStream();//�õ������
        while ((len = in.read(buffer)) > 0) {//��ȡ������(in)��������ݴ洢��������(buffer)
            out.write(buffer, 0, len);//���������������������������
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
