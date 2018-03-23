package com.fis.services;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.model.Fluglinien;

@Stateless
public class FluglinienDao extends AbstractDao {

	public FluglinienDao() { super(); }
	
	public void createRelation(PrintWriter pw, String fluglinie, int startOrt, int zielOrt) {
		if(startOrt == zielOrt) {
			writeAlert(pw, "Warnung!", "Ein Flughafen kann nicht gleichzeitig Start- und Zielort sein.", "alert-danger", "left");
			return;
		}
		try {
			Fluglinien r = new Fluglinien(fluglinie, startOrt, zielOrt);
			entityManager.getTransaction().begin();
			entityManager.persist(r);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Die Relation konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
			return;
		}
		writeAlert(pw, "Erfolg!", "Die Relation wurden erfolgreich hinzugefügt.", "alert-success", "left");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listRelationen() {
		return entityManager.createNativeQuery("SELECT fluglinien.Fluglinie, starthafen.Bezeichnung As `Starthafen`, landehafen.Bezeichnung As `Landehafen` FROM fluglinien INNER JOIN flughäfen As `starthafen` ON fluglinien.Startort = starthafen.ID INNER JOIN flughäfen As `landehafen` ON fluglinien.Zielort = landehafen.ID").getResultList();
	}

}
