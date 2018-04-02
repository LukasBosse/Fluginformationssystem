/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.FluglinienDao;
import com.fis.dto.Fluglinien;

/**
 * Klasse: FluglinienDao
 * Beschreibung: Dao zum Zugriff auf die 'Fluglinien'-Datenbanktabelle.
 */

@Stateless
public class FluglinienDaoImpl extends AbstractDaoImpl implements FluglinienDao {

	/** Constructor **/
	public FluglinienDaoImpl() { super(); }
	
	/**
	 * Erstellt eine neue Relation (Starort<->Zielort).
	 * @param pw
	 * @param fluglinie
	 * @param startOrt
	 * @param zielOrt
	 */
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
			writeAlert(pw, "Warnung!", "Die Relation konnte nicht hinzugef�gt werden. Bitte pr�fen Sie Ihre Eingaben!", "alert-danger", "left");
			return;
		}
		writeAlert(pw, "Erfolg!", "Die Relation wurden erfolgreich hinzugef�gt.", "alert-success", "left");
	}
	
	/**
	 * Listet s�mtliche Relationen auf.
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> listRelationen() {
		return entityManager.createNativeQuery("SELECT fluglinien.Fluglinie, starthafen.Bezeichnung As `Starthafen`, landehafen.Bezeichnung As `Landehafen` FROM fluglinien INNER JOIN flugh�fen As `starthafen` ON fluglinien.Startort = starthafen.ID INNER JOIN flugh�fen As `landehafen` ON fluglinien.Zielort = landehafen.ID").getResultList();
	}

}
