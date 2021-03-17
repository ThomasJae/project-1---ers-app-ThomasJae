package com.app.driver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.beans.Ticket;
import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.ReimbursementType.RType;
import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.dao.TicketDao;
import com.app.dao.TicketDaoDB;
import com.app.dao.UserDao;
import com.app.dao.UserDaoDB;
import com.app.services.TicketService;
import com.app.services.UserService;
import com.app.exceptions.UsernameAlreadyExistsException;
import com.app.exceptions.InvalidCredentialsException;

public class ErsApplicationDriver {
	
	public final static Logger logger = Logger.getLogger(ErsApplicationDriver.class);
	
	public static void main(String[] args) {
		logger.setLevel(Level.ALL);
		UserDao udao = new UserDaoDB();
		TicketDao tdao = new TicketDaoDB();
		UserService userv = new UserService(udao, tdao);
		TicketService tserv = new TicketService(tdao);
		User user = new User();
		Ticket ticket = new Ticket();
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		// test addUser(User user)
		/*
		System.out.print("Username: ");
		input = scan.next();
		user.setUsername(input);
		System.out.print("Password: ");
		input = scan.next();
		user.setPassword(input);
		System.out.print("First name: ");
		input = scan.next();
		user.setFirstName(input);
		System.out.print("Last name: ");
		input = scan.next();
		user.setLastName(input);
		System.out.print("Email: ");
		input = scan.next();
		user.setEmail(input);
		System.out.print("Employee (1) or Manager (2): ");
		input = scan.next();
		if (input.equals("1"))
			user.setUserType(UserType.EMPLOYEE);
		else if (input.equals("2"))
			user.setUserType(UserType.MANAGER);
		udao.addUser(user);
		System.out.println("User Added to Database"); */
		///////////// SUCCESS //////////////////////////////
		// test getUser(Integer userId)
		//System.out.println(udao.getUser(1));
		///////////// SUCCESS //////////////////////////////
		// test getUser(String username, String passw)
		//user = udao.getUser("testUser", "testPass");
		//System.out.println(udao.getUser(user.getUsername(), user.getPassword()));
		///////////// SUCCESS //////////////////////////////
		// test getAllUsers();
		/*
		List<User> uList = new ArrayList<User>();
		uList = udao.getAllUsers();
		for (User u : uList) {
			System.out.println(u);
		} */
		///////////// SUCCESS //////////////////////////////
		// test updateUser(User u)
		/*
		user = udao.getUser(1);
		System.out.print("Username: ");
		input = scan.next();
		user.setUsername(input);
		System.out.print("Password: ");
		input = scan.next();
		user.setPassword(input);
		System.out.print("First name: ");
		input = scan.next();
		user.setFirstName(input);
		System.out.print("Last name: ");
		input = scan.next();
		user.setLastName(input);
		System.out.print("Email: ");
		input = scan.next();
		user.setEmail(input);
		System.out.print("Employee (1) or Manager (2): ");
		input = scan.next();
		if (input.equals("1"))
			user.setUserType(UserType.EMPLOYEE);
		else if (input.equals("2"))
			user.setUserType(UserType.MANAGER);
		udao.updateUser(user); */
		///////////// SUCCESS //////////////////////////////
		// test removeUser(User u)
		//user = udao.getUser(4);
		//udao.removeUser(user);
		///////////// SUCCESS //////////////////////////////
		// test addTicket(Ticket t)
		/*
		user = udao.getUser(2);
		System.out.print("Reimbursement amount: ");
		input = scan.next();
		Double amt = Double.parseDouble(input);
		ticket.setTicketAmount(amt);
		ticket.setSubmitted(LocalDateTime.now());
		System.out.println("Description:");
		input = scan.nextLine();
		input = scan.nextLine();
		ticket.setDescription(input);
		ticket.setAuthorId(user.getId());
		System.out.print("LODGING (1), TRAVEL (2), FOOD (3), or OTHER (4): ");
		input = scan.next();
		if (input.equals("1"))
			ticket.setTicketType(TicketType.LODGING);
		else if (input.equals("2"))
			ticket.setTicketType(TicketType.TRAVEL);
		else if (input.equals("3"))
			ticket.setTicketType(TicketType.FOOD);
		else if (input.equals("4"))
			ticket.setTicketType(TicketType.OTHER);
		else
			ticket.setTicketType(null);
		tdao.addTicket(ticket);
		System.out.println("\nReimbursement Request Submitted!"); */
		///////////// SUCCESS //////////////////////////////
		// test getTicket(Integer ticketId)
		//System.out.println(tdao.getTicket(1));
		///////////// SUCCESS //////////////////////////////
		// test getTickets()
		/*
		List<Ticket> tList = new ArrayList<Ticket>();
		tList = tdao.getTickets();
		for (Ticket t : tList) {
			System.out.println(t);
		} */
		////////////// SUCCESS /////////////////////////////
		// test getTicketsByUser(User u)
		/*
		user = udao.getUser("newUser", "newPass");
		List<Ticket> tList = new ArrayList<Ticket>();
		tList = tdao.getTicketsByUser(user);
		for (Ticket t : tList) {
			System.out.println(t);
		} */
		////////////// SUCCESS /////////////////////////////
		// test updateTicket(Ticket t)
		/*
		user = udao.getUser("newUser", "newPass");
		if (user.getUserType().equals(UserType.MANAGER)) {
			ticket = tdao.getTicket(2);
			ticket.setResolved(LocalDateTime.now());
			ticket.setResolverId(user.getId());
			System.out.print("APPROVE (1) or DENY (2) this reimbursement request: ");
			input = scan.next();
			if (input.equals("1"))
				ticket.setTicketStatus(TicketStatus.APPROVED);
			else if (input.equals("2"))
				ticket.setTicketStatus(TicketStatus.DENIED);
			tdao.updateTicket(ticket);
		}
		System.out.println(tdao.getTicket(2)); */
		////////////// SUCCESS /////////////////////////////
		// test removeTicket(Ticket t)
		//ticket = tdao.getTicket(3);
		//tdao.removeTicket(ticket);
		///////////// SUCCESS //////////////////////////////
		// test login(String username, String password)
		/*
		boolean running = true;
		while (running) {
			System.out.print("Enter your Username: ");
			String username = scan.next();
			System.out.print("Enter your Password: ");
			String password = scan.next();
			try {
				//currentUser = uServ.login(username, password);
				user = userv.login(username, password);
				user.setTickets(tdao.getTicketsByUser(user));
	
				System.out.println("Welcome back " + user.getFirstName() + " (" + username + ")");
				running = false;
			} catch (InvalidCredentialsException e) { // As the system, I reject login attempts with invalid
														// credentials
				logger.error("Invalid Credentials");
				System.out.print("This Username or Password is incorrect. Try again? Y/N: ");
				input = scan.next();
				if (input.equals("N") || input.equals("n")) {
					running = false;
				}
			}
		} */
		///////////// SUCCESS /////////////////////////////
		// test register(User newUser)
		/*
		System.out.print("Username: ");
		input = scan.next();
		user.setUsername(input);
		System.out.print("Password: ");
		input = scan.next();
		user.setPassword(input);
		System.out.print("First name: ");
		input = scan.next();
		user.setFirstName(input);
		System.out.print("Last name: ");
		input = scan.next();
		user.setLastName(input);
		System.out.print("Email: ");
		input = scan.next();
		user.setEmail(input);
		System.out.print("Employee (1) or Manager (2): ");
		input = scan.next();
		if (input.equals("1"))
			user.setUserType(URole.EMPLOYEE);
		else if (input.equals("2"))
			user.setUserType(URole.MANAGER);
		try {
			userv.register(user);
			logger.info("New user has been registered");
		} catch (UsernameAlreadyExistsException e) { // As the system, I reject registration attempts for
														// usernames that already exist
			System.out.println("This Username has already been taken. Please try again!");
			logger.error("Username Already Exists");
		} */
		////////////// SUCCESS ////////////////////////////
		// test createNewTicket(User u)
		/*
		user = udao.getUser(2);
		ticket = tserv.createNewTicket(user);
		System.out.print("Reimbursement amount: ");
		input = scan.next();
		Double amt = Double.parseDouble(input);
		ticket.setTicketAmount(amt);
		System.out.println("Description:");
		input = scan.nextLine();
		input = scan.nextLine();
		ticket.setDescription(input);
		System.out.print("LODGING (1), TRAVEL (2), FOOD (3), or OTHER (4): ");
		input = scan.next();
		if (input.equals("1"))
			ticket.setTicketType(RType.LODGING);
		else if (input.equals("2"))
			ticket.setTicketType(RType.TRAVEL);
		else if (input.equals("3"))
			ticket.setTicketType(RType.FOOD);
		else if (input.equals("4"))
			ticket.setTicketType(RType.OTHER);
		else
			ticket.setTicketType(null);
		tdao.addTicket(ticket); // add ticket to database */
		///////////// SUCCESS ////////////////////////////
		// test approveOrDenyTicket(Ticket t, boolean approval)
		/*
		user = userv.login("newUser", "newPass");
		ticket = tdao.getTicket(5);
		tserv.approveOrDenyTicket(ticket, false, user); */
		///////////// SUCCESS /////////////////////////////
		
		scan.close();
	}
}
