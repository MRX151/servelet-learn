package com.mrx.finalmvc.controller;

import com.mrx.finalmvc.Controller;
import com.mrx.finalmvc.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello world!——StudentController";
	}

}
