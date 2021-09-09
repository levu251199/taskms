package com.taskms.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taskms.entity.Task;

@Transactional
@Repository
public class TaskDAO_Implement implements TaskDAO {

	@Autowired
	SessionFactory factory;

	/* Find By ID */
	@Override
	public Task findByID(Integer id) {
		Session session = factory.getCurrentSession();
		Task entity = session.find(Task.class, id);
		return entity;
	}

	/* Find All */
	@Override
	public List<Task> findAll(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Task WHERE account_id LIKE '" + username + "'";
		TypedQuery<Task> query = session.createQuery(hql, Task.class);
		List<Task> list = query.getResultList();
		return list;
	}

	/* Create */
	@Override
	public Task create(Task entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	/* Update */
	@Override
	public void update(Task entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	/* Delete */
	@Override
	public Task delete(Integer id) {
		Session session = factory.getCurrentSession();
		Task entity = session.find(Task.class, id);
		session.delete(entity);
		return entity;
	}

	/* Find by keyword */
	@Override
	public List<Task> findByKeyword(String keyword, String username) {
		String hql = "FROM Task WHERE (account_id LIKE '" + username + "') AND (name LIKE :kw OR description LIKE :kw)";
		Session session = factory.getCurrentSession();
		TypedQuery<Task> query = session.createQuery(hql, Task.class);
		query.setParameter("kw", "%" + keyword + "%");
		List<Task> list = query.getResultList();
		return list;
	}

	@Override
	public List<Task> findImportant(String ids, String username) {
		String hql = "FROM Task WHERE (account_id LIKE '" + username + "') AND (id IN (" + ids + "))";
		Session session = factory.getCurrentSession();
		TypedQuery<Task> query = session.createQuery(hql, Task.class);
		List<Task> list = query.getResultList();
		return list;
	}

}
