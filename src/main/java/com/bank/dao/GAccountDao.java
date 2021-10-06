package com.bank.dao;

import java.util.List;

public interface GAccountDao<T> {
	
	//Interface for all bank CRUD commands

	List<T> getAll();
	T getOneAccount(int num);
	void updateAccount(T entity);
	void addAccount(T entity);
	void deleteAccount(T entity);
	void approveAccount(int num);
	void denyAccount(int num);
	void withdraw(int actNum, float amt);
	void deposit(int actNum, float amt);
	void transfer(int actNumSrc, int actNumDest, float amt1, float amt2);
}
