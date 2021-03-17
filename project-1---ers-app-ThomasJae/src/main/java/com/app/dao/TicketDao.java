package com.app.dao;

import java.util.List;

import com.app.beans.Ticket;
import com.app.beans.User;

/**
 * The data access object interface for CRUD operations on Tickets.
 * This allows us to define a contract which can be implemented in different ways
 * using different methods of data storage and retrieval.
 */
public interface TicketDao {
	/**
	 * Adds a new ers ticket into the persistence layer
	 * @param t the ticket object to add
	 * @return the same ticket that was added
	 */
	public Ticket addTicket(Ticket t);
	
	/**
	 * Retrieves a ticket
	 * @param ticketId the id of the ticket to retrieve
	 * @return the ticket object
	 */
	public Ticket getTicket(Integer ticketId);
	
	/**
	 * Retrieves all tickets
	 * @return a list of all tickets
	 */
	public List<Ticket> getTickets();
	
	/**
	 * Retrieves tickets by a particular user
	 * @param u the user object to search by
	 * @return a list of tickets that the user owns
	 */
	public List<Ticket> getTicketsByUser(User u);
	
	/**
	 * Updates a specific ticket
	 * @param t the ticket to update
	 * @return the updated ticket
	 */
	public Ticket updateTicket(Ticket t);
	
	/**
	 * Deletes a ticket from the persistence layer
	 * @param t the ticket to delete
	 * @return true if the deletion was successful; false if not
	 */
	public boolean removeTicket(Ticket t);
}
