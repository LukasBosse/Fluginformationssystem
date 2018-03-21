package com.fis.services;

import com.fis.model.User;

public class UserDao extends AbstractDao {
				
	public UserDao() { super(); }
	
	public User findUser(String username) {
	    query = entityManager.createQuery("Select u from User u where u.username = :username");
	    query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
	
	public void create(User u) {
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();	
	}
	
}
