package com.taskms.dao;

import java.util.List;

import com.taskms.entity.Task;

public interface TaskDAO {
	// Find By ID
	Task findByID(Integer id);

	// Find All
	List<Task> findAll(String username);

	// Create
	Task create(Task entity);

	// Update
	void update(Task entity);

	// Delete
	Task delete(Integer id);

	// Find By Keyword
	List<Task> findByKeyword(String keyword, String username);
	
	// Find Important Task
	List<Task> findImportant(String ids, String username);

}
