package com.mrx.lst;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrx.entity.Stu;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();
		System.out.println("session id = " + session.getId());
		if(session.isNew()) {
			System.out.println("创建session");
		}else {
			System.out.println("获取session");
			//手工销毁session
			session.invalidate();
			session = request.getSession();
		}
		System.out.println("session id = " + session.getId());
		
		Stu stu = (Stu) session.getAttribute("stu");

		if (null == stu) {
			stu = new Stu("jon", 1);
			session.setAttribute("stu", stu);
		} else {
			stu.setAge(stu.getAge() + 1);
		}

		Object t = session.getAttribute("times");
		int times;
		if(null == t) {
			times = 0;
		}else {
			times = (int)t;
		}
		times++;
		session.setAttribute("times", times);

		response.getWriter().append("您访问了" + times + "次").append(request.getContextPath());
	}

}
