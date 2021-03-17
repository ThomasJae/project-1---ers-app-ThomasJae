package com.app.beans;

/**
 * Represents a Type of reimbursement.
 * Can be LODGING, TRAVEL, FOOD or OTHER.
 */
public class ReimbursementType {
	
	private int type_id;
	public static enum RType {
		LODGING, TRAVEL, FOOD, OTHER
	}
	private RType rType;
	
	public ReimbursementType() {
		super();
	}
	public ReimbursementType(int type_id, RType rType) {
		super();
		this.type_id = type_id;
		this.rType = rType;
	}

	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public RType getrType() {
		return rType;
	}
	public void setrType(RType rType) {
		this.rType = rType;
	}
	
	@Override
	public String toString() {
		return "ReimbursementType [type_id=" + type_id + ", rType=" + rType + "]";
	}
}
