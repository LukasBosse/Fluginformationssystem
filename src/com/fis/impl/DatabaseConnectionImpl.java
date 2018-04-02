/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.fis.dao.DatabaseConnection;

/**
 * Klasse: DatabaseConnectionImpl
 * Beschreibung: Aufbau und Beendung der Datebankverbindung. 
 */

@Stateless
public class DatabaseConnectionImpl implements DatabaseConnection {

	protected EntityManagerFactory emfactory;
	protected EntityManager entityManager;
	
	/** Constructor **/
	public DatabaseConnectionImpl() {}
	
	/** 
	 * Constructor 
	 * @param emfactory
	 * @param entityManager
	 **/
	public DatabaseConnectionImpl(EntityManagerFactory emfactory, EntityManager entityManager) {
		this.emfactory = emfactory;
		this.entityManager = entityManager;		
	}

	/** Beendung der Datenbankverbindung. **/
	public void disconnect() {
		entityManager.close();
		emfactory.close();
	}

	/** Getter-Methode EntityManagerFactor @return emfactory **/
	public EntityManagerFactory getEntityManagerFactory() {
		return emfactory;
	}

	/** Getter-Methode EntityManager @return entityManager **/
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
