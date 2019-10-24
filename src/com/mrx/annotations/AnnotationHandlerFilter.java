package com.mrx.annotations;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 用于处理自定义的注解类WebServlet、WebInitParam
 */
//@WebFilter("/AnnotationHandlerFilter")
public class AnnotationHandlerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AnnotationHandlerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * 初始化时扫描指定包下哪些类使用了WebServlet
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private void addServletClassToServletContext(String packagename,Map<String,Class<?>> map) {
//		Set<Class<?>> set = 
	}
}
