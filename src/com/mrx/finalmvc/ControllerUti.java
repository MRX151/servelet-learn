package com.mrx.finalmvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUti {
	
	public static void handleRequest(HttpServletRequest request,HttpServletResponse response,Method m,Object obj) {
		
		try {
			String responseString = (String) m.invoke(obj, null);
			response.getWriter().write(responseString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
