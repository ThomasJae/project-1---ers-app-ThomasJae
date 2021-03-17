package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.dao.UserDaoDB;

public class NewUserController {

	public final static Logger logger = Logger.getLogger(NewUserController.class);
	
	public static String newUser(HttpServletRequest req) {
		logger.setLevel(Level.ALL);
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("newusername");
		String password = req.getParameter("newpassword");
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String choice = req.getParameter("radioURole");
		URole uRole = URole.EMPLOYEE;
		if (choice.equals("MANAGER"))
			uRole = URole.MANAGER;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		user.setUserType(uRole);
		UserDaoDB udao = new UserDaoDB();
		udao.addUser(user);
		logger.info("New user registered");
		return "index.change";
	}
}
