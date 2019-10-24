package com.mrx.lst;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class HelloFilter
 */
//@WebFilter("/*")
public class HelloFilter implements Filter {
	
	private FilterConfig config;
	
    /**
     * Default constructor. 
     */
    public HelloFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//会在web容器开始运行时创建
//		System.out.println("------------过滤器初始化-----------");
		this.config = fConfig;
	}
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		//会在web容器结束运行时被销毁
//		System.out.println("------------过滤器销毁-----------");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		
		System.out.println("HelloFilter 执行前！！！即将访问："+url);
//		Enumeration<String> names = config.getInitParameterNames();
//		while (names.hasMoreElements()) {
//			String string = (String) names.nextElement();
//			System.out.println(string + "=" + config.getInitParameter(string));
//		}
		// pass the request along the filter chain
		chain.doFilter(request, response);//让目标资源执行，放行
		System.out.println("HelloFilter 结束！！！");
	}



}
