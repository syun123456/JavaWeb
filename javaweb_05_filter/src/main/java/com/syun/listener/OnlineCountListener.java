package com.syun.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// Listener有很多種，基本上不用不到

// 統計在線人數，透過Session統計
public class OnlineCountListener implements HttpSessionListener {
	// 實現HttpSessionListener的方法，當session被建立時就執行...
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
		
		if(onlineCount == null) {
			onlineCount = new Integer(1);
		}
		else {
			int count = onlineCount.intValue();
			onlineCount = new Integer(count + 1);
		}
		
		ctx.setAttribute("OnlineCount", onlineCount);
	}
	
	// 實現HttpSessionListener的方法，當session被銷毀時就執行...
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
		
		if(onlineCount == null) {
			onlineCount = new Integer(0);
		}
		else {
			int count = onlineCount.intValue();
			onlineCount = new Integer(count - 1);
		}
		
		ctx.setAttribute("OnlineCount", onlineCount);
	}
	
}
