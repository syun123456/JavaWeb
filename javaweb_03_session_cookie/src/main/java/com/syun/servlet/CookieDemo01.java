package com.syun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo01 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		// 伺服器從客戶端取得cookie
		Cookie[] cookies = req.getCookies();
		// 判斷cookie是否存在
		if(cookies != null) {
			out.write("上次登入網站時間：");
			
			for(int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookie.getName().equals("lastLoginTime")) {
					long lastLoginTime = Long.parseLong(cookie.getValue());
					Date date = new Date(lastLoginTime);
					out.write(date.toLocaleString());
				}
			}
		}
		else {
			out.write("第一次進入網站");
		}
		
		// 伺服器回應客戶端cookie
		Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
		// 設定cookie保留期限，預設為-1(不保留)
		cookie.setMaxAge(24*60*60);
		resp.addCookie(cookie);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
