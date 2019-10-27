package com.mrx.annotations;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/s3",description = "TestServlet_3")
public class TestServlet_3 {

	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.getWriter().write("你好，这里是Servlet3号");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
