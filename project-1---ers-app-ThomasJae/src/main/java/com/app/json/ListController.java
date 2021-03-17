package com.app.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.User;
import com.app.controllers.CreateController;
import com.app.beans.Ticket;
import com.app.dao.TicketDaoDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListController {

	public final static Logger logger = Logger.getLogger(ListController.class);
	
	public static void listFinder(HttpServletRequest req, HttpServletResponse res)
		throws JsonProcessingException, IOException{
		logger.setLevel(Level.ALL);
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("currentuser");
		List<Ticket> tList = new ArrayList<Ticket>();
		if (user != null) {
			TicketDaoDB tdao = new TicketDaoDB();
			tList = tdao.getTicketsByUser(user);
			logger.info("List of reimbursements displayed");
			res.getWriter().write(new ObjectMapper().writeValueAsString(tList));
		} else {
			logger.warn("List is empty");
			res.getWriter().write(new ObjectMapper().writeValueAsString(tList));
		}
	}
}
