package com.taskms.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taskms.entity.Account;

@Transactional
@Repository
public class AccountDAO_Implement implements AccountDAO {

	@Autowired
	SessionFactory factory;

	/* Find By ID */
	@Override
	public Account findbyID(Integer id) {
		Session session = factory.getCurrentSession();
		Account entity = session.find(Account.class, id);
		return entity;
	}

	/* Find By Email */
	@Override
	public Account findbyUsername(String username) {
		Session session = factory.getCurrentSession();
		Account entity = session.find(Account.class, username);
		return entity;
	}

	/* Find All */
	@Override
	public List<Account> findAll() {
		String hql = "FROM Member";
		Session session = factory.getCurrentSession();
		TypedQuery<Account> query = session.createQuery(hql, Account.class);
		List<Account> list = query.getResultList();
		return list;
	}

	/* Create */
	@Override
	public Account create(Account entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	/* Update */
	@Override
	public void update(Account entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	/* Delete */
	@Override
	public Account delete(Integer id) {
		Session session = factory.getCurrentSession();
		Account entity = session.find(Account.class, id);
		session.delete(entity);
		return entity;
	}

}
