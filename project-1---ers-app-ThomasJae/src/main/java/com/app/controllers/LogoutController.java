package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogoutController {
	
	public final static Logger logger = Logger.getLogger(LogoutController.class);

	public static String logout(HttpServletRequest req) {
		logger.setLevel(Level.ALL);
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		req.getSession().invalidate();
		logger.info("User logged out");
		return "logout.change";
	}
}
