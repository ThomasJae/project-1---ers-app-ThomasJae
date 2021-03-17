package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Ignore;
import org.junit.Test;

import com.app.beans.Ticket;
import com.app.beans.ReimbursementType.RType;
import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.dao.TicketDao;
import com.app.dao.TicketDaoDB;
import com.app.dao.UserDao;
import com.app.dao.UserDaoDB;

/**
 * These tests use the database DAO implementation to read/write to a database. This will help you test
 * the integration of your JDBC code and the database.
 * 
 * If you are using a local database these tests will fail when run on a remote server (i.e. on Github Actions),
 * so they are ignored for scoring purposes. But, you can remove the @Ignore annotation while
 * you run these tests on your machine. Don't forget to put it back again before you
 * push your code though.
 */
@Ignore
/*
 * These tests will pass ONLY IF you TRUNCATE RESTART IDENTITY CASCADE
 * both the ers_users table
 * and the ers_reimbursement table
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseIntegrationTests extends PointWatcher {
	
	static TicketDao tdao = new TicketDaoDB();
	static Ticket toTest = new Ticket();
	static UserDao udao = new UserDaoDB();
	static User testUser = new User();
	
	@BeforeClass
	public static void setupTicketAndUser() {
		testUser.setUsername("testUser");
		testUser.setPassword("hello_world");
		testUser.setFirstName("TestFIRST");
		testUser.setLastName("TestLAST");
		testUser.setEmail("tuser@gmail.com");
		testUser.setUserType(URole.EMPLOYEE);
		udao.addUser(testUser);
		testUser = udao.getUser("testUser", "hello_world");
		toTest = new Ticket();
		toTest.setTicketAmount(10.00);
		toTest.setSubmitted(LocalDateTime.now());
		toTest.setDescription("Test ticket");
		toTest.setAuthorId(1);
		toTest.setTicketStatus(RStatus.PENDING);
		toTest.setTicketType(RType.OTHER);
		tdao.addTicket(toTest);
		toTest = tdao.getTicket(1);
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		tdao.removeTicket(tdao.getTicket(3));
		udao.removeUser(udao.getUser(2));
		udao.removeUser(udao.getUser(3));
	}
	
	@Test
	@Points(1)
	public void testAddTicket() {
		Ticket fromDB = tdao.getTicket(toTest.getTicketId());
		assertEquals(toTest, fromDB);
	}
	
	@Test
	@Points(3)
	public void testGetAllTickets() {
		Ticket secondTicket = new Ticket();
		secondTicket.setTicketAmount(35.00);
		secondTicket.setSubmitted(LocalDateTime.now());
		secondTicket.setDescription("Second ticket");
		secondTicket.setAuthorId(1);
		secondTicket.setTicketStatus(RStatus.PENDING);
		secondTicket.setTicketType(RType.LODGING);
		tdao.addTicket(secondTicket);
		secondTicket = tdao.getTicket(2);
		List<Ticket> all = tdao.getTickets();
		assertEquals(all.size(), 1);
		tdao.removeTicket(secondTicket);
	}
	
	@Test
	@Points(2)
	public void testUpdateTicket() {
		User newUser = new User();
		newUser.setUserType(URole.MANAGER);
		newUser.setUsername("newUser");
		newUser.setPassword("newPass");
		udao.addUser(newUser);
		newUser = udao.getUser("newUser", "newPass");
		Ticket updateTick = new Ticket();
		updateTick = tdao.getTicket(3);
		updateTick.setResolved(LocalDateTime.now());
		updateTick.setResolverId(newUser.getId());
		updateTick.setTicketStatus(RStatus.APPROVED);
		Ticket updated = tdao.updateTicket(updateTick);
		assertEquals(updateTick, updated);
	}
	
	@Test
	@Points(1)
	public void testDeleteTicket() {
		boolean success = tdao.removeTicket(toTest);
		if (success) {
			assertEquals(tdao.getTickets().size(), 0);
		} else {
			fail("Unable to delete account");
		}
	}
	
	@Test
	@Points(3)
	public void testGetTicketsByUser() {
		User u = new User();
		u.setUsername("user");
		u.setPassword("pass");
		u.setUserType(URole.MANAGER);
		udao.addUser(u);
		u = udao.getUser("user", "pass");
		Ticket secondTicket = new Ticket();
		secondTicket.setTicketAmount(35.00);
		secondTicket.setSubmitted(LocalDateTime.now());
		secondTicket.setDescription("Second ticket");
		secondTicket.setAuthorId(u.getId());
		secondTicket.setTicketStatus(RStatus.PENDING);
		secondTicket.setTicketType(RType.LODGING);
		tdao.addTicket(secondTicket);
		secondTicket = tdao.getTicket(3);
		// only secondAccount should be retrieved since it belongs to user 2
		List<Ticket> test = tdao.getTicketsByUser(u);
		assertEquals(test.size(), 1);
		assertEquals(secondTicket, test.get(0));
	}
	
	/*
	 * USER INTEGRATION TESTS
	 */
	
	@Test
	@Points(1)
	public void testzAddAndGetUser() {
		User actual = udao.getUser(testUser.getId());
		assertEquals(testUser, actual);
	}
	
	@Test
	@Points(3)
	public void testzGetAllUsers() {
		User second = new User();
		second.setUsername("test2");
		second.setPassword("someTestPassword");
		second.setUserType(URole.EMPLOYEE);
		udao.addUser(second);
		second = udao.getUser("test2", "someTestPassword");
		List<User> allUsers = udao.getAllUsers();
		assertEquals(allUsers.size(), 3);
		udao.removeUser(second);
	}
	
	@Test
	@Points(2)
	public void testzUpdateUser() {
		testUser = udao.getUser(2);
		testUser.setFirstName("Charlie");
		udao.updateUser(testUser);
		assertEquals(udao.getUser(testUser.getId()).getFirstName(), "Charlie");
	}
	
	@Test
	@Points(1)
	public void testzDeleteUser() {
		User user = new User();
		user = udao.getUser(3);
		boolean success = udao.removeUser(testUser);
		if (success) {
			assertEquals(udao.getAllUsers().size(), 2);
		} else {
			fail("Unable to delete account");
		}
	}
}
