/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.FlughafenDao;
import com.fis.dto.Flugh�fen;

/**
 * Klasse: FlughafenDao
 * Beschreibung: Dao zum Zugriff auf die 'Flughafen'-Datenbanktabelle.
 */

@Stateless
public class FlughafenDaoImpl extends AbstractDaoImpl implements FlughafenDao {

	/** Constructor **/
	public FlughafenDaoImpl() { super(); }
	
	/**
	 * Auflistung s�mtlicher hinterlegten Flugh�fen.
	 * @return List<Flugh�fen>
	 */
	public List<Flugh�fen>listAllFlugh�fen() {
		return entityManager.createNamedQuery("Flugh�fen.findAll", Flugh�fen.class).getResultList();
	}
	
	/**
	 * Speichert einen neuen Flughafen in die Datenbank.
	 * @param pw
	 * @param bezeichnung
	 */
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
