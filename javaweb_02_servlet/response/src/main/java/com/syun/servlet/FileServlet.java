package com.syun.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 要下載的文件路徑
		String realPath = "C:\\Workspace\\javaweb_02_servlet\\response\\src\\main\\resources\\test.jpg";
		// 下載文件的名稱
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		System.out.println(fileName);
		// 設定讓瀏覽器可以下載文件，Header設定
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
		// 獲得文件輸入流
		FileInputStream in = new FileInputStream(realPath);
		// 建立緩衝區
		int len = 0;
		byte[] buffer = new byte[1024];
		// 獲得OutputStream對象
		ServletOutputStream out = resp.getOutputStream();
		// 將FileInputStream寫入緩衝區，使用OutputStream將緩衝區資料輸出至客戶端
		while((len = in.read(buffer))>0) {
			out.write(buffer, 0, len);
		}
		
		in.close();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
