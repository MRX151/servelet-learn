package com.mrx.lst;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrx.entity.Student;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class ContextServelet
 */
@WebServlet("/ContextServelet")
public class ContextServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig servletConfig) {
		this.config = servletConfig;
	}
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//设置Content-type
		response.setContentType("text/html;charset=UTF-8");
		//如果先执行的是getWriter，就会默认设置Content-Type为text/html;charset=ISO-8859-1。
		response.getWriter().append("你好 ").append(request.getContextPath());

		
		//通过config获取context
		ServletContext context = this.config.getServletContext();
		Student student = (Student) context.getAttribute("stu");
		if(null == student) {
			student = new Student("小明", 18);
			context.setAttribute("stu", student);
		}
		student.setAge(student.getAge() + 1);
		response.getWriter().append(student.toString());
	}

}
