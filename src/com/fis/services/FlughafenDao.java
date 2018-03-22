package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Flugh�fen;

@ManagedBean
public class FlughafenDao extends AbstractDao {

	public FlughafenDao(Writer oS) { super(oS); }
	
	public List<Flugh�fen>listAllFlugh�fen() {
		return entityManager.createNamedQuery("Flugh�fen.findAll", Flugh�fen.class).getResultList();
	}
	
	public void createFlughafen(String bezeichnung) {
		Flugh�fen f = new Flugh�fen(bezeichnung);
		entityManager.getTransaction().begin();
		entityManager.persist(f);
		entityManager.getTransaction().commit();
		verify(f);
	}
	
	public void verify(Flugh�fen f) {
		if(f.getId() > 0) {
			htmlWriter.writeAlert("Erfolg!", "Der Flughafen wurden erfolgreich hinzugef�gt.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Der Flughafen konnte nicht hinzugef�gt werden. Bitte pr�fen Sie Ihre Eingaben!", "alert-danger", "left");
		}
	}
	
}
