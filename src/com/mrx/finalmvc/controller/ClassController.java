package com.mrx.finalmvc.controller;

import com.mrx.finalmvc.Controller;
import com.mrx.finalmvc.RequestMapping;

@Controller
@RequestMapping(value = "/class")
public class ClassController {
	
	@RequestMapping(value = "/test")
	public String testClass() {
		
		return "classController say hello!";
		
	}
}
