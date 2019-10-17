package com.mrx.lst;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrx.entity.Student;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		Student stu = (Student)session.getAttribute("stu");
		
		if(null == stu) {
			stu = new Student("jon", 1);
			session.setAttribute("times", stu);
		}else {
			stu.setAge(stu.getAge() + 1);
		}
		response.getWriter().append("您访问了" + stu.getAge() + "次").append(request.getContextPath());
	}

}
