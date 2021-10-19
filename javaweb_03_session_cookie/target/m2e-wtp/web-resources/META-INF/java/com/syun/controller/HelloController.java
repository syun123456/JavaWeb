package com.syun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// 等同於在servlet中的request或session存放資訊
		mv.addObject("msg", "HelloSpringMVC");
		
		// 等同於request的轉發或response的重定向
		mv.setViewName("hello"); // /jsp/hello.jsp
		
		return mv;
	}
	
}
