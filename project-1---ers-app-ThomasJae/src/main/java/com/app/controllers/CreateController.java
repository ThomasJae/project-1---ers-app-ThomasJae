package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.ReimbursementType.RType;
import com.app.beans.Ticket;
import com.app.beans.User;
import com.app.dao.TicketDaoDB;
import com.app.services.TicketService;

public class CreateController {

	public final static Logger logger = Logger.getLogger(CreateController.class);
	
	public static String create(HttpServletRequest req) {
		logger.setLevel(Level.ALL);
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}

		User user = (User) req.getSession().getAttribute("currentuser");
		TicketDaoDB tdao = new TicketDaoDB();
		TicketService tserv = new TicketService(tdao);
		if (user != null) {
			Ticket ticket = tserv.createNewTicket(user);

			Double amt = Double.parseDouble(req.getParameter("amount"));
			String desc = req.getParameter("description");
			String type = req.getParameter("radioType");
			RType rType = RType.OTHER;
			if (type.equals("LODGING"))
				rType = RType.LODGING;
			else if (type.equals("TRAVEL"))
				rType = RType.TRAVEL;
			else if (type.equals("FOOD"))
				rType = RType.FOOD;
			else if (type.equals("OTHER"))
				rType = RType.OTHER;
			ticket.setTicketAmount(amt);
			ticket.setDescription(desc);
			ticket.setTicketType(rType);
			tdao.addTicket(ticket);

			String uType = user.getUserType().toString();
			if (uType.equals("MANAGER")) {
				logger.info("Manager has created a reimbursement request");
				return "homeplus.change";
			} else {
				logger.info("Employee has created a reimbursement request");
				return "home.change";
			}
		} else {
			logger.warn("User needs to login before creating a reimbursement request");
			return "index.change";
		}
	}
}
