package com.syun.filter;

import java.io.IOException;

// 要選擇servlet裡面的Filter類
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	// 初始化
	public void init(FilterConfig filterConfig) throws ServletException {
		// 在web伺服器(Tomcat)啟動時，就會進行初始化
		System.out.println("Filter初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("Filter執行前");
		chain.doFilter(request, response); // 一定要寫，固定寫法，將request及response物件傳遞給要處理的servlet
		System.out.println("Filter執行後");
		
	}

	// 銷毀
	public void destroy() {
		// web伺服器關閉時，Filter才會被銷毀
		System.out.println("Filter銷毀");
	}
	
}
