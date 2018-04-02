/*
 * Project: Fluginformationssystem
 * Author: Lukas Bosse, Torben Kuhnke, Malte Peters (WI 47/15)
 */

package com.fis.impl;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;

import com.fis.dao.BuchungsDao;
import com.fis.dto.Buchung;
import com.fis.dto.Passagier;

/**
 * Klasse: BuchungsDaoImpl
 * Beschreibung: Dao zum Zugriff auf die 'Buchung'-Datenbanktabelle. 
 */

@Stateless
public class BuchungsDaoImpl extends AbstractDaoImpl implements BuchungsDao {

	/** Constructor **/
	public BuchungsDaoImpl() { super(); }
	
	/**
	 * Ermittlung s�mtlicher unbest�tigter Buchungen. 
	 * @return
	 */
	public List<Buchung> findUnbest�tigteBuchung() {
		return entityManager.createQuery("Select b from Buchung b where b.bestaetigt = 0", Buchung.class).getResultList();
	}
	
	/**
	 * Ermittlung s�mtlicher Buchungen zur jeweiligen Flugnummer.
	 * @param flugNr
	 * @return List<Buchung>
	 */
	@SuppressWarnings("unchecked")
	public List<Buchung> findBuchungById(String flugNr) {
		query = entityManager.createQuery("SELECT b FROM Buchung b WHERE b.flugnr = :id", Buchung.class);
		query.setParameter("id", flugNr);
		return query.getResultList();
	}
	
	/**
	 * Ermittlungen s�mtlicher Buchungen eines Passagiers anhand seines Namens.
	 * @param name
	 * @return List<Buchung>
	 */
	@SuppressWarnings("unchecked")
	public List<Buchung> findBuchungByName(String name) {
		query = entityManager.createQuery("SELECT b FROM Buchung b WHERE b.name = :name", Buchung.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	/**
	 * Best�tigung einer Buchung anhand seiner ID.
	 * @param pw
	 * @param id
	 */
	public void updateBuchung(PrintWriter pw, int id) {
		try {
			Buchung b = entityManager.find(Buchung.class, id);
			entityManager.getTransaction().begin();
			b.setBestaetigt(!b.getBestaetigt());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Die Buchung konnte nicht best�tigt werden.", "alert-danger", "right");
			return;
		}
		writeAlert(pw, "Erfolg!", "Die Buchung wurde best�tigt.", "alert-success", "right");
	}

	public void addBuchung(PrintWriter pw, Passagier p, String flugNr) {
		try {
			Buchung b = new Buchung();
			b.setName(p.getName());
			b.setFlugnr(flugNr);
			b.setOrt(p.getOrt());
			b.setPreis(BigDecimal.valueOf(generateRandomPreis()));
			b.setTag(new Date());
			b.setGeschlecht(0);
			entityManager.getTransaction().begin();
			entityManager.persist(b);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			writeAlert(pw, "Warnung!", "Die Buchung konnte nicht erstellt werden.", "alert-danger", "right");
			return;
		}
		writeAlert(pw, "Erfolg!", "Die Buchung wurde erstellt.", "alert-success", "right");
	}
	
	private double generateRandomPreis() {
		Random ran = new Random(1000);
		return ran.nextDouble();
	}

}
