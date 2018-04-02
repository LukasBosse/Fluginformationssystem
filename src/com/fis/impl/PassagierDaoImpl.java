/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;

import com.fis.dao.PassagierDao;
import com.fis.dto.Passagier;

/**
 * Klasse: PassagierDao
 * Beschreibung: Dao zum Zugriff auf die 'Passagiere'-Datenbanktabelle.
 */

@Stateless
public class PassagierDaoImpl extends AbstractDaoImpl implements PassagierDao {

	/** Constructor **/
	public PassagierDaoImpl() { super(); }
	
	/**
	 * Listet sämtliche Passagiere auf.
	 * @return List<Passagier>
	 */
	public List<Passagier> listPassagier() {
		return entityManager.createQuery("SELECT p FROM Passagier p", Passagier.class).getResultList();
	}
	
	/**
	 * Speichert einen neuen Passagier.
	 * @param pw
	 * @param name
	 * @param ort
	 * @param geburtsdatum
	 * @param geschlecht
	 */
	public void createPassagier(PrintWriter pw, String name, String ort, String geburtsdatum, boolean geschlecht) {
		try {
			Passagier p = new Passagier(name,ort,geburtsdatum,geschlecht);
			entityManager.getTransaction().begin();
			entityManager.persist(p);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warung!", "Der Passagier konnte nicht hinzugefügt werden.", "alert-danger", "right");
			return;
		}
		writeAlert(pw, "Erfolg!", "Der Passagier wurde erfolgreich hinzugefügt.", "alert-success", "right");
	}

	public Passagier findPassagier(String name) {
		return entityManager.find(Passagier.class, name);
	}

}
