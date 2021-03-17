package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.ReimbursementType.RType;
import com.app.beans.Ticket;
import com.app.beans.User;
import com.app.utils.ConnectionUtil;

/**
 * Implementation of TicketDao which reads/writes to a database
 */
public class TicketDaoDB implements TicketDao {

	public Ticket addTicket(Ticket t) {
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_submitted, "
					+ "reimb_description, reimb_author, reimb_status_id, reimb_type_id) "
					+ "values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, t.getTicketAmount());
			Timestamp ts = Timestamp.valueOf(t.getSubmitted());
			ps.setTimestamp(2, ts);
			ps.setString(3, t.getDescription());
			ps.setInt(4, t.getAuthorId());
			ps.setInt(5, 1); // new tickets start as PENDING
			String strType = t.getTicketType().toString();
			if (strType.equals("LODGING"))
				ps.setInt(6, 1);
			else if (strType.equals("TRAVEL"))
				ps.setInt(6, 2);
			else if (strType.equals("FOOD"))
				ps.setInt(6, 3);
			else if (strType.equals("OTHER"))
				ps.setInt(6, 4);
			ps.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public Ticket getTicket(Integer ticketId) {
		Ticket gotTicket = new Ticket();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = " + ticketId + ";";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				gotTicket.setTicketId(rs.getInt(1));
				gotTicket.setTicketAmount(rs.getDouble(2));
				Timestamp ts = rs.getTimestamp(3);
				LocalDateTime localts = null;
				localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
				gotTicket.setSubmitted(localts);
				ts = rs.getTimestamp(4);
				if (ts != null) {
					localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
					gotTicket.setResolved(localts);
				} else
					gotTicket.setResolved(null);
				gotTicket.setDescription(rs.getString(5));
				gotTicket.setAuthorId(rs.getInt(6));
				gotTicket.setResolverId(rs.getInt(7));
				int status = rs.getInt(8);
				if (status == 1)
					gotTicket.setTicketStatus(RStatus.PENDING);
				else if (status == 2)
					gotTicket.setTicketStatus(RStatus.APPROVED);
				else if (status == 3)
					gotTicket.setTicketStatus(RStatus.DENIED);
				else
					gotTicket.setTicketStatus(null);
				int type = rs.getInt(9);
				if (type == 1)
					gotTicket.setTicketType(RType.LODGING);
				else if (type == 2)
					gotTicket.setTicketType(RType.TRAVEL);
				else if (type == 3)
					gotTicket.setTicketType(RType.FOOD);
				else if (type == 4)
					gotTicket.setTicketType(RType.OTHER);
				else
					gotTicket.setTicketType(null);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gotTicket;
	}

	public List<Ticket> getTickets() {
		List<Ticket> tList = new ArrayList<Ticket>();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_reimbursement";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				Ticket t = new Ticket();
				t.setTicketId(rs.getInt(1));
				t.setTicketAmount(rs.getDouble(2));
				Timestamp ts = rs.getTimestamp(3);
				LocalDateTime localts = null;
				localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
				t.setSubmitted(localts);
				ts = rs.getTimestamp(4);
				if (ts != null) {
					localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
					t.setResolved(localts);
				} else
					t.setResolved(null);
				t.setDescription(rs.getString(5));
				t.setAuthorId(rs.getInt(6));
				t.setResolverId(rs.getInt(7));
				int status = rs.getInt(8);
				if (status == 1)
					t.setTicketStatus(RStatus.PENDING);
				else if (status == 2)
					t.setTicketStatus(RStatus.APPROVED);
				else if (status == 3)
					t.setTicketStatus(RStatus.DENIED);
				else
					t.setTicketStatus(null);
				int type = rs.getInt(9);
				if (type == 1)
					t.setTicketType(RType.LODGING);
				else if (type == 2)
					t.setTicketType(RType.TRAVEL);
				else if (type == 3)
					t.setTicketType(RType.FOOD);
				else if (type == 4)
					t.setTicketType(RType.OTHER);
				else
					t.setTicketType(null);
				tList.add(t);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tList;
	}

	public List<Ticket> getTicketsByUser(User u) {
		List<Ticket> tList = new ArrayList<Ticket>();
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = " + u.getId() + ";";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				Ticket t = new Ticket();
				t.setTicketId(rs.getInt(1));
				t.setTicketAmount(rs.getDouble(2));
				Timestamp ts = rs.getTimestamp(3);
				LocalDateTime localts = null;
				localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
				t.setSubmitted(localts);
				ts = rs.getTimestamp(4);
				if (ts != null) {
					localts = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
					t.setResolved(localts);
				} else
					t.setResolved(null);
				t.setDescription(rs.getString(5));
				t.setAuthorId(rs.getInt(6));
				t.setResolverId(rs.getInt(7));
				int status = rs.getInt(8);
				if (status == 1)
					t.setTicketStatus(RStatus.PENDING);
				else if (status == 2)
					t.setTicketStatus(RStatus.APPROVED);
				else if (status == 3)
					t.setTicketStatus(RStatus.DENIED);
				else
					t.setTicketStatus(null);
				int type = rs.getInt(9);
				if (type == 1)
					t.setTicketType(RType.LODGING);
				else if (type == 2)
					t.setTicketType(RType.TRAVEL);
				else if (type == 3)
					t.setTicketType(RType.FOOD);
				else if (type == 4)
					t.setTicketType(RType.OTHER);
				else
					t.setTicketType(null);
				tList.add(t);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tList;
	}

	public Ticket updateTicket(Ticket t) {
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_resolver = ?, "
					+ "reimb_status_id = ? WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp ts = Timestamp.valueOf(t.getResolved());
			ps.setTimestamp(1, ts);
			ps.setInt(2, t.getResolverId());
			String status = t.getTicketStatus().toString();
			if (status.equals("PENDING"))
				ps.setInt(3, 1);
			else if (status.equals("APPROVED"))
				ps.setInt(3, 2);
			else if (status.equals("DENIED"))
				ps.setInt(3, 3);
			ps.setInt(4, t.getTicketId());
			ps.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public boolean removeTicket(Ticket t) {
		boolean deleted = false;
		try {
			//Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			ConnectionUtil cu = new ConnectionUtil();
			Connection conn = cu.getConnection();
			
			String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getTicketId());
			ps.execute();
			deleted = true;
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}

}
