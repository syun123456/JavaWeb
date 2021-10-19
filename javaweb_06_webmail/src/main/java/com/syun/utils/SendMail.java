package com.syun.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.syun.pojo.User;

// 使用多執行緒提高用戶體驗
public class SendMail extends Thread{
	private String from = "xxxxx@gmail.com";
	private String username = "xxxxx@gmail.com";
	private String password = "xxxxxxxxxxxxxxxx";
	private String host = "smtp.gmail.com";
	private User user;
	
	public SendMail(User user) {
		this.user = user;
	}
	
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", host); // 設定gmail伺服器
			prop.put("mail.transport.protocol", "smtp"); // 郵件發送協議
			prop.put("mail.smtp.auth", "true"); // 驗證用戶帳號密碼
			prop.put("mail.smtp.port", 587);
			
			// 建立整個應用程式所需環境資訊的Session物件
			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			// 開啟Session的Debug模式，可以看到程式發送Mail的運行狀態
			session.setDebug(true);
			// 透過Session得到Transport物件
			Transport ts = session.getTransport("smtp");
			// 使用郵件帳號及授權碼連上Mail Server
			ts.connect(host, from, password);
			// 建立郵件內容(寫郵件)
			// 建立郵件物件
			MimeMessage message = new MimeMessage(session);
			// 建立郵件發送人
			message.setFrom(new InternetAddress(from));
			// 建立郵件收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			// 建立郵件標題
			message.setSubject("註冊通知信");
			// 建立郵件內容
			String info = "恭喜註冊成功，" + user.getUsername();
			message.setContent(info, "text/html;charset=UTF-8");
			message.saveChanges();
			// 發送郵件
			ts.sendMessage(message, message.getAllRecipients());
			// 關閉連接
			ts.close();
			
			System.out.println("Finish.....");
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
