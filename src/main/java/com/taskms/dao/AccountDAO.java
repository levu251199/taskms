package com.taskms.dao;

import java.util.List;

import com.taskms.entity.Account;

public interface AccountDAO {
	// Find By ID
	Account findbyID(Integer id);

	// Find By Email
	Account findbyUsername(String email);

	// Find All
	List<Account> findAll();

	// Create
	Account create(Account entity);

	// Update
	void update(Account entity);

	// Delete
	Account delete(Integer id);

}
