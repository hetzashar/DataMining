package com.datamining.beans;

import java.io.Serializable;
import java.util.Date;

public class UserlogBean implements Serializable {
	
	int Id;
	private String username;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private boolean enabled;
    private Date created;
    private Date modified;
    private Date lastlogin;

	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getFName() {
		return fname;
	}
	public void setFName(String fname) {
		this.fname = fname;
	}
	
	public String getLName() {
		return lname;
	}
	public void setLName(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
