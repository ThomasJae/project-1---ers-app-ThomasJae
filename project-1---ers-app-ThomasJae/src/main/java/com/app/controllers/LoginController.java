package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.dao.TicketDaoDB;
import com.app.dao.UserDaoDB;
import com.app.exceptions.InvalidCredentialsException;
import com.app.services.UserService;

public class LoginController {

	public final static Logger logger = Logger.getLogger(LoginController.class);
	
	public static String login(HttpServletRequest req) {
		logger.setLevel(Level.ALL);
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		
		try {
			UserDaoDB udao = new UserDaoDB();
			TicketDaoDB tdao = new TicketDaoDB();
			UserService userv = new UserService(udao, tdao);
			user = userv.login(username, password);
			user.setTickets(tdao.getTicketsByUser(user));
		} catch (InvalidCredentialsException e) {
			return "wrongcreds.change";
		}
		
		req.getSession().setAttribute("loggedUsername", username);
		req.getSession().setAttribute("loggedPassword", password);
		req.getSession().setAttribute("currentuser", user);
		if (user.getUserType().equals(URole.MANAGER)) {
			logger.info("Manager has logged in");
			return "homeplus.change";
		} else {
			logger.info("Employee has logged in");
			return "home.change";
		}
	}
}
