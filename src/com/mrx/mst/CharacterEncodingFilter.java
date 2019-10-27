package com.mrx.mst;

import java.io.IOException;

import javax.management.RuntimeErrorException;
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
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter("/CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

	private FilterConfig config = null;
	public static final String defaultCharset = "utf-8";
	
    /**
     * Default constructor. 
     */
    public CharacterEncodingFilter() {
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
		String url = ((HttpServletRequest)request).getRequestURL().toString();
//		System.out.println("character filter 被触发，即将访问："+url);
		
		String charset = this.config.getInitParameter("charset");
		if(null == charset) {
			charset = this.defaultCharset;
		}
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		
		MyCharacterEncodingRequest mRequest = new MyCharacterEncodingRequest((HttpServletRequest)request);
		chain.doFilter(mRequest, response);
//		System.out.println("character filter 结束");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}

	/**
	 * Decorator模式，与继承直接override不同，它可以在不改变原类的逻辑下，动态地拓展对象的功能
	 * 
	 * HttpServletRequestWrapper是Servlet API中提供的HttpServletRequest的包装器，继承这个类就可以增强默认的Request方法
	 * 
	 * @author Administrator
	 *
	 */
	class MyCharacterEncodingRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		
		public MyCharacterEncodingRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		/**
		 * 覆写需要增加的方法
		 */
		@Override
		public String getParameter(String name) {
//			try {
//				String value = this.request.getParameter(name);
//				if(null == value) {
//					return null;
//				}
//				//如果不是以get方式获取到数据的，就直接返回获取到的值
//				if(!this.request.getMethod().equalsIgnoreCase("get")) {
//					return value;
//				}else {
//					//如果是以get方式获取到数据的，就对获取到的数据进行转码处理
//					value = new String(value.getBytes("ISO-8859-1"), this.request.getCharacterEncoding());
//					return value;
//				}
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
			System.out.println("CharacterEncodingFilter 准备获取参数" + name);
			return this.request.getParameter(name) + "CharacterEncodingFilter";
		}
		
	}
}
