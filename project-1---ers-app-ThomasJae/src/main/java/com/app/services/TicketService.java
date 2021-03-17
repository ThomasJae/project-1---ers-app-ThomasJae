package com.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.Ticket;
import com.app.dao.TicketDao;
import com.app.beans.UserRole.URole;
import com.app.exceptions.UnauthorizedException;
import com.app.beans.User;

/**
 * This class should contain the business logic for performing operations on
 * Tickets
 */
public class TicketService {
	
	public TicketDao tdao;
	
	public TicketService(TicketDao dao) {
		this.tdao = dao;
	}
	
	/**
	 * Creates a new ers Ticket for a given User
	 * 
	 * @return the Ticket object that was created
	 */
	public Ticket createNewTicket(User u) {
		// account members to set:
		// (double reimb_amount), timestamp reimb_submitted, (String reimb_description),
		// int reimb_author, int reimb_status_id, (int reimb_type_id)
		Ticket newTicket = new Ticket(); // set account variables here
		newTicket.setSubmitted(LocalDateTime.now());
		newTicket.setAuthorId(u.getId());
		newTicket.setTicketStatus(RStatus.PENDING);
		/* We started off the ticket, prompt user for amount, description,
		 * and type
		 * Then, add new ticket to list of tickets from user
		 * Finally, add ticket to database
		 */
		List<Ticket> tList = new ArrayList<Ticket>(); // temp list
		// now, add to user list of tickets
		if (tdao.getTicketsByUser(u) != null) {
			tList = tdao.getTicketsByUser(u); // copy list of current user tickets to temp list
		}
		tList.add(newTicket); // append new ticket onto the list
		u.setTickets(tList); // update user list of tickets with new ticket added
		return newTicket; // return created ticket object
	}
	
	/**
	 * Approve or reject an ers ticket request.
	 * 
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException
	 *             if logged in user is not an Employee
	 * @return reimb_status_id 2 if ticket is approved, 
	 * 			   or reimb_status_id 3 if denied
	 */
	public boolean approveOrDenyTicket(Ticket t, boolean approval, User u) {
		//Optional<User> loggedIn = SessionCache.getCurrentUser();
		//User current = new User();
		//current = loggedIn.get();
		if (u.getUserType() != URole.MANAGER) {
			throw new UnauthorizedException();
		} else {
			if (approval == true) {
				t.setTicketStatus(RStatus.APPROVED);
			}
			else if (approval == false) {
				t.setTicketStatus(RStatus.DENIED);
			}
			t.setResolved(LocalDateTime.now());
			t.setResolverId(u.getId());
			tdao.updateTicket(t);
		}
		return approval;
	}
}
