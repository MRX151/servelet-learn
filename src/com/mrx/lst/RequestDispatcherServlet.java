package com.mrx.lst;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrx.entity.Stu;

/**
 * Servlet implementation class RequestDispatcherServlet
 */
@WebServlet("/request_dispatch")
public class RequestDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request对象提供了一个域对象，用于在转发时传值
		String data = "大家好，我是孤傲苍狼，我正在总结JavaWeb";
		/**
		 * 将数据存放到request对象中,此时把request对象当作一个Map容器来使用
		 */
		request.setAttribute("data", data);
		// 客户端访问RequestDemo06这个Servlet后，RequestDemo06通知服务器将请求转发(forward)到test.jsp页面进行处理
		request.getRequestDispatcher("/test.jsp").forward(request, response);
	}

}
