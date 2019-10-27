package com.mrx.finalmvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;



/**
 * Servlet Filter implementation class MVCFilter
 */
//@WebFilter("/*")
public class MVCFilter implements Filter {

	FilterConfig filterConfig;
	
    /**
     * Default constructor. 
     */
    public MVCFilter() {
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
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = fConfig;
		//1.通过properties配置文件获取要扫描的包
		Properties properties = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String packages = properties.getProperty("packages");
		if(null != packages && packages.length() > 0) {
			
			Map<String, Method> url_method = new HashMap<>();
			Map<Method, Object> method_obj = new HashMap<>();			
			String contextPath = this.filterConfig.getServletContext().getContextPath();
			
			
			Set<Class<?>> set = new HashSet<>();
			String[] packs = packages.split(",");
			for(String pack:packs) {//把所有的class都加载到set中去
				ScannerUti.scan(pack, set);
			}
			for(Class<?> clazz : set) {//遍历set
				if(true == clazz.isAnnotationPresent(Controller.class) 
						&& true == clazz.isAnnotationPresent(RequestMapping.class)) {
					try {
						String url = contextPath;
						
						Object obj = clazz.newInstance();
						RequestMapping anno = clazz.getAnnotation(RequestMapping.class);
						url += anno.value();
						
						Method[] methods = clazz.getDeclaredMethods();
						for(Method m :methods) {
							if(true == m.isAnnotationPresent(RequestMapping.class)) {
								RequestMapping manno = m.getAnnotation(RequestMapping.class);
								String final_url = url + manno.value();
								url_method.put(final_url, m);
								method_obj.put(m, obj);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
			System.out.println("加载完毕，url_method："+url_method);
			System.out.println("加载完毕，method_obj："+method_obj);
		}
	}
}
