package com.app.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.Ticket;
import com.app.beans.User;
import com.app.dao.TicketDaoDB;

public class ResolveController {
	
	public final static Logger logger = Logger.getLogger(ResolveController.class);
	
	public static String resolve(HttpServletRequest req) {
		logger.setLevel(Level.ALL);
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		
		User user = (User)req.getSession().getAttribute("currentuser");
		int id = Integer.parseInt(req.getParameter("idNum"));
		String choice = req.getParameter("radioRes");
		RStatus rStatus = RStatus.PENDING;
		if (choice.equals("APPROVED"))
			rStatus = RStatus.APPROVED;
		else if (choice.equals("DENIED"))
			rStatus = RStatus.DENIED;
		TicketDaoDB tdao = new TicketDaoDB();
		Ticket ticket = tdao.getTicket(id);
		
		ticket.setResolved(LocalDateTime.now());
		ticket.setResolverId(user.getId());
		ticket.setTicketStatus(rStatus);
		
		tdao.updateTicket(ticket);
		logger.info("Reimbursement status updated");
		return "homeplus.change";
	}
}
