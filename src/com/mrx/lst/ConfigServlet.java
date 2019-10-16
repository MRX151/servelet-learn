package com.mrx.lst;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigServlet
 */
@WebServlet("/ConfigServlet")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ����ServletConfig�������������õĳ�ʼ������
	 */
	private ServletConfig config;

	/**
	 * ��servlet�����˳�ʼ��������web�����ڴ���servletʵ������ʱ��
	 * ���Զ�����Щ��ʼ��������װ��ServletConfig�����У����ڵ���servlet��init����ʱ��
	 * ��ServletConfig���󴫵ݸ�servlet������������Աͨ��ServletConfig����Ϳ��� �õ���ǰservlet�ĳ�ʼ��������Ϣ��
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置Content-type
		response.setHeader("Content-type", "text/html;charset=utf-8");
		
        //获取在web.xml中配置的初始化参数
        String paramVal = this.config.getInitParameter("name");//获取指定的初始化参数
        response.getWriter().print(paramVal);
        
        response.getWriter().print("<br/>");
      //获取所有的初始化参数
        Enumeration<String> e = config.getInitParameterNames();
        while(e.hasMoreElements()){
            String name = e.nextElement();
            String value = config.getInitParameter(name);
            response.getWriter().print(name + "=" + value + "<br/>");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
