package com.bank.model;

public class BankAccount {
	
	private int id;
	private String fname;
	private String lname;
	private float balance;
	private String acct_type;
	private String acct_status;
	private String user_type;
	private String user_name;
	
	public BankAccount() {};
	
	public BankAccount(int id, String fname, String lname, float balance, String acct_type, String acct_status, String user_type, String user_name) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.balance = balance;
		this.acct_type = acct_type;
		this.acct_status = acct_status;
		this.user_type = user_type;
		this.user_name = user_name;
	}
	
	public BankAccount(String fname, String lname, float balance, String acct_type, String acct_status, String user_type, String user_name) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.balance = balance;
		this.acct_type = acct_type;
		this.acct_status = acct_status;
		this.user_type = user_type;
		this.user_name = user_name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	public void setFirstName(String firstName) {
		this.fname = firstName;
	}
	
	public String getLastName() {
		return lname;
	}
	
	public void setLastName(String lastName) {
		this.lname = lastName;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public void setBalance(float bal) {
		this.balance = bal;
	}
	
	public String getAcctType() {
		return acct_type;
	}
	
	public void setAcctType(String type) {
		this.acct_type = type;
	}
	
	public String getAcctStatus() {
		return acct_status;
	}
	
	public void setAcctStatus(String status) {
		this.acct_status = status;
	}
	
	public String getUserType() {
		return user_type;
	}
	
	public void setUserType(String utype) {
		this.user_type = utype;
	}
	
	public String getUserName() {
		return user_name;
	}
	
	public void setUserName(String uname) {
		this.user_name = uname;
	}
	
	@Override
	public String toString() {
		return "first name: " + fname + "last name: " + lname + "balance: " + balance + "act type: " + acct_type 
				+ "act status:" + acct_status + "user type: " + user_type;
	}
}
