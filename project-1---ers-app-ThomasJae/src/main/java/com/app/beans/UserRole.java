package com.app.beans;

/**
 * Represents a Role of the user.
 * Can be EMPLOYEE or MANAGER.
 */
public class UserRole {
	
	private int role_id;
	public static enum URole {
		EMPLOYEE, MANAGER
	}
	private URole uRole;
	
	public UserRole() {
		super();
	}
	public UserRole(int role_id, URole uRole) {
		super();
		this.role_id = role_id;
		this.uRole = uRole;
	}

	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public URole getuRole() {
		return uRole;
	}
	public void setuRole(URole uRole) {
		this.uRole = uRole;
	}
	
	@Override
	public String toString() {
		return "UserRole [role_id=" + role_id + ", uRole=" + uRole + "]";
	}
}
