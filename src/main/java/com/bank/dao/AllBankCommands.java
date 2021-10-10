package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.BankAccount;
import com.bank.model.UserAccount;

public class AllBankCommands implements GAccountDao<BankAccount> {
	
	private BankDbConnection con;
	public AllBankCommands() {};
	public AllBankCommands(BankDbConnection con) {
		this.con = con;
	}
	
	
	//@Override //checked
	public UserAccount getUser(String user) {
		
		UserAccount currUser = new UserAccount();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from users where user_name = ?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.setString(1, user);
			ResultSet results = pstat.executeQuery();

			while(results.next()) {
				currUser.setId(results.getInt(1));
				currUser.setUserName(results.getString(2));
				currUser.setPass(results.getString(3));
				currUser.setUserType(results.getString(4));
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return currUser;
	}
	
	
	//@Override  //checked
	public void addUser(String user, String pass) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "{? = call insert_user(?, ?, ?)}";
			CallableStatement cstat = connect.prepareCall(sql);
			cstat.registerOutParameter(1, Types.VARCHAR);
			cstat.setString(2, user);
			cstat.setString(3, pass);
			cstat.setString(4, "customer");
			cstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override  //checked
	public List<BankAccount> getAll() {
		List<BankAccount> allAccounts = new ArrayList<>();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from accounts";
			PreparedStatement pstat = connect.prepareStatement(sql);
			ResultSet results = pstat.executeQuery();
			
			while(results.next()) {
				BankAccount newAccount = new BankAccount(results.getInt(1), results.getString(2), results.getString(3), results.getFloat(4), 
							results.getString(5), results.getString(6), results.getString(7), results.getString(8));
				allAccounts.add(newAccount);
			  }
			}
		
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		System.out.println(allAccounts);
		return allAccounts;
	}

	
	@Override //checked
	public BankAccount getOneAccount(int num) {
		BankAccount oneAcct = new BankAccount();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from accounts where id=" + num;
			PreparedStatement pstat = connect.prepareStatement(sql);
			ResultSet results = pstat.executeQuery();
			
			while(results.next()) {
				oneAcct.setId(results.getInt(1));
				oneAcct.setFirstName(results.getString(2));
				oneAcct.setLastName(results.getString(3));
				oneAcct.setBalance(results.getFloat(4));
				oneAcct.setAcctType(results.getString(5));
				oneAcct.setAcctStatus(results.getString(6));
				oneAcct.setUserType(results.getString(7));
				oneAcct.setUserName(results.getString(8));
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return oneAcct;
	}
	
	
	//@Override 
	public BankAccount getOneAccount(String user) {
		BankAccount oneAcct = new BankAccount();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from accounts where user_name = ?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.setString(1, user);
			ResultSet results = pstat.executeQuery();
			
			while(results.next()) {
				oneAcct.setId(results.getInt(1));
				oneAcct.setFirstName(results.getString(2));
				oneAcct.setLastName(results.getString(3));
				oneAcct.setBalance(results.getFloat(4));
				oneAcct.setAcctType(results.getString(5));
				oneAcct.setAcctStatus(results.getString(6));
				oneAcct.setUserType(results.getString(7));
				oneAcct.setUserName(results.getString(8));
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return oneAcct;
	}

	
	@Override  //checked
	public void addAccount(BankAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "{? = call insert_account(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cstat = connect.prepareCall(sql);
			
			cstat.registerOutParameter(1, Types.VARCHAR);
			cstat.setString(2, entity.getFirstName());
			cstat.setString(3, entity.getLastName());
			cstat.setBigDecimal(4, java.math.BigDecimal.valueOf(entity.getBalance()));
			cstat.setString(5, entity.getAcctType());
			cstat.setString(6, "pending");
			cstat.setString(7, entity.getUserType());
			cstat.setString(8, entity.getUserName());
			cstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override  //checked
	public void updateAccount(BankAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "update accounts set balance = ? where id = ?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			
			pstat.setBigDecimal(1, java.math.BigDecimal.valueOf(entity.getBalance()));
			pstat.setInt(2, entity.getId());
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override  //checked
	public void deleteAccount(BankAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "delete from accounts where id=?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.setInt(1, entity.getId());
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override  //checked
	public void approveAccount(int num) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "update accounts set account_status = 'active' where id = " + num;
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.execute();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override  //checked
	public void denyAccount(int num) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "update accounts set account_status = 'denied' where id = " + num;
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.execute();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override  //checked
	public void withdraw(int actNum, float amt) {
		try(Connection connect = con.getDbConnection()) {
			String sql = "update accounts set balance = ? where id = ?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.setBigDecimal(1, java.math.BigDecimal.valueOf(amt));
			pstat.setInt(2, actNum);
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override  //checked
	public void deposit(int actNum, float amt) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "update accounts set balance = ? where id = ?";
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.setBigDecimal(1, java.math.BigDecimal.valueOf(amt));
			pstat.setInt(2, actNum);
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override  //checked
	public void transfer(int actNumSrc, int actNumDest, float amtSrc, float amtDest) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql1 = "update accounts set balance = ? where id = ?";
			String sql2 = "update accounts set balance = ? where id = ?";
			PreparedStatement pstat1 = connect.prepareStatement(sql1);
			pstat1.setBigDecimal(1, java.math.BigDecimal.valueOf(amtSrc));
			pstat1.setInt(2, actNumSrc);
			pstat1.execute();
			PreparedStatement pstat2 = connect.prepareStatement(sql2);
			pstat2.setBigDecimal(1, java.math.BigDecimal.valueOf(amtDest));
			pstat2.setInt(2, actNumDest);
			pstat2.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
