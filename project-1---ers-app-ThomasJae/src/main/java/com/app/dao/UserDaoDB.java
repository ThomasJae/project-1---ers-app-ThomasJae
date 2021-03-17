package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.app.beans.User;
import com.app.beans.UserRole.URole;
import com.app.utils.ConnectionUtil;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao {

	public User addUser(User user) {
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "INSERT INTO ers_users(ers_username, ers_password, user_first_name, user_last_name, "
					+ "user_email, user_role_id) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			if (user.getUserType() != null) {
				String uType = user.getUserType().toString();
				if (uType.equals("EMPLOYEE"))
					ps.setInt(6, 1);
				else if (uType.equals("MANAGER"))
					ps.setInt(6, 2);
			} else
				ps.setInt(6, 0);
			ps.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUser(Integer userId) {
		User gotUser = new User();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_users WHERE ers_users_id = " + userId + ";";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				gotUser.setId(rs.getInt(1));
				gotUser.setUsername(rs.getString(2));
				gotUser.setPassword(rs.getString(3));
				gotUser.setFirstName(rs.getString(4));
				gotUser.setLastName(rs.getString(5));
				gotUser.setEmail(rs.getString(6));
				int uType = (rs.getInt(7));
				if (uType == 1)
					gotUser.setUserType(URole.EMPLOYEE);
				else if (uType == 2)
					gotUser.setUserType(URole.MANAGER);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gotUser;
	}

	public User getUser(String username, String pass) {
		User gotUser = new User();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_users WHERE ers_username = '" + username + "' AND "
					+ "ers_password = '" + pass + "';";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				gotUser.setId(rs.getInt(1));
				gotUser.setUsername(rs.getString(2));
				gotUser.setPassword(rs.getString(3));
				gotUser.setFirstName(rs.getString(4));
				gotUser.setLastName(rs.getString(5));
				gotUser.setEmail(rs.getString(6));
				int uType = (rs.getInt(7));
				if (uType == 1)
					gotUser.setUserType(URole.EMPLOYEE);
				else if (uType == 2)
					gotUser.setUserType(URole.MANAGER);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gotUser;
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_users";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				int uType = (rs.getInt(7));
				if (uType == 0)
					user.setUserType(null);
				else if (uType == 1)
					user.setUserType(URole.EMPLOYEE);
				else if (uType == 2)
					user.setUserType(URole.MANAGER);
				userList.add(user);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User updateUser(User u) {
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "UPDATE ers_users SET ers_username = ?, ers_password = ?, user_first_name = ?, "
					+ "user_last_name = ?, user_email = ?, user_role_id = ? WHERE ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			String uType = u.getUserType().toString();
			if (uType.equals("EMPLOYEE"))
				ps.setInt(6, 1);
			else if (uType.equals("MANAGER"))
				ps.setInt(6, 2);
			ps.setInt(7, u.getId());
			ps.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public boolean removeUser(User u) {
		boolean deleted = false;
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			conn.setAutoCommit(false);
			String sql = "{? = call remove_user(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, u.getId());
			cs.execute();
			deleted = true;
			conn.commit();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}

}
