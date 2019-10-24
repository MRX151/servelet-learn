package com.mrx.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //Retention表名该注解只在运行时生效
@Target(TYPE) //target为TYPE表名该注解只能用于类或接口
public @interface WebServlet {
	//Servlet的默认访问url
	String value();//无default代表必填
	//Servlet的其他访问url
	String[] urlPatterns() default {""};
	//Servlet的描述
	String description() default "";
	//Servlet的显示名称
	String displayName() default "";
	//Servlet的名称
	String name() default "";
	//Servlet的init参数
	WebInitParam[] initParams() default {};
}
