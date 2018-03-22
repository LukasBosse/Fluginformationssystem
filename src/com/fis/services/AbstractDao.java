package com.fis.services;

import java.io.Writer;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fis.de.HTMLWriter;

@ManagedBean
public class AbstractDao {

	protected EntityManagerFactory emfactory;
	protected EntityManager entityManager;
	protected Query query;
	
	protected HTMLWriter htmlWriter;
	
	public AbstractDao(Writer oS) {
		emfactory = Persistence.createEntityManagerFactory("JPA_CONNECTION");
		entityManager = emfactory.createEntityManager();
		htmlWriter = new HTMLWriter(oS);
	}
		
	public void closeConnection() {
		entityManager.close();
		emfactory.close();
	}
	
	public HTMLWriter getWriter() {
		return htmlWriter;
	}
	
}
