package com.fis.services;

import java.io.PrintWriter;

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
	
	public void writeAlert(PrintWriter pw, String title, String message, String type, String position) {
		pw.write(("<div style='position: absolute; bottom: 0px; " + position + ": 15px;' class='alert " + type + "'  role='alert'" +
				"<strong>" + title + "</strong> " + message +
				  "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
				    "<span aria-hidden='true'>&times;</span>" +
				  "</button>" +
				"</div>"));
	}
		
	public void closeConnection() {
		entityManager.close();
		emfactory.close();
	}
	
}
