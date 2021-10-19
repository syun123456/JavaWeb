package com.syun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syun.pojo.User;
import com.syun.utils.SendMail;

public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email    = req.getParameter("email");
		
		User user = new User(username, password, email);
		
		SendMail send = new SendMail(user);
		send.start();
		
		req.setAttribute("message", "註冊成功，請至信箱確認!");
		req.getRequestDispatcher("info.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
