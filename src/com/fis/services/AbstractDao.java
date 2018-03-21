package com.fis.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AbstractDao {

	protected EntityManagerFactory emfactory;
	protected EntityManager entityManager;
	protected Query query;
	
	public AbstractDao() {
		emfactory = Persistence.createEntityManagerFactory("JPA_CONNECTION");
		entityManager = emfactory.createEntityManager();
	}
		
	public void closeConnection() {
		entityManager.close();
		emfactory.close();
	}
	
}
