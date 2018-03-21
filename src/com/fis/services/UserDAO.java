package com.fis.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fis.model.User;

public class UserDAO {
		
	private EntityManagerFactory emfactory;
	private EntityManager entityManager;
	private Query query;
	
	public UserDAO() {
		emfactory = Persistence.createEntityManagerFactory( "MeineJpaPU" );
		entityManager = emfactory.createEntityManager();
	}
	
	public User findUser(String username) {
	    query = entityManager.createQuery("Select u from User u where u.username = :username");
	    query.setParameter("username", username);
	    User user = (User) query.getSingleResult();
		return user;
	}
	
	public void createUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();	
	}
	
	public void closeConnection() {
		entityManager.close();
		emfactory.close();
	}

}
