package com.mrx.lst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write("你好 jon,");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lastAccessTime")) {
					String times = cookie.getValue();
					writer.write("上次访问时间：" + times + ",删除cookie");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		} else {
			response.getWriter().append("这是您第一次访问本站！");
			Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
			cookie.setMaxAge(60);
			response.addCookie(cookie);
		}

	}

}
