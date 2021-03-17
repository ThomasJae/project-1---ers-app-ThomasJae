package com.app.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.ReimbursementType.RType;

/**
 * Represents an ers ticket request that an Employee can submit.
 * Every ticket can be of type LODGING, TRAVEL, FOOD, or OTHER.
 * Every ticket starts as PENDING status and must be APPROVED or DENIED by
 * 	a Manager.
 * Author and Submitted timestamp is from User requesting the ers, while
 * 	Resolver and Resolved timestamp is from Manager who reviews the ticket.
 */

public class Ticket implements Serializable {
	/**Automatically generated universally unique identifier*/
	private static final long serialVersionUID = -1114239370994391355L;
	
	private Integer ticketId;
	private Double ticketAmount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private String description;
	private Integer authorId;
	private Integer resolverId;
	private RStatus ticketStatus;
	private RType ticketType;
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Double getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(Double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public LocalDateTime getSubmitted() {
		return submitted;
	}
	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}
	public LocalDateTime getResolved() {
		return resolved;
	}
	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getResolverId() {
		return resolverId;
	}
	public void setResolverId(Integer resolverId) {
		this.resolverId = resolverId;
	}
	public RStatus getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(RStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public RType getTicketType() {
		return ticketType;
	}
	public void setTicketType(RType ticketType) {
		this.ticketType = ticketType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((resolverId == null) ? 0 : resolverId.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((ticketAmount == null) ? 0 : ticketAmount.hashCode());
		result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
		result = prime * result + ((ticketStatus == null) ? 0 : ticketStatus.hashCode());
		result = prime * result + ((ticketType == null) ? 0 : ticketType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolverId == null) {
			if (other.resolverId != null)
				return false;
		} else if (!resolverId.equals(other.resolverId))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (ticketAmount == null) {
			if (other.ticketAmount != null)
				return false;
		} else if (!ticketAmount.equals(other.ticketAmount))
			return false;
		if (ticketId == null) {
			if (other.ticketId != null)
				return false;
		} else if (!ticketId.equals(other.ticketId))
			return false;
		if (ticketStatus != other.ticketStatus)
			return false;
		if (ticketType != other.ticketType)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketAmount=" + ticketAmount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", authorId=" + authorId + ", resolverId="
				+ resolverId + ", ticketStatus=" + ticketStatus + ", ticketType=" + ticketType + "]";
	}
	
}