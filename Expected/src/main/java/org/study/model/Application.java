package org.study.model;

import java.util.Date;

public class Application {

	String Id;
	String userId;
	Date dob;
	String PLAN;
	String PLANDESC;
	
	public Application(String id) {
		this.Id = id;
	}
	public String getId() {
		return Id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPLAN() {
		return PLAN;
	}
	public void setPLAN(String pLAN) {
		PLAN = pLAN;
	}
	public String getPLANDESC() {
		return PLANDESC;
	}
	public void setPLANDESC(String pLANDESC) {
		PLANDESC = pLANDESC;
	}
	@Override
	public String toString() {
		return "Application [Id=" + Id + ", userId=" + userId + ", dob=" + dob + ", PLAN=" + PLAN + ", PLANDESC="
				+ PLANDESC + "]";
	}
	

}
