package com.mrx.sps;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SleepFormServlet
 */
@WebServlet("/sleep_form")
public class SleepFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println("收到表单请求，username=" + request.getParameter("username") + new Date().toString());
		try {
			Thread.sleep(3000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("主线程休眠了3s");
	}

}
