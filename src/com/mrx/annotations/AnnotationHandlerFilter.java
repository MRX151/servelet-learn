package com.mrx.annotations;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用于处理自定义的注解类WebServlet、WebInitParam
 */
//@WebFilter(value = "/*")
public class AnnotationHandlerFilter implements Filter {

	private FilterConfig config = null;

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		System.out.println("annotationFilter被触发");
		HttpServletRequest sevRequest = (HttpServletRequest) request;
		HttpServletResponse sevResponse = (HttpServletResponse) response;
		Map<String, Object> objMap = (Map<String, Object>) this.config.getServletContext().getAttribute("servletObjMap");
		String uri = sevRequest.getRequestURI();
		String method_name = sevRequest.getMethod();
//		System.out.println("METHOD_NAME:"+method_name);
		Object obj = objMap.get(uri);
		
		if(null != obj) {
			try {
				//获取对象实例
				Class<?> servletClazz = obj.getClass();
				//获取方法实例
				Method doGetMethod = servletClazz.getDeclaredMethod("doGet", HttpServletRequest.class,HttpServletResponse.class);
				doGetMethod.invoke(obj,  sevRequest,sevResponse);
//				System.out.println("执行你方法的是：" + obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
//		chain.doFilter(request, response);//如果这里继续chain的话，就会陷入404的错误，顺带一提的是，尽管chain没有被触发，但之前由Filter包裹出来的结束逻辑还是可以正常执行
//		System.out.println("annotationFilter结束");
	}

	/**
	 * 初始化时扫描指定包下哪些类使用了WebServlet
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AnnotationHandlerFilter过滤器初始化开始");
		this.config = fConfig;
		String[] packages = fConfig.getInitParameter("packagenames").split(",");
		Map<String, Object> objMap = new HashMap();
		for (String packagename : packages) {
			addServletClassToServletContext(packagename,objMap);
		}
		this.config.getServletContext().setAttribute("servletObjMap", objMap);

		System.out.println("AnnotationHandlerFilter过滤器初始化结束,objMap:" + objMap);
	}

	private void addServletClassToServletContext(String packagename, Map<String, Object> objMap) {
		Set<Class<?>> set = ScanClassUtil.getClasses(packagename);
		String contextPath = this.config.getServletContext().getContextPath();//实际访问地址，前面一定会有contextPath，需要加上
		for (Class<?> class1 : set) {
			if (class1.isAnnotationPresent(WebServlet.class)) {// 如果包含这个注解
				
				Object obj = null;
				
				try {
					obj = class1.newInstance();//通过反射方式构造这个对象
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				WebServlet anonWebServlet = class1.getAnnotation(WebServlet.class);// 获取该类中，这个注解的实例
				String value = anonWebServlet.value();// 获取实例中value的配置
				if (!value.equals("")) {
					value = contextPath + value;//加上contextPath
					objMap.put(value, obj);
				}
				String[] urlPatterns = anonWebServlet.urlPatterns();// 获取实例中urlPattern的配置
				for (String s : urlPatterns) {
					if (!s.equals("")) {
						s = contextPath + s;//加上contextPath
						objMap.put(value, obj);
					}
				}
			}
		}

	}
}
