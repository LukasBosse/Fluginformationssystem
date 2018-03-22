package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Flughäfen;

@ManagedBean
public class FlughafenDao extends AbstractDao {

	public FlughafenDao(Writer oS) { super(oS); }
	
	public List<Flughäfen>listAllFlughäfen() {
		return entityManager.createNamedQuery("Flughäfen.findAll", Flughäfen.class).getResultList();
	}
	
	public void createFlughafen(String bezeichnung) {
		Flughäfen f = new Flughäfen(bezeichnung);
		entityManager.getTransaction().begin();
		entityManager.persist(f);
		entityManager.getTransaction().commit();
		verify(f);
	}
	
	public void verify(Flughäfen f) {
		if(f.getId() > 0) {
			htmlWriter.writeAlert("Erfolg!", "Der Flughafen wurden erfolgreich hinzugefügt.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Der Flughafen konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
		}
	}
	
}
