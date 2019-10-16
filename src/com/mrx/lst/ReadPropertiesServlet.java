package com.mrx.lst;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadPropertiesServlet
 */
@WebServlet("/ReadPropertiesServlet")
public class ReadPropertiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("包内properties</br>").append(request.getContextPath());
		propertiesValue(response, "/WEB-INF/classes/com/mrx/lst/db.properties");
		response.getWriter().append("src目录下(包外)的properties</br>").append(request.getContextPath());
		propertiesValue(response, "/WEB-INF/classes/db.properties");
		response.getWriter().append("webroot目录下的properties</br>").append(request.getContextPath());
		propertiesValue(response, "/db.properties");
	}
	
	private void propertiesValue(HttpServletResponse response,String path) throws IOException {
		Properties prop = getProperties(path);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        response.getWriter().println("读取目录{"+ path+"}配置文件：</br>");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
        response.getWriter().println("<hr/>");
	}

	private Properties getProperties(String path) throws IOException{

		InputStream inputStream = this.getServletContext().getResourceAsStream(path);
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
	}
}
