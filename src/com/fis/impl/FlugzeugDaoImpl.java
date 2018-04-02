/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.FlugzeugDao;
import com.fis.dto.Flugzeuge;

/**
 * Klasse: FlugzeugDao
 * Beschreibung: Dao zum Zugriff auf die 'Flugzeuge'-Datenbanktabelle.
 */

@Stateless
public class FlugzeugDaoImpl extends AbstractDaoImpl implements FlugzeugDao {

	/** Constructor **/
	public FlugzeugDaoImpl() { super(); }
	
	/** Listet sämtliche Flugzeuge auf.
	 * @return List<Flugzeuge>
	 */
	public List<Flugzeuge> listAllFlugzeuge() {
		return entityManager.createNamedQuery("Flugzeuge.findAll", Flugzeuge.class).getResultList();
	}
	
	/**
	 * Ermittelt ein Flugzeug anhand seiner ID.
	 * @param id
	 * @return Flugzeuge.class
	 */
	public Flugzeuge findFlugzeug(int id) {
		return entityManager.find(Flugzeuge.class, id);
	}

	/**
	 * Ermittelt ein Flugzeug anhand seiner aktuellen Fluglinie.
	 * @param fluglinie
	 * @return List<Flugzeuge>
	 */
	@SuppressWarnings("unchecked")
	public List<Flugzeuge> findFlugzeugeByFluglinie(String fluglinie) {
		query = entityManager.createQuery("SELECT f From Flugzeuge f WHERE f.fluglinie = :fluglinie", Flugzeuge.class);
		query.setParameter("fluglinie", fluglinie);
		return query.getResultList();
	}
	
	/**
	 * Listet sämtliche Flugzeuge mit zusätzlichen Details auf.
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findFlugzeugWithDetails() {
		List<Object[]> obj = entityManager.createNativeQuery("SELECT f.id, f.hersteller, f.type, f.sitze, f.fluglinie, g.Gesellschaft FROM flugzeuge As `f`, fluggesellschaften As `g` WHERE g.ID = f.fluggesellschaft").getResultList();
		return obj;
	}
	
	/**
	 * Speichert ein neues Flugzeug in die Datenbank.
	 * @param pw
	 * @param f
	 */
	public void create(PrintWriter pw, Flugzeuge f) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(f);
			entityManager.getTransaction().commit();	
		} catch (Exception e) {
	  		writeAlert(pw, "Warnung!", "Das Flugzeug konnte nicht hinzugefügt werden.", "alert-danger", "left");
	  		return;
		}
  		writeAlert(pw, "Erfolg!", "Das Flugzeug wurde erfolgreich hinzugefügt.", "alert-success", "left");
	}
	
	/**
	 * Ändert die Fluglinie eines spezifischen Flugzeuges.
	 * @param pw
	 * @param id
	 * @param fluglinie
	 */
	public void updateFluglinie(PrintWriter pw, int id, String fluglinie) {
		try {
			Flugzeuge f = findFlugzeug(id);
			entityManager.getTransaction().begin();
			f.setFluglinie(fluglinie);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
	  		writeAlert(pw, "Warung!", "Das Flugzeug konnte nicht zugewiesen werden.", "alert-danger", "left");
	  		return;
		}
  		writeAlert(pw, "Erfolg!", "Das Flugzeug wurde erfolgreich zugewiesen.", "alert-success", "left");
	}

}
