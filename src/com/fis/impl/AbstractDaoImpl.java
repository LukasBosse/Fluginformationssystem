/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fis.dao.AbstractDao;
import com.fis.de.NamingService;

/**
 * Klasse: AbstractDao
 * Beschreibung: Abstrakte Data Access Object (DAO) Definition.
 * Deklarierung und Initalisierung von DAO-Standardattributen und Methoden.
 * Beinhaltet EntityManagerFactory, EntityManager, Query sowie die Ausgabe von Alerts.
 */

public class AbstractDaoImpl implements AbstractDao {

	protected EntityManagerFactory emfactory;
	protected EntityManager entityManager;
	protected Query query;
	
	@EJB
	private DatabaseConnectionImpl dbConnection;
	private NamingService namingService;
	private final String DATABASEURL = "databaseConnection";
				
	/** Constructor **/
	public AbstractDaoImpl() { initalizeDatabaseConnection(); }
	
	/** Methode zur Initialisierung der Datenbankverbindung via JNDI **/
	public void initalizeDatabaseConnection() {
		namingService = new NamingService();
		if(namingService.get(DATABASEURL) == null) {
			emfactory = Persistence.createEntityManagerFactory("JPA_CONNECTION");
			entityManager = emfactory.createEntityManager();
			dbConnection = new DatabaseConnectionImpl(emfactory, entityManager);
			namingService.put(DATABASEURL, dbConnection);
		} else {
			dbConnection = (DatabaseConnectionImpl) namingService.get(DATABASEURL);
			emfactory = dbConnection.getEntityManagerFactory();
			entityManager = dbConnection.getEntityManager();
		}
	}
	
	/**
	 * Methode zum Schreiben von Warnungs- oder Erfolgsmeldungen.
	 * @param pw
	 * @param title
	 * @param message
	 * @param type
	 * @param position
	 */
	public void writeAlert(PrintWriter pw, String title, String message, String type, String position) {
		pw.write(("<div style='position: absolute; bottom: 0px; " + position + ": 15px;' class='alert " + type + "'  role='alert'" +
				"<strong>" + title + "</strong> " + message +
				  "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
				    "<span aria-hidden='true'>&times;</span>" +
				  "</button>" +
				"</div>"));
	}
	
	/**
	 * Beendet die EntityManagerFactory und EntityManager des jeweiligen DAOs
	 */
	public void closeConnection() {
		entityManager.close();
		emfactory.close();
		namingService.remove(DATABASEURL);
	}
	
}
