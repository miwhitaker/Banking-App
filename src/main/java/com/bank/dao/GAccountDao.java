package com.bank.dao;

import java.util.List;

public interface GAccountDao<T> {
	
	//Interface for all bank CRUD commands

	List<T> getAll();
	T getOneAccount(int num);
	void updateAccount(T entity);
	void addAccount(T entity);
	void deleteAccount(T entity);
}
