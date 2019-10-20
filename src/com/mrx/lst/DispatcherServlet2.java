package com.mrx.lst;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrx.entity.Student;

/**
 * Servlet implementation class DispatcherServlet2
 */
@WebServlet("/dispatcher2")
public class DispatcherServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student("jon", 18);
		request.setAttribute("student", student);
		request.getRequestDispatcher("/request_dispatch").forward(request, response);
	}

}
