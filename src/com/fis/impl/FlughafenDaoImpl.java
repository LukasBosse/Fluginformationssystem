/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.FlughafenDao;
import com.fis.dto.Flughäfen;

/**
 * Klasse: FlughafenDao
 * Beschreibung: Dao zum Zugriff auf die 'Flughafen'-Datenbanktabelle.
 */

@Stateless
public class FlughafenDaoImpl extends AbstractDaoImpl implements FlughafenDao {

	/** Constructor **/
	public FlughafenDaoImpl() { super(); }
	
	/**
	 * Auflistung sämtlicher hinterlegten Flughäfen.
	 * @return List<Flughäfen>
	 */
	public List<Flughäfen>listAllFlughäfen() {
		return entityManager.createNamedQuery("Flughäfen.findAll", Flughäfen.class).getResultList();
	}
	
	/**
	 * Speichert einen neuen Flughafen in die Datenbank.
	 * @param pw
	 * @param bezeichnung
	 */
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
