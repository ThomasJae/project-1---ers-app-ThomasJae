package com.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.app.beans.Ticket;
import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.dao.TicketDao;
import com.app.exceptions.UnauthorizedException;
import com.app.services.TicketService;

/**
 * These tests test the Ticket features.
 * Remember that tests follow the AAA pattern (arrange, act, assert).
 * First we arrange the initial values and setup any objects needed.
 * Then we act - call the method we are testing.
 * Finally we assert that the final state is what we expected to happen.
 */
public class TicketTests extends PointWatcher {
	
	@Mock
	TicketDao dao;
	
	@InjectMocks
	TicketService tSrv;
	
	@Test
	@Points(2)
	public void testCreateNewTicket() {
		User dummyUser = new User();
		Ticket ticket = tSrv.createNewTicket(dummyUser);
		assertEquals(ticket.getAuthorId(), dummyUser.getId());
		assertEquals(ticket.getTicketStatus(), RStatus.PENDING);
	}
	
	@Test
	@Points(2)
	public void testApproveTicket() {
		User dummyUser = new User();
		dummyUser.setUserType(URole.MANAGER);
		Ticket ticket = tSrv.createNewTicket(dummyUser);
		tSrv.approveOrDenyTicket(ticket, true, dummyUser);
		assertEquals(ticket.getTicketStatus(), RStatus.APPROVED);
		assertEquals(ticket.getResolverId(), dummyUser.getId());
	}
	
	@Test
	@Points(2)
	public void testDenyTicket() {
		User dummyUser = new User();
		dummyUser.setUserType(URole.MANAGER);
		Ticket ticket = tSrv.createNewTicket(dummyUser);
		tSrv.approveOrDenyTicket(ticket, false, dummyUser);
		assertEquals(ticket.getTicketStatus(), RStatus.DENIED);
		assertEquals(ticket.getResolverId(), dummyUser.getId());
	}
	
	@Test(expected=UnauthorizedException.class)
	@Points(1)
	public void testEmployeeCannotApproveTicket() {
		User dummyUser = new User();
		dummyUser.setUserType(URole.EMPLOYEE);
		Ticket ticket = tSrv.createNewTicket(dummyUser);
		tSrv.approveOrDenyTicket(ticket, true, dummyUser);
	}
}
