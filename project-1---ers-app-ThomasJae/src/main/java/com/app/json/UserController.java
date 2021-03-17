package com.app.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.User;
import com.app.beans.Ticket;
import com.app.beans.UserRole.URole;
import com.app.controllers.CreateController;
import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.ReimbursementType.RType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {

	public final static Logger logger = Logger.getLogger(UserController.class);

	public static void userFinder(HttpServletRequest req, HttpServletResponse res)
		throws JsonProcessingException, IOException{
		logger.setLevel(Level.ALL);
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("currentuser");
		logger.info("User found");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}
}
