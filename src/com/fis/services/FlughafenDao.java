package com.fis.services;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Flughäfen;

@Stateless
public class FlughafenDao extends AbstractDao {

	public FlughafenDao() { super(); }
	
	public List<Flughäfen>listAllFlughäfen() {
		return entityManager.createNamedQuery("Flughäfen.findAll", Flughäfen.class).getResultList();
	}
	
	public void createFlughafen(PrintWriter pw, String bezeichnung) {
		try {
			Flughäfen f = new Flughäfen(bezeichnung);
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Der Flughafen konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
			return;
		}
		writeAlert(pw, "Erfolg!", "Der Flughafen wurden erfolgreich hinzugefügt.", "alert-success", "left");
	}
	
}
