package com.mrx.lst;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DispatcherServlet收到请求，准备跳转到‘/field’");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("DispatcherServlet收到请求，准备跳转到‘/field’");
        ServletContext context = this.getServletContext();//获取ServletContext对象
        RequestDispatcher rd = context.getRequestDispatcher("/field");//获取请求转发对象(RequestDispatcher)
        rd.forward(request, response);//调用forward方法实现请求转发
	}

}
