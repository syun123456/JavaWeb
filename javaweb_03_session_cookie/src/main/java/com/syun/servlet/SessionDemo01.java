package com.syun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syun.pojo.Person;

public class SessionDemo01 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 取得Session
		HttpSession session = req.getSession();
		// 設置session內容
		session.setAttribute("name", new Person("syun", 18));
		// 取得Session ID
		String sessionId = session.getId();
		// 判斷session是否為新建立
		if(session.isNew()) {
			resp.getWriter().write("Session建立成功，ID：" + sessionId);
		}
		else {
			resp.getWriter().write("Session已存在，ID：" + sessionId);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
