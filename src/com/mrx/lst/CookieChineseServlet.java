package com.mrx.lst;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieChineseServlet
 */
@WebServlet("/cookie2")
public class CookieChineseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length != 0) {
			for(Cookie cookie : cookies) {
				
				writer.write(cookie.getName() + ":" +  URLDecoder.decode(cookie.getValue(),"utf-8")+ "</br>");
			}
		}else {
			writer.write("未查到cookie");
			Cookie cookie = new Cookie("chinese", URLEncoder.encode("一段中文","utf-8"));
			response.addCookie(cookie);
		}
	}

}
