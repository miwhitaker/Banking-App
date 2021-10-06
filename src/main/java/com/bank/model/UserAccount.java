package com.bank.model;

public class UserAccount {

	private int id;
	private String userName;
	private String pass;
	private String userType;
	
	public UserAccount() {};
	
	public UserAccount(int id, String userName, String pass, String userType) {
		this.id = id;
		this.userName = userName;
		this.pass = pass;
		this.userType = userType;
	}
	
	public UserAccount(String userName, String pass, String userType) {
		this.userName = userName;
		this.pass = pass;
		this.userType = userType;
	}
	
	public UserAccount(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String password) {
		this.userName = password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String utype) {
		this.userType = utype;
	}
}
