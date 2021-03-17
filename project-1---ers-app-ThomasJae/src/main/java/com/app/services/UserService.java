package com.app.services;

import java.util.ArrayList;
import java.util.List;

import com.app.beans.User;
import com.app.dao.TicketDao;
import com.app.dao.UserDao;
import com.app.exceptions.InvalidCredentialsException;
import com.app.exceptions.UsernameAlreadyExistsException;

/**
 * This class should contain the business logic for performing operations on
 * users
 */
public class UserService {

	UserDao userDao;
	TicketDao ticketDao;

	public UserService(UserDao udao, TicketDao tdao) {
		this.userDao = udao;
		this.ticketDao = tdao;
	}

	/**
	 * Validates the username and password, and return the User object for that user
	 * 
	 * @throws InvalidCredentialsException
	 *             if either username is not found or password does not match
	 * @return the User who is now logged in
	 */
	public User login(String username, String password) {
		User loggedIn = new User(); // create user object
		loggedIn = userDao.getUser(username, password);	// grab user from DB
		if (loggedIn == null || loggedIn.getUsername() == null) {
			throw new InvalidCredentialsException();
		}
		return loggedIn;
	}

	/**
	 * Creates the specified User in the persistence layer
	 * 
	 * @param newUser
	 *            the User to register
	 * @throws UsernameAlreadyExistsException
	 *             if the given User's username is taken
	 */
	public void register(User newUser) {
		List<User> userList = new ArrayList<User>(); // temp list of user objects
		userList = userDao.getAllUsers(); // grab list of all users
		User userExists = new User(); // see if user exists by grabbing only if username AND password match
		
		userExists = userDao.getUser(newUser.getUsername(), newUser.getPassword()); // required for jUnit test
		if (userExists == null || userExists.getUsername() == null) { // if userExists couldn't find exact match
			for (User u : userList) { // go through the list again, looking only for username
				if (u.getUsername().equals(newUser.getUsername())) { // see if any username has already been taken
					throw new UsernameAlreadyExistsException(); // throw exception
				}
			}
		} else if (userExists.getUsername().equals(newUser.getUsername())) { // if match found
			throw new UsernameAlreadyExistsException(); // throw exception
		}

		userDao.addUser(newUser); // add the user object to the database
		//this.login(newUser.getUsername(), newUser.getPassword()); // login new user for session
	}
}
