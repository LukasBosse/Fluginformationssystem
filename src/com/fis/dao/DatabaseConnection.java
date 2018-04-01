/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.dao;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Remote
public interface DatabaseConnection {
	
	EntityManagerFactory emfactory = null;
	EntityManager entityManager = null;
	
	public void disconnect();
	
	public EntityManagerFactory getEntityManagerFactory();
	
	public EntityManager getEntityManager();
	
}
