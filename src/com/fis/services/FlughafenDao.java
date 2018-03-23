package com.fis.services;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Flugh�fen;

@Stateless
public class FlughafenDao extends AbstractDao {

	public FlughafenDao() { super(); }
	
	public List<Flugh�fen>listAllFlugh�fen() {
		return entityManager.createNamedQuery("Flugh�fen.findAll", Flugh�fen.class).getResultList();
	}
	
	public void createFlughafen(PrintWriter pw, String bezeichnung) {
		try {
			Flugh�fen f = new Flugh�fen(bezeichnung);
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Der Flughafen konnte nicht hinzugef�gt werden. Bitte pr�fen Sie Ihre Eingaben!", "alert-danger", "left");
			return;
		}
		writeAlert(pw, "Erfolg!", "Der Flughafen wurden erfolgreich hinzugef�gt.", "alert-success", "left");
	}
	
}
