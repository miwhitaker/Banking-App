package bank.com.model;

public class EditAccount {
	
	private String fname;
	private String lname;
	private double balance;
	private String acct_type;
	private String acct_status;
	private String user_type;
	
	public EditAccount() {};
	
	public EditAccount(String fname, String lname, double balance, String acct_type, String acct_status, String user_type) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.balance = balance;
		this.acct_type = acct_type;
		this.acct_status = acct_status;
		this.user_type = user_type;
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
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double bal) {
		this.balance = bal;
	}
	
	public String getAcctType() {
		return acct_type;
	}
	
	public void setAcctType(String type) {
		this.acct_type = type
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
}
