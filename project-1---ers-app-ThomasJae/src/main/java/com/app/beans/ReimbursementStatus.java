package com.app.beans;

/**
 * Represents a Status of the reimbursement.
 * Can be PENDING, APPROVED or DENIED.
 */
public class ReimbursementStatus {
	
	private int status_id;
	public static enum RStatus {
		PENDING, APPROVED, DENIED
	}
	private RStatus rStatus;
	
	public ReimbursementStatus() {
		super();
	}
	public ReimbursementStatus(int status_id, RStatus rStatus) {
		super();
		this.status_id = status_id;
		this.rStatus = rStatus;
	}

	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public RStatus getrStatus() {
		return rStatus;
	}
	public void setrStatus(RStatus rStatus) {
		this.rStatus = rStatus;
	}
	
	@Override
	public String toString() {
		return "ReimbursementStatus [status_id=" + status_id + ", rStatus=" + rStatus + "]";
	}
}
