package com.mrx.lst;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

/**
 * Servlet implementation class FieldConfigServlet
 */
@WebServlet("/FieldConfigServlet")
public class FieldConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FieldConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("读取全局配置").append(request.getContextPath()).append("</br>");
		
		//ServletConfig config = this.getServletConfig();
		ServletContext context = this.getServletContext();
		Enumeration<String> keys = context.getInitParameterNames();
	
		while (keys.hasMoreElements()) {
			String string = (String) keys.nextElement();
			response.getWriter().append("key:"+string).append(" value:"+context.getInitParameter(string)).append("</br>");
		}
		
	}

}
