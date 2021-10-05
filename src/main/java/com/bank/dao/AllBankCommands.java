package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.EditAccount;

public class AllBankCommands implements GAccountDao<EditAccount> {
	
	private BankDbConnection con;
	
	public AllBankCommands() {};
	public AllBankCommands(BankDbConnection con) {
		this.con = con;
	}

	@Override
	public List<EditAccount> getAll() {
		List<EditAccount> allAccounts = new ArrayList<>();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from accounts";
			PreparedStatement pstat = connect.prepareStatement(sql);
			ResultSet results = pstat.executeQuery();
			
			while(results.next()) {
				EditAccount newAccount = new EditAccount(results.getInt(1), results.getString(2), results.getString(3), results.getFloat(4), 
							results.getString(5), results.getString(6), results.getString(7));
				allAccounts.add(newAccount);
			}
		}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		System.out.println(allAccounts);
		return allAccounts;
	}

	@Override
	public EditAccount getOneAccount(int num) {
		EditAccount oneAcct = new EditAccount();
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "select * from accounts where id=" + num;
			PreparedStatement pstat = connect.prepareStatement(sql);
			ResultSet results = pstat.executeQuery();
			oneAcct.setId(results.getInt(1));
			oneAcct.setFirstName(results.getString(2));
			oneAcct.setLastName(results.getString(3));
			oneAcct.setBalance(results.getFloat(4));
			oneAcct.setAcctType(results.getString(5));
			oneAcct.setAcctStatus(results.getString(6));
			oneAcct.setUserType(results.getString(7));
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return oneAcct;
	}

	@Override
	public void addAccount(EditAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "{? = call insert_account(?, ?, ?, ?, ?, ?)}";
			CallableStatement cstat = connect.prepareCall(sql);
			
			cstat.registerOutParameter(1, Types.VARCHAR);
			cstat.setString(2, entity.getFirstName());
			cstat.setString(3, entity.getLastName());
			cstat.setBigDecimal(4, java.math.BigDecimal.valueOf(entity.getBalance()));
			cstat.setString(5, entity.getAcctType());
			cstat.setString(6, entity.getAcctStatus());
			cstat.setString(7, entity.getUserType());
			cstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccount(EditAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "insert into accounts(balance) values(?) where id=" + entity.getId();
			PreparedStatement pstat = connect.prepareStatement(sql);
			
			pstat.setBigDecimal(1, java.math.BigDecimal.valueOf(entity.getBalance()));
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(EditAccount entity) {
		
		try(Connection connect = con.getDbConnection()) {
			String sql = "delete from accounts where name=" + entity.getId();
			PreparedStatement pstat = connect.prepareStatement(sql);
			pstat.execute();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
