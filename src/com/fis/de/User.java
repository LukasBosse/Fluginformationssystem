package com.fis.de;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userID;
	private String username;
	private String userType;
	
	public User(int userID, String username, String userType) {
		this.userID = userID;
		this.username = username;
		this.userType = userType;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
