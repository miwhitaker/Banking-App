package com.bank.dao;

import java.util.List;

public interface GAccountDao<T> {
	
	//Interface for all bank CRUD commands

	List<T> getAll();
	T getByName();
	void insert(T thing);
	void update(T thing);
	void delete(T thing);
	
}
