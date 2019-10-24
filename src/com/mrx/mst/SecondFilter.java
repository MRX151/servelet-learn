package com.mrx.mst;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class SecondFilter
 */
//@WebFilter("/SecondFilter")
public class SecondFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecondFilter() {
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
		System.out.println("SecondFilter 被触发");
		SecondRequestDecorator sDecorator = new SecondRequestDecorator((HttpServletRequest) request);
		chain.doFilter(sDecorator, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	class SecondRequestDecorator extends HttpServletRequestWrapper {

		private HttpServletRequest request;
		
		public SecondRequestDecorator(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			this.request = request;
		}
		
		@Override
		public String getParameter(String name) {
			System.out.println("SecondFilter 准备获取参数" + name);
			return this.request.getParameter(name) + "SecondFilter";
		}
		
	}
}
