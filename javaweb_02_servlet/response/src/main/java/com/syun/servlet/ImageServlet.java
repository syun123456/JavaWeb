package com.syun.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 瀏覽器每3秒自動刷新一次
		resp.setHeader("refresh", "3");
		// 在記憶體建立圖片
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		// 取得圖片後才可進行修改
		Graphics2D g = (Graphics2D) image.getGraphics();
		// 設定圖片背景顏色
		g.setColor(Color.white); //先選顏色
		g.fillRect(0, 0, 80, 20); // 再選想要的區域
		// 設定圖片產生隨機數
		g.setColor(Color.BLUE);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(makeNum(), 0, 20);
		// 設定瀏覽器這個Request用圖片開啟
		resp.setContentType("image/jpeg");
		// 網頁有Cache機制，設定不使用Cache
		resp.setDateHeader("expires", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		// 把圖片傳給瀏覽器
		ImageIO.write(image, "jpeg", resp.getOutputStream());
		
	}
	
	// 產生隨機數
	private String makeNum() {
		Random random = new Random();
		String num = random.nextInt(9999999)+"";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 7-num.length(); i++) {
			sb.append(0);
		}
		num = sb.toString() + num;
		return num;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
